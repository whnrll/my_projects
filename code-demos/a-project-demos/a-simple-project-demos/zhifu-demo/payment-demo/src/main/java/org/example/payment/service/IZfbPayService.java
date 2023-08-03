package org.example.payment.service;

import org.example.payment.vo.BaseResult;

import javax.servlet.http.HttpServletRequest;

/**
 * 支付宝接口
 */
public interface IZfbPayService {


    /**
     * 统一下单
     * @param orderNo
     * @return
     */
    BaseResult pcPay(String orderNo);


    /**
     * 修改订单状态
     * @param request
     */
    void updateOrderStatus(HttpServletRequest request);


    /**
     * 支付宝退款
     * @param orderNo
     * @param reason
     */
    void refund(String orderNo,String reason);


    /**
     * 根据订单编号查询交易状态
     * @param orderNo
     * @return
     */
    String queryPayStauts(String orderNo) throws Exception;

}
