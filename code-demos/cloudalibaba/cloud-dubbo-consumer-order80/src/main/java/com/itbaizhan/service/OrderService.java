package com.itbaizhan.service;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单业务层
 */
@Service
public class OrderService {


    //支付服务远程调用
    @DubboReference(mock = "com.itbaizhan.service.fallback.PaymentServiceFallback")
    private IPaymentService iPaymentService;


    public String index(){
        return iPaymentService.index();
    }

}
