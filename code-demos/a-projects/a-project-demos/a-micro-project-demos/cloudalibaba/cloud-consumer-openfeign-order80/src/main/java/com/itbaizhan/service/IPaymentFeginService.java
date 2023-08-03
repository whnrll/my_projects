package com.itbaizhan.service;


import com.itbaizhan.service.fallback.PaymentServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 远程调用
 */
@Service
//@FeignClient(value = "payment-provider",fallback = PaymentServiceFallback.class)
@FeignClient(value = "payment-provider")
public interface IPaymentFeginService {


     @GetMapping("/payment/index")
     String index();

    /**
     * 根据id查询支付信息
     * @param id
     * @return
     */
    @GetMapping("/payment/findById")
     String findById(@RequestParam("id") String id);

}
