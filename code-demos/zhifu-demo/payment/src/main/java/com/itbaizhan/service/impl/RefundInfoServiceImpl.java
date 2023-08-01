package com.itbaizhan.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.itbaizhan.entity.OrderInfo;
import com.itbaizhan.entity.RefundInfo;
import com.itbaizhan.enums.OrderStatus;
import com.itbaizhan.mapper.RefundInfoMapper;
import com.itbaizhan.service.IOrderInfoService;
import com.itbaizhan.service.IRefundInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author itbaizhan
 * @since 04-21
 */
@Service
public class RefundInfoServiceImpl extends ServiceImpl<RefundInfoMapper, RefundInfo> implements IRefundInfoService {

    @Autowired
    IOrderInfoService iOrderInfoService;

    /**
     * 创建退款单
     * @param orderNo 订单编号
     * @param reason 退款理由
     * @return
     */
    @Override
    public RefundInfo createRefundsByOrderNo(String orderNo, String reason) {

        // 1. 根据订单编号查询订单信息
        OrderInfo orderInfo = iOrderInfoService.findByOrderNo(orderNo);
        // 2. 根据订单号生成退款单
        RefundInfo refundInfo = new RefundInfo();
        // 3. 订单编号
        refundInfo.setOrderNo(orderInfo.getOrderNo());
        // 4. 退款编号
        refundInfo.setRefundNo(String.valueOf(System.currentTimeMillis()));
        // 5. 原订单金额（分）
        refundInfo.setTotalFee(orderInfo.getTotalFee());
        // 6. 退款金额
        refundInfo.setRefund(orderInfo.getTotalFee());
        // 7. 退款原因
        refundInfo.setReason(reason);
        baseMapper.insert(refundInfo);
        return refundInfo;
    }

    /**
     * 更新退款单
     * @param bodyString 微信返回的退款json数据
     */
    @Override
    public void updateRefund(String bodyString) {

        // 1. json转 Map
        Map resultMap = JSON.parseObject(bodyString, HashMap.class);
        // 2. 获取退款状态
        String  status = (String) resultMap.get("status");
        // 3. 获取微信支付退款单号
        String  refundId = (String) resultMap.get("refund_id");
        // 4. 获取商户退款单号
        String  refundNo = (String) resultMap.get("out_refund_no");

        LambdaUpdateWrapper<RefundInfo> refundInfoLambd = new LambdaUpdateWrapper<>();
        refundInfoLambd.set(RefundInfo::getRefundStatus,status);
        refundInfoLambd.set(RefundInfo::getRefundId,refundId);
        refundInfoLambd.set(RefundInfo::getContentReturn,bodyString);
        refundInfoLambd.eq(RefundInfo::getRefundNo,refundNo);
        baseMapper.update(null,refundInfoLambd);
    }

    /**
     * 更新退款单状态
     * @param bodyString
     */
    @Override
    public void updateRefundStatus(String bodyString) {
        // 1. json转 Map
        Map resultMap = JSON.parseObject(bodyString, HashMap.class);
        // 2. 获取退款状态
        String  status = (String) resultMap.get("refund_status");
        // 3. 获取商户退款单号
        String  refundNo = (String) resultMap.get("out_refund_no");
        LambdaUpdateWrapper<RefundInfo> refundInfoLambd = new LambdaUpdateWrapper<>();
        refundInfoLambd.set(RefundInfo::getRefundStatus,status);
        refundInfoLambd.eq(RefundInfo::getRefundNo,refundNo);
        baseMapper.update(null,refundInfoLambd);
    }

    /**
     * 修改订单状态
     * @param orderNo 订单编号
     */
    @Override
    public void updateRefundAliPayStatus(String orderNo) {
        LambdaUpdateWrapper<RefundInfo> refundInfoLambd = new LambdaUpdateWrapper<>();
        refundInfoLambd.set(RefundInfo::getRefundStatus, OrderStatus.REFUND_SUCCESS.getType());
        refundInfoLambd.eq(RefundInfo::getOrderNo,orderNo);
        baseMapper.update(null,refundInfoLambd);
    }

}
