package org.example.payment.controller;

import org.example.payment.controller.dto.OrderInfoDTO;
import org.example.payment.vo.BaseResult;
import org.example.payment.vo.CodeEnum;
import org.example.payment.entity.OrderInfo;
import org.example.payment.service.IOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  订单接口
 * </p>
 *
 * @author itbaizhan
 * @since 04-21
 */
@RestController
@RequestMapping("/api/order")
public class OrderInfoController {


    @Autowired
    private IOrderInfoService iOrderInfoService;// 订单接口

    /**
     * 创建订单
     * @return
     */
    @PostMapping("/createOrder")
    public BaseResult createOrder(@RequestBody OrderInfoDTO orderInfoDTO){
        if (orderInfoDTO.getTotalFee() == null || orderInfoDTO.getTitle() == null){
            return BaseResult.error(CodeEnum.PARAMETER_ERROR);
        }
        OrderInfo order = iOrderInfoService.createOrder(orderInfoDTO);
        return BaseResult.ok(order);
    }

    /**
     * 查询全部订单
     * @return
     */
    @GetMapping("/findAll")
    public BaseResult findAll(){
        List<OrderInfo> orders = iOrderInfoService.findAll();
        return BaseResult.ok(orders);
    }

    /**
     * 根据订单编号查询订单
     * @param orderNo
     * @return
     */
    @GetMapping("/findByOrderNo/{orderNo}")
    public BaseResult createOrder(@PathVariable String orderNo){
        OrderInfo order = iOrderInfoService.findByOrderNo(orderNo);
        return BaseResult.ok(order);
    }
}
