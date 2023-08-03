package org.example.payment.service;

import org.example.payment.entity.RefundInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author itbaizhan
 * @since 04-21
 */
public interface IRefundInfoService extends IService<RefundInfo> {


    /**
     * 根据订单编号创建退款单
     * @param orderNo 订单编号
     * @param reason 退款理由
     * @return
     */
   RefundInfo  createRefundsByOrderNo(String orderNo,String reason);


    /**
     * 更新退款单
     * @param bodyString
     */
   void  updateRefund(String bodyString);


    /**
     * 更新退款单
     * @param bodyString
     */
    void  updateRefundStatus(String bodyString);


    /**
     * 修改订单状态
     * @param orderNo 订单编号
     */
    void  updateRefundAliPayStatus(String orderNo);


}
