package com.bjsxt.order.controller;

import com.bjsxt.order.service.OrderService;
import com.bjsxt.pojo.TbOrder;
import com.bjsxt.pojo.TbOrderItem;
import com.bjsxt.pojo.TbOrderShipping;
import com.bjsxt.utils.Result;
import io.protostuff.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 订单服务Controller
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    /**
     * 创建订单
     */
    @RequestMapping("/insertOrder")
    public Result insertOrder(String orderItem, TbOrder tbOrder , TbOrderShipping tbOrderShipping){
        try{
            Result res = Result.formatObjectToList(orderItem, TbOrderItem.class);
            List<TbOrderItem> list = (List<TbOrderItem>) res.getData();
            return this.orderService.insertOrder(list,tbOrder,tbOrderShipping);
        }catch(Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }
}
