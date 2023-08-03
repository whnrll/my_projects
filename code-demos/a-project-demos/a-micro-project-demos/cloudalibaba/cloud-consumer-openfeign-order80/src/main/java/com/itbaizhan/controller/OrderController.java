package org.example.payment.zhifubao.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.example.payment.zhifubao.service.IPaymentFeginService;
import org.example.payment.zhifubao.service.fallback.PaymentServiceFallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 订单微服务
 */
@Slf4j
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private IPaymentFeginService iPaymentFeginService;

    /**
     * 测试openfeign
     * @return
     */
    @GetMapping("index")
    public String index(){
        return iPaymentFeginService.index();
    }


    /**
     * 根据id查询支付信息
     * 测试fallback
     * @param id
     * @return
     */
    @SentinelResource(value = "orderfindById",
            blockHandler = "testBlockHeader",blockHandlerClass = PaymentServiceFallback.class,
            fallback = "testFallBack",fallbackClass = PaymentServiceFallback.class)
    @GetMapping("findById")
    public String test(String id){
        if (id.equals("1")){
            throw  new RuntimeException("制造接口异常.");
        }

        return iPaymentFeginService.findById(id);
    }


//    // 接口异常降级方法
//    public String testFallBack(String id,Throwable e){
//        return "接口异常降级~";
//    }
//

    // Sentinel控制台配置异常降级方法
//    public String testBlockHeader(String id, BlockException e){
//        return "sentinel控制配置异常降级~";
//    }
}
