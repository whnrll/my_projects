package com.itbaizhan.account.service;

import com.itbaizhan.account.entity.AccountInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itbaizhan.account.entity.PayInfo;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author itbaizhan
 * @since 05-23
 */
public interface IAccountInfoService extends IService<AccountInfo> {

    /**
     * 更新账户
     */
    void updateAccount(PayInfo payInfo);


    /**
     * 查询交易结果
     *
     * @param txNo
     * @return
     */
    PayInfo queryPayment(String txNo);
}
