package org.example.payment.zhifubao.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.example.payment.zhifubao.service.PaymentService;
import com.sun.deploy.security.BlockedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 支付控制层 主要练习链路
 */
@RestController
@RequestMapping("payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    /**
     * 添加订单
     * @return
     */
    @GetMapping("save")
    public String save(){
        paymentService.findByGoods();
        return "添加商品";
    }

    /**
     * 查询订单订单
     * @return
     */
    @GetMapping("query")
    public String query(){
         paymentService.findByGoods();
        return "查询商品";
    }
    /**
     * 冷启动测试
     * @return
     */
    @GetMapping("warmup")
    public String warmup(){
        return "warmup";
    }

    /**
     * 排队等待测试
     * @return
     */
    @GetMapping("queue")
    public String queue(){
        return "queue";
    }



    
}
