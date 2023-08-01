package com.itbaizhan.service;

import com.itbaizhan.entity.PaymentInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author itbaizhan
 * @since 04-21
 */
public interface IPaymentInfoService extends IService<PaymentInfo> {


    /**
     * 添加交易记录
     * @param plainTextMap
     */
    void  createPaymentInfo(Map<String,Object> plainTextMap);
}
