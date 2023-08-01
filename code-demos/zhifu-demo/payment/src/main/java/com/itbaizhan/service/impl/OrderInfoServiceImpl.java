package com.itbaizhan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.itbaizhan.controller.dto.OrderInfoDTO;
import com.itbaizhan.entity.OrderInfo;
import com.itbaizhan.enums.OrderStatus;
import com.itbaizhan.mapper.OrderInfoMapper;
import com.itbaizhan.service.IOrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  订单业务层
 * </p>
 *
 * @author itbaizhan
 * @since 04-21
 */
@Slf4j
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {


    /**
     * 实现订单
     * @param orderInfoDTO
     * @return
     */
    @Override
    public OrderInfo createOrder(OrderInfoDTO orderInfoDTO) {
        log.info("************ 创建订单 *********");
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setTitle(orderInfoDTO.getTitle());
        // 订单编号
        orderInfo.setOrderNo(String.valueOf(System.currentTimeMillis()));
        // 用户id
        orderInfo.setUserId(123456L);
        // 金额 分   1 = 100
        orderInfo.setTotalFee(orderInfoDTO.getTotalFee());
        // 订单状态
        orderInfo.setOrderStatus(OrderStatus.NOTPAY.getType());
        // 创建时间
        orderInfo.setCreateTime(LocalDateTime.now());

        int insert = baseMapper.insert(orderInfo);
        if (insert > 0 ){
            log.info("*********** 订单创建成功");
        }
        return orderInfo;
    }

    /**
     * 根据订单编号查询订单信息
     * @param orderNo
     * @return
     */
    @Override
    public OrderInfo findByOrderNo(String orderNo) {
        LambdaQueryWrapper<OrderInfo > lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(OrderInfo::getOrderNo,orderNo);
        List<OrderInfo> orderInfos = baseMapper.selectList(lambdaQueryWrapper);
        if (orderInfos != null && !orderInfos.isEmpty()){
            return orderInfos.get(0);

        }
        return null;
    }

    /**
     * 更新codeurl
     * @param id id
     * @param codeUrl 二维码
     */
    @Override
    public void saveCodeUrl(Long id, String codeUrl) {
        UpdateWrapper<OrderInfo> updateWrapper = new UpdateWrapper<>();
        // 设置要更新的字段 key = db属性
        updateWrapper.set("code_url",codeUrl);
        //条件
        updateWrapper.eq("id",id);
        baseMapper.update(null,updateWrapper);
    }

    /**
     * 根据订单id更新订单状态
     * @param id 订单id
     * @param orderStatus 订单状态
     */
    @Override
    public void updateOrderStatus(Long id, OrderStatus orderStatus) {
        LambdaUpdateWrapper<OrderInfo> lo = new LambdaUpdateWrapper<>();
        lo.eq(OrderInfo::getId,id);
        lo.set(OrderInfo::getOrderStatus,orderStatus.getType());
        baseMapper.update(null,lo);
    }

    @Override
    public void updateOrderStatus(String orderNo, OrderStatus orderStatus) {
        LambdaUpdateWrapper<OrderInfo> lo = new LambdaUpdateWrapper<>();
        lo.eq(OrderInfo::getOrderNo,orderNo);
        lo.set(OrderInfo::getOrderStatus,orderStatus.getType());
        baseMapper.update(null,lo);
    }

    /**
     * 超时订单
     * @param minutes 分钟
     * @return
     */
    @Override
    public List<OrderInfo> getTimeOutOrder(int minutes) {
        // 1. 获取5分钟之前的时间
        Instant instant = Instant.now().minus(Duration.ofMinutes(5));
        // 2. 查询条件构造器
        LambdaQueryWrapper<OrderInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 3. 订单类型
        lambdaQueryWrapper.eq(OrderInfo::getOrderStatus,OrderStatus.NOTPAY.getType());
        // 4. 小于订单创建时间
        lambdaQueryWrapper.le(OrderInfo::getCreateTime,instant);
        // 5. 查询
        List<OrderInfo> orderInfos = baseMapper.selectList(lambdaQueryWrapper);
        return orderInfos;

    }

    @Override
    public List<OrderInfo> findAll() {
        return baseMapper.selectList(null);
    }

}
