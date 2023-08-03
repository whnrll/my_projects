package com.itbaizhan.payment.service;

import com.itbaizhan.payment.entity.PayInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author itbaizhan
 * @since 05-23
 */
public interface IPayInfoService extends IService<PayInfo> {


    /**
     * 保存充值记录
     * @param accountNo 账户编号
     * @param payAmount 金额
     */
    PayInfo savePayment(String accountNo,BigDecimal payAmount);


    /**
     * 查询指定的充值信息
     * @param txNo 交易流水
     * @return
     */
    PayInfo getById(String txNo);

}
