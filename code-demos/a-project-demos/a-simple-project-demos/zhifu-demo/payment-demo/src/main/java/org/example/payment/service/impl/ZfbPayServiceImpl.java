package org.example.payment.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import org.example.payment.config.ZfbPayConfig;
import org.example.payment.entity.OrderInfo;
import org.example.payment.entity.PaymentInfo;
import org.example.payment.entity.RefundInfo;
import org.example.payment.enums.PayType;
import org.example.payment.enums.zfb.ZfbNotifyType;
import org.example.payment.service.IOrderInfoService;
import org.example.payment.service.IPaymentInfoService;
import org.example.payment.service.IRefundInfoService;
import org.example.payment.service.IZfbPayService;
import org.example.payment.vo.BaseResult;
import org.example.payment.vo.CodeEnum;
import org.example.payment.vo.PayInfoVO;
import org.example.payment.enums.OrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * 支付宝业务层
 */
@Slf4j
@Service
public class ZfbPayServiceImpl implements IZfbPayService {

    @Autowired
    private IPaymentInfoService iPaymentInfoService; // 交易记录

    @Autowired
    private IRefundInfoService iRefundInfoService; // 退款接口

    @Autowired
    private IOrderInfoService iOrderInfoService; // 订单接口

    @Autowired
    private AlipayClient alipayClient;

    @Autowired
    private ZfbPayConfig zfbPayConfig;

    /**
     * 支付宝支付当面付
     * @param orderNo
     * @return
     */
    @Override
    public BaseResult pcPay(String orderNo) {
        // 1. 根据订单编号查询订单信息
        OrderInfo orderInfo = iOrderInfoService.findByOrderNo(orderNo);
        if (orderInfo == null) {
            return BaseResult.error(CodeEnum.ORDER_ERROR);
        }
        // 2. 封装请求参数
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        request.setNotifyUrl(zfbPayConfig.getNotify_url().concat(ZfbNotifyType.PC_NOTIFY.getType()));// 支付成功回调
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", orderInfo.getOrderNo()); //  订单编号
        bizContent.put("total_amount", orderInfo.getTotalFee());// 订单金额
        bizContent.put("subject", orderInfo.getTitle()); // 订单标题
        request.setBizContent(bizContent.toString());
        // 3. 发送请求
        AlipayTradePrecreateResponse response = null;
        // 4. 支付宝二维码
        String qrCode = "";
        try {
            response = alipayClient.execute(request);
            if(!response.isSuccess()){
                throw  new Exception("生成支付宝订单失败" + response.getMsg());
            }
            qrCode = response.getQrCode();

        } catch (Exception e) {
            e.printStackTrace();
        }
        PayInfoVO payInfoVO = new PayInfoVO();
        payInfoVO.setCodeUrl(qrCode);
        payInfoVO.setOrderNo(orderInfo.getOrderNo());
        return BaseResult.ok(payInfoVO);
    }

    /**
     * 修改订单状态
     * @param request
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateOrderStatus(HttpServletRequest request) {

        // 1. 获取订单状态
        String tradeStatus = request.getParameter("trade_status");
        if (tradeStatus.equals("TRADE_SUCCESS")){
            // 2. 获取订单编号
            String orderNo = request.getParameter("out_trade_no");
            OrderInfo orderInfo = iOrderInfoService.findByOrderNo(orderNo);
            if (!OrderStatus.NOTPAY.getType().equals(orderInfo.getOrderStatus())) {
                return;
            }
            // 3. 修改订单状态
            iOrderInfoService.updateOrderStatus(orderNo, OrderStatus.SUCCESS);
            // 4. 添加交易记录
            PaymentInfo paymentInfo = new PaymentInfo();
            paymentInfo.setOrderNo(orderNo);// 订单编号
            String tradeNo = request.getParameter("trade_no");// 获取交易记录号
            paymentInfo.setTransactionId(tradeNo);// 交易号
            paymentInfo.setPaymentType(PayType.ALIPAY.getType());// 支付类型
            paymentInfo.setTradeType("当面付");
            paymentInfo.setTradeState(tradeStatus);// 交易状态
            paymentInfo.setPayerTotal(orderInfo.getTotalFee());
            paymentInfo.setContent(JSON.toJSONString(request.getParameterMap()));
            paymentInfo.setCreateTime(LocalDateTime.now());
            iPaymentInfoService.save(paymentInfo);
        }
    }

    /**
     * 退款
     * @param orderNo 订单编号
     * @param reason 退款理由
     */
    @Override
    public void refund(String orderNo, String reason) {
        log.info("************  支付宝退款**********");
        // 1. 根据订单编号创建退款单
        RefundInfo refundInfo = iRefundInfoService.createRefundsByOrderNo(orderNo, reason);
        // 2. 封装参数
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", orderNo);//订单编号
        bizContent.put("refund_amount", refundInfo.getRefund()); // 退款金额
        // 3. 转json存入request
        request.setBizContent(bizContent.toString());
        //4. 发送请求
        AlipayTradeRefundResponse response = null;
        try {
            response = alipayClient.execute(request);
            if(response.isSuccess()){
                // 5. 修改订单状态
                iOrderInfoService.updateOrderStatus(orderNo,OrderStatus.REFUND_SUCCESS);
                // 6. 退款单状态
                iRefundInfoService.updateRefundAliPayStatus(orderNo);

            } else {
               log.error("******* 调用失败 =>{}",response.getSubMsg());
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }


    /**
     * 根据订单编号查询交易状态
     * @param orderNo 订单编号
     * @return
     */
    @Override
    public String queryPayStauts(String orderNo) throws Exception {
        log.info(" **************  统一交易状态查询 **********");
        // 1. 组装参数
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", orderNo);//订单编号
        request.setBizContent(bizContent.toString());
        AlipayTradeQueryResponse response = alipayClient.execute(request);
        if(response.isSuccess()){
            log.info("**** 查询交易状态调用成功={}",response.getBody());
            return response.getBody();
        } else {
            String body = response.getBody();
            log.error("调用失败=>{}",body);
            throw  new Exception("查询交易记录失败");
        }
    }


}
