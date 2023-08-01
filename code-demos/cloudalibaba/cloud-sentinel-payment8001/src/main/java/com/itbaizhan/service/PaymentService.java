package com.itbaizhan.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    /**
     * 查询商品
     * @return
     */
    @SentinelResource("goods")
    public String findByGoods(){
        return "查询商品";
    }



}
