package com.itbaizhan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 支付控制层
 */
@RestController
@RequestMapping("payment")
public class PaymentController {

    @GetMapping("index")
    public String index(){
        try {
            TimeUnit.SECONDS.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "payment success";
    }

    /**
     * 根据id查询支付信息
     * @param id
     * @return
     */
    @GetMapping("findById")
    public String findById(String id){
        return "payment info :" + id;
    }
}
