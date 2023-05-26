package com.example.order.service;

import com.example.mybatis.entity.TbOrder;

/**
 * 描述：订单管理服务
 *
 * @author xutao
 * @date 2023-04-16 21:33:23
 * @since 1.0.0
 */
public interface IOrderManagerService {

    /**
     * 描述：查询订单
     *
     * @param orderId 订单 id
     * @return {@link TbOrder }
     */
    TbOrder queryOrderById(Long orderId);
}
