package com.itbaizhan.service.impl;

import com.alibaba.fastjson.JSON;
import com.itbaizhan.config.WxPayConfig;
import com.itbaizhan.entity.OrderInfo;
import com.itbaizhan.entity.RefundInfo;
import com.itbaizhan.enums.OrderStatus;
import com.itbaizhan.enums.wx.WxApiType;
import com.itbaizhan.enums.wx.WxNotifyType;
import com.itbaizhan.enums.wx.WxTradeState;
import com.itbaizhan.service.IOrderInfoService;
import com.itbaizhan.service.IPaymentInfoService;
import com.itbaizhan.service.IRefundInfoService;
import com.itbaizhan.service.IWxPaymentService;
import com.itbaizhan.utils.WxVerifierUtils;
import com.itbaizhan.vo.BaseResult;
import com.itbaizhan.vo.CodeEnum;
import com.itbaizhan.vo.PayInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.awt.image.Raster;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 微信支付
 */
@Slf4j
@Service
public class WxPaymentServiceImpl implements IWxPaymentService {


    @Autowired
    private IOrderInfoService iOrderInfoService;// 订单接口

    @Autowired
    WxPayConfig wxPayConfig;// 微信支付配置参数

    @Autowired
    CloseableHttpClient wxPayClient;

    @Autowired
    IPaymentInfoService iPaymentInfoService;// 交易记录接口

    @Autowired
    IRefundInfoService iRefundInfoService;//退款接口



    // 独占锁
    private final  ReentrantLock lock = new ReentrantLock();


    /**
     * Native下单
     *
     * @param orderNo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResult nativePay(String orderNo) throws Exception {
        log.info("***********  开始 Native下单 *********");
        // 1. 根据订单编号查询订单信息
        OrderInfo orderInfo = iOrderInfoService.findByOrderNo(orderNo);
        if (orderInfo == null) {
            return BaseResult.error(CodeEnum.ORDER_ERROR);
        }
        if (orderInfo != null && !StringUtils.isEmpty(orderInfo.getCodeUrl())) {
            // 直接返回二维码
            PayInfoVO payInfoVO = new PayInfoVO();
            payInfoVO.setCodeUrl(orderInfo.getCodeUrl());
            payInfoVO.setOrderNo(orderInfo.getOrderNo());
            return BaseResult.ok(payInfoVO);
        }

        // 2. 调用统一下载API  https://api.mch.weixin.qq.com/v3/pay/transactions/native
        HttpPost httpPost = new HttpPost(wxPayConfig.getDomain().concat(WxApiType.NATIVE_PAY.getType()));

        // 3. 组装请求参数
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("appid", wxPayConfig.getAppid());// 应用id
        paramsMap.put("mchid", wxPayConfig.getMchId());// 商户id
        paramsMap.put("description", "test");// 商品描述
        paramsMap.put("out_trade_no", orderInfo.getOrderNo());// 订单编号
        paramsMap.put("notify_url", wxPayConfig.getNotifyDomain().concat(WxNotifyType.NATIVE_NOTIFY.getType()));// 通知地址
        HashMap<String, Object> amountMap = new HashMap<>();
        amountMap.put("total", orderInfo.getTotalFee());
        paramsMap.put("amount", amountMap);// 订单金额

        // 4. 将参数转换为json字符串
        String jsonString = JSON.toJSONString(paramsMap);
        log.info("Native下单参数=======>{}" + jsonString);

        // 5. 准备消息 boby
        StringEntity entity = new StringEntity(jsonString, "UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        // 6. 准备请求头
        httpPost.setHeader("Accept", "application/json");

        // 7.完成签名并执行请求
        CloseableHttpResponse response = wxPayClient.execute(httpPost);

        try {
            // 8. 拿出body 响应体
            String bodyString = EntityUtils.toString(response.getEntity());
            // 9. 获取响应状态码
            int statusCode = response.getStatusLine().getStatusCode();
            // 10 判断响应码
            if (statusCode == 200) {
                HashMap<String, String> responseMap = JSON.parseObject(bodyString, HashMap.class);
                // 11 取出code_url
                String codeUrl = responseMap.get("code_url");
                // 更新code_url
                iOrderInfoService.saveCodeUrl(orderInfo.getId(), codeUrl);
                PayInfoVO payInfoVO = new PayInfoVO();
                payInfoVO.setCodeUrl(codeUrl);
                payInfoVO.setOrderNo(orderInfo.getOrderNo());
                return BaseResult.ok(payInfoVO);
            } else {
                log.error("Native 下单失败响应码 " + statusCode + "返回结果" + bodyString);
                return BaseResult.error(CodeEnum.PAYMENT_ERROR);
            }
        } finally {
            response.close();
        }
    }


    /**
     * 修改订单状态
     *
     * @param bodyMap -> 订单编号  5   15  30
     */
    @Override
    public void updateOrderStatus(Map<String, Object> bodyMap) throws GeneralSecurityException {
        log.info("******************* 修改订单状态 *********");
        // 1. 获取明文
        String plainText = WxVerifierUtils.decrypt(bodyMap, wxPayConfig.getApiV3Key());
        // 2. 字符串转为map
        HashMap plainTextMap = JSON.parseObject(plainText, HashMap.class);
        // 3. 获取订单编号
        String orderNo = (String) plainTextMap.get("out_trade_no");

        // 尝试获得锁， 如果成功返回true  获取失败立即返回false 不必一直等待锁的释放
        if (lock.tryLock()){
            try {
                // 4. 根据订单编号获取订单信息
                OrderInfo orderInfo = iOrderInfoService.findByOrderNo(orderNo);
                // 5. 判定订单状态
                if (!OrderStatus.NOTPAY.getType().equals(orderInfo.getOrderStatus())) {
                    return;
                }
                // 7. 更新订单状态
                iOrderInfoService.updateOrderStatus(orderInfo.getId(), OrderStatus.SUCCESS);

                // 8. 添加一条交易记录日志
                iPaymentInfoService.createPaymentInfo(plainTextMap);
            }finally {
               // 手动释放锁
               lock.unlock();
            }
        }


    }

    /**
     * 根据订单编号查询订单
     * @param orderNo
     * @return
     */
    @Override
    public String queryOrder(String orderNo) throws IOException {
        log.info("*************  查询订单信息 *********");


        // 1. 组装请求url地址 https://api.mch.weixin.qq.com/v3/pay/transactions/out-trade-no/1217752501201407033233368018?mchid=1230000109
        String url = String.format(WxApiType.ORDER_QUERY_BY_NO.getType(), orderNo);
        url = wxPayConfig.getDomain().concat(url).concat("?mchid=").concat(wxPayConfig.getMchId());

        // 2. 获取httpget
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Accept","application/json");

        // 3. 完成签名后发起请求
        CloseableHttpResponse response = wxPayClient.execute(httpGet);

        try {
            // 4. 获取响应体body
            String bodyString = EntityUtils.toString(response.getEntity());
            // 5. 获取状态码
            int statusCode = response.getStatusLine().getStatusCode();
            // 6. 判断状态码
            if (statusCode == 200){
                log.info("查询成功 =>{}",bodyString);
            }else if (statusCode == 204){// 处理成功  没有body
                log.info("成功");
            }else {
                log.info("查询订单接口失败"+statusCode + "返回结果 " + bodyString);
                throw  new IOException("request failed");
            }
            return bodyString;
        }finally {
            response.close();
        }
    }

    /**
     * 检查订单状态
     * @param orderNo 订单编号
     */
    @Override
    public void checkOrderStatus(String orderNo) throws IOException {
        log.info("************ 根据订单编号核实订单状态 ********");
        // 1. 根据订单编号查询订单信息
        String result = this.queryOrder(orderNo);
        // 2. JSON转Map
        Map<String,Object> resultMap = JSON.parseObject(result, HashMap.class);
        // 3. 获取微信订单状态
        String tradeState = (String) resultMap.get("trade_state");
        // 4. 判断订单状态
        if (tradeState.equals(WxTradeState.SUCCESS.getType())){
            log.warn("核实订单已支付==========>{}",orderNo);
            // 5. 如果确认订单支付，则更新本地订单状态
            iOrderInfoService.updateOrderStatus(orderNo,OrderStatus.SUCCESS);
            // 6. 记录支付日志
            iPaymentInfoService.createPaymentInfo(resultMap);
        }
        // 8. 关单处理
        if(tradeState.equals(WxTradeState.NOTPAY.getType())){
            // 调用微信关单api关闭订单
            this.cancelOrder(orderNo);
        }


    }

    /**
     * 根据订单编号关闭订单
     * @param orderNo 订单编号
     */
    @Override
    public void cancelOrder(String orderNo) throws IOException {
        // 1.调用微信关单api关闭订单
        this.closeOrder(orderNo);
        // 2. 更新商户端的订单状态
        iOrderInfoService.updateOrderStatus(orderNo,OrderStatus.CANCEL);
    }

    /**
     * 退款
     * @param orderNo 退款编号
     * @param reason 退款理由
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void refund(String orderNo, String reason) throws IOException {
        log.info("*************  创建退款单 ******");
        // 1. 根据订单编号创建退款单
        RefundInfo refundInfo = iRefundInfoService.createRefundsByOrderNo(orderNo, reason);

        log.info("************ 正在调用微信支付申请退款API ********");
        // 2. 调用申请退款
        HttpPost httpPost = new HttpPost(wxPayConfig.getDomain().concat(WxApiType.DOMESTIC_REFUNDS.getType()));

        // 3. 组装请求参数
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("out_trade_no", refundInfo.getOrderNo());// 订单编号
        paramsMap.put("out_refund_no", refundInfo.getRefundNo());// 商户退款单号
        paramsMap.put("reason", reason);// 退款原因
        paramsMap.put("notify_url", wxPayConfig.getNotifyDomain().concat(WxNotifyType.REFUND_NOTIFY.getType()));// 退款结果回调url

        Map<String, Object> amountMap = new HashMap<>();
        amountMap.put("refund", refundInfo.getRefund());// 退款金额
        amountMap.put("total", refundInfo.getTotalFee());// 原始订单金额
        amountMap.put("currency", "CNY");// 退款金额
        paramsMap.put("amount", amountMap);// 金额

        // 4. 将参数转换为json字符串
        String jsonString = JSON.toJSONString(paramsMap);
        log.info("申请退款参数=======>{}" + jsonString);

        // 5. 准备消息 boby
        StringEntity entity = new StringEntity(jsonString, "UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        // 6. 准备请求头
        httpPost.setHeader("Accept", "application/json");

        // 7.完成签名并执行请求
        CloseableHttpResponse response = wxPayClient.execute(httpPost);

        try {
            // 8. 获取响应体body
            String bodyString = EntityUtils.toString(response.getEntity());
            // 9. 获取状态码
            int statusCode = response.getStatusLine().getStatusCode();
            // 10. 判断状态码
            if (statusCode == 200){
                log.info("退款成功 =>{}",bodyString);
            }else if (statusCode == 204){// 处理成功  没有body
                log.info("退款成功 =>{}",bodyString);
            }else {
                log.info("申请退款接口失败"+statusCode + "返回结果 " + bodyString);
                throw  new IOException("request failed");
            }
            // 11.更新订单状态  支付成功  -》   退款中
            iOrderInfoService.updateOrderStatus(refundInfo.getOrderNo(),OrderStatus.REFUND_PROCESSING);

            // 12. 更新退款单
            iRefundInfoService.updateRefund(bodyString);
        }finally {
            response.close();
        }

    }

    /**
     * 处理退款单
     * @param resultMap
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void processRefunds(Map<String, Object> resultMap) throws GeneralSecurityException {
        log.info("************ 处理退款单 ********");
        // 1. 获取明文
        String plainText = WxVerifierUtils.decrypt(resultMap, wxPayConfig.getApiV3Key());
        // 2. 字符串转为map
        HashMap plainTextMap = JSON.parseObject(plainText, HashMap.class);
        // 3. 获取订单编号
        String orderNo = (String) plainTextMap.get("out_trade_no");
        if (lock.tryLock()){
            try {
                // 4. 根据订单编号查询订单信息
                OrderInfo byOrderNo = iOrderInfoService.findByOrderNo(orderNo);
                // 5. 判断是否已经更新过
                if(!OrderStatus.REFUND_PROCESSING.getType().equals(byOrderNo.getOrderStatus())){
                    return ;
                }
                // 6. 更新订单状态
                iOrderInfoService.updateOrderStatus(byOrderNo.getId(),OrderStatus.REFUND_SUCCESS);
                // 7. 更新退款单状态
                iRefundInfoService.updateRefundStatus(plainText);
            }finally {
                lock.unlock();//主动释放锁
            }
        }
    }


    /**
     * 根据订单编号修改微信后台的订单状态
     * @param orderNo
     */
    private void  closeOrder(String orderNo) throws IOException {
        log.info("************  关单接口调用 **********");
        // 1. 组装请求url地址
        String url = String.format(wxPayConfig.getDomain().concat(WxApiType.CLOSE_ORDER_BY_NO.getType()), orderNo);
        // 2. 获取HTTPpost
        HttpPost httpPost = new HttpPost(url);
        // 3. 组装参数
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("mchid", wxPayConfig.getMchId());
        // 4. 将参数转换为json字符串
        String jsonString = JSON.toJSONString(paramsMap);
        // 5. 准备消息 boby
        StringEntity entity = new StringEntity(jsonString, "UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        // 6. 准备请求头
        httpPost.setHeader("Accept", "application/json");
        // 7.完成签名并执行请求
        CloseableHttpResponse response = wxPayClient.execute(httpPost);
        // 8. 获取状态码
        try {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200){// 处理成功
                log.info("关单成功");
            }else if(statusCode == 204){
                log.info("关单成功");
            }else {
                log.error("关单失败，响应码 = "+ statusCode);
                throw new IOException("requset failed");
            }
        }finally {
            response.close();
        }

    }




}




