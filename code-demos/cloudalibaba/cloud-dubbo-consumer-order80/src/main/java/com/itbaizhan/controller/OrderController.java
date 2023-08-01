package com.itbaizhan.controller;

import com.itbaizhan.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单控制层
 */
@RestController
@RequestMapping("order")
public class OrderController {


    @Autowired
    private OrderService orderService;

    /**
     * 测试dubbo远程调用
     * @return
     */
    @GetMapping("index")
    public String index(){
       return  orderService.index();
    }


}
