package org.example.payment.service;

import org.example.payment.controller.dto.OrderInfoDTO;
import org.example.payment.entity.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.payment.enums.OrderStatus;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author itbaizhan
 * @since 04-21
 */
public interface IOrderInfoService extends IService<OrderInfo> {


    /**
     * 创建订单
     * @param orderInfoDTO
     * @return
     */
    OrderInfo createOrder(OrderInfoDTO orderInfoDTO);


    /**
     * 根据订单编号查询订单信息
     * @param orderNo
     * @return
     */
    OrderInfo findByOrderNo(String orderNo);


    /**
     *
     * @param id 订单id
     * @param codeUrl 二维码
     */
    void saveCodeUrl(Long id,String codeUrl);


    /**
     * 根据订单id修改订单状态
     * @param id 订单id
     * @param orderStatus 订单状态
     */
    void updateOrderStatus(Long id,OrderStatus orderStatus);


    /**
     * 根据订单编号修改订单状态
     * @param orderNo 订单编号
     * @param orderStatus 订单状态
     */
    void updateOrderStatus(String  orderNo,OrderStatus orderStatus);
    /**
     * 查询超时订单
     * @param minutes 分钟
     * @return
     */
    List<OrderInfo> getTimeOutOrder(int minutes);


    List<OrderInfo> findAll();

}
