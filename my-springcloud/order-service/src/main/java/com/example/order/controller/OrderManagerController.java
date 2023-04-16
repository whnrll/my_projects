package com.example.order.controller;

import javax.validation.constraints.NotNull;

import com.exaple.common.vo.ResultResponse;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mybatis.entity.TbOrder;
import com.example.order.service.IOrderManagerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@RequestMapping("/manager")
@RestController
public class OrderManagerController {

    @Autowired
    private IOrderManagerService orderService;

    @GetMapping("/ok")
    public String ok() {
        return "ok";
    }

    @GetMapping("/query/{orderId}")
    public ResultResponse<TbOrder> query(
        @PathVariable("orderId") @NotNull @Range(min = 101, max = 200, message = "订单编号不存在，范围：{min}-{max}") Long orderId) {
        // 根据id查询订单并返回
        return ResultResponse.success(orderService.queryOrderById(orderId));
    }

}
