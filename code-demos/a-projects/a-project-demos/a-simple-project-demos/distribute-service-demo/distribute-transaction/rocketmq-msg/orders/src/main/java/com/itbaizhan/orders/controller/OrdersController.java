package com.itbaizhan.orders.controller;

import com.itbaizhan.orders.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author itbaizhan
 * @since 05-21
 */
@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService iOrderService;

    /**
     * 创建订单
     * @param productId 商品id
     * @param payCount 购买数量
     * @return
     */
    @GetMapping(value = "/saveOrders")
    public String transfer(Long productId,Integer payCount){
        //提交订单
        iOrderService.submitOrders(productId, payCount);
        return "下单成功";
    }

}
