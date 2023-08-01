package com.itbaizhan.service.impl;

import com.alibaba.fastjson.JSON;
import com.itbaizhan.entity.PaymentInfo;
import com.itbaizhan.enums.PayType;
import com.itbaizhan.mapper.PaymentInfoMapper;
import com.itbaizhan.service.IPaymentInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author itbaizhan
 * @since 04-21
 */
@Slf4j
@Service
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoMapper, PaymentInfo> implements IPaymentInfoService {


    /**
     * 添加交易记录
     * @param plainTextMap
     */
    @Override
    public void createPaymentInfo(Map<String, Object> plainTextMap) {
        log.info("***********  记录交易日志 ************");
        // 1. 获取订单号
        String  orderNo = (String) plainTextMap.get("out_trade_no");
        // 2. 微信支付页面编号
        String  transactionId = (String) plainTextMap.get("transaction_id");
        // 3. 交易类型
        String  tradeType = (String) plainTextMap.get("trade_type");
        // 4. 交易状态
        String  tradeState = (String) plainTextMap.get("trade_state");
        // 5. 获取用户支付金额
        Map<String,Object>  amount = (Map<String, Object>) plainTextMap.get("amount");
        Integer payer_total = (Integer) amount.get("payer_total");

        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setOrderNo(orderNo);
        paymentInfo.setTransactionId(transactionId);
        // 微信  支付宝
        paymentInfo.setPaymentType(PayType.WXPAY.getType());
        paymentInfo.setTradeType(tradeType);
        paymentInfo.setTradeState(tradeState);
        paymentInfo.setPayerTotal(payer_total);
        paymentInfo.setContent(JSON.toJSONString(plainTextMap));
        paymentInfo.setCreateTime(LocalDateTime.now());
        baseMapper.insert(paymentInfo);
    }
}
