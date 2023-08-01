package com.itbaizhan.itbaizhanlock.service;

import com.itbaizhan.itbaizhanlock.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author itbaizhan
 * @since 05-26
 */
public interface ITOrderService extends IService<TOrder> {


    /**
     * 创建订单
     * @param produceId 商品id
     * @param purchaseNum 购买数量
     * @return
     */
    String createOrder(Integer produceId,Integer purchaseNum);


    /**
     * 创建订单 （悲观锁）
     * @param produceId
     * @param purchaseNum
     * @return
     */
    String  createOrderPessimisticlock(Integer produceId,Integer purchaseNum);


    /**
     * 创建订单 （乐观锁）
     * @param produceId
     * @param purchaseNum
     * @return
     */
    String  createOrderOptimisticlock(Integer produceId,Integer purchaseNum);



    /**
     * 创建订单  基于redis分布式锁
     * @param produceId
     * @param purchaseNum
     * @return
     */
    String  createOrderRedis(Integer produceId,Integer purchaseNum);

    /**
     * 创建订单  基于Redisson分布式锁
     * @param produceId
     * @param purchaseNum
     * @return
     */
    String  createOrderRedission(Integer produceId,Integer purchaseNum);



    /**
     * 创建订单  基于zookeeper分布式锁
     * @param produceId
     * @param purchaseNum
     * @return
     */
    String  createOrderZookeeper(Integer produceId,Integer purchaseNum) throws Exception;


}
