package org.example.payment.zhifubao.service.fallback;

import org.example.payment.zhifubao.service.IPaymentService;

/**
 * 支付服务降级处理
 */
public class PaymentServiceFallback implements IPaymentService {
    public String index() {
        return "服务繁忙请稍后再试~~~~~";
    }
}
