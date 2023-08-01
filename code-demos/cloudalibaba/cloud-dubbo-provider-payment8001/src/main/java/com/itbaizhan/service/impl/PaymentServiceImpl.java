package com.itbaizhan.service.impl;

import com.itbaizhan.service.IPaymentService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Method;

/**
 * 支付业务层
 */
@DubboService(timeout = 5000,methods = {@Method(name = "index",retries = 2)} ,
        cluster = "failfast")
public class PaymentServiceImpl implements IPaymentService {

    public String index() {
        return "hello dubbo";
    }
}
