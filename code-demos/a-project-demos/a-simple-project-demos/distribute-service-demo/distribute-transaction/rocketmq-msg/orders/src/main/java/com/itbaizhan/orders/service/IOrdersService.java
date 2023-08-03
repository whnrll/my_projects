package com.itbaizhan.orders.service;

import com.itbaizhan.orders.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itbaizhan.orders.tx.TxMessage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author itbaizhan
 * @since 05-21
 */
public interface IOrdersService extends IService<Orders> {


    /**
     * 提交订单
     * @param productId 商品编号
     * @param payCount 购买数量
     */
    void  submitOrders(Long productId,Integer payCount);


    /**
     * 添加订单
     * @param productId 商品编号
     * @param payCount 购买数量
     */
    void  save(Long productId,Integer payCount);


    /**
     * 提交订单同时解决幂等问题
     */
    void sumbitOrderAndSaveTxNo(TxMessage txMessage);

}
