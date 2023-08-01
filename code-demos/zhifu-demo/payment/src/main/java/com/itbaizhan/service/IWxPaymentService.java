package com.itbaizhan.service;

import com.itbaizhan.vo.BaseResult;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信支付接口
 */
public interface IWxPaymentService {

    /**
     * 根据订单编号进行Native下单
     * @param orderNo
     * @return code_url
     */
    BaseResult nativePay(String orderNo) throws Exception;


    /**
     * 修改订单状态
     * @param bodyMap
     */
    void updateOrderStatus(Map<String,Object> bodyMap) throws GeneralSecurityException;


    /**
     * 根据订单编号查询订单信息
     * @param orderNo
     * @return
     */
    String queryOrder(String orderNo) throws IOException;


    /**
     * 检查订单状态
     * @param orderNo 订单编号
     */
    void checkOrderStatus(String orderNo) throws IOException;


    /**
     * 根据订单编号关闭订单
     * @param orderNo
     */
    void cancelOrder(String orderNo) throws IOException;


    /**
     * 退款
     * @param orderNo 退款编号
     * @param reason 退款理由
     */
    void refund(String orderNo,String reason) throws IOException;


    /**
     * 处理退款
     * @param resultMap
     */
    void processRefunds(Map<String, Object> resultMap) throws GeneralSecurityException;

}
