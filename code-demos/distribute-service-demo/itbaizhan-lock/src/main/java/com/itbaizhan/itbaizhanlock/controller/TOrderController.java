package com.itbaizhan.itbaizhanlock.controller;

import com.itbaizhan.itbaizhanlock.service.ITOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author itbaizhan
 * @since 05-26
 */
@RestController
@RequestMapping("/order")
public class TOrderController {

    @Autowired
    private ITOrderService itOrderService;

    /**
     * 创建订单
     * @param produceId 商品id
     * @param purchaseNum 购买数量
     * @return
     */
    @PostMapping("/create")
    public String creatOrder(Integer produceId,Integer purchaseNum) throws Exception {
        String order = itOrderService.createOrderZookeeper(produceId, purchaseNum);
        return order;
    }



}
