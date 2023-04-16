package com.example.order.service.impl;

import com.example.mybatis.entity.TbOrder;
import com.example.mybatis.mapper.TbOrderMapper;
import com.example.order.service.IOrderManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IOrderManagerServiceImpl implements IOrderManagerService {

    @Autowired
    private TbOrderMapper orderMapper;

    @Override
    public TbOrder queryOrderById(Long orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }
}
