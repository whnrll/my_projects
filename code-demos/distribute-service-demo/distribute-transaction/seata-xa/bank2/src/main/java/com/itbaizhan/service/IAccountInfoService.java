package com.itbaizhan.service;

import com.itbaizhan.entity.AccountInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author itbaizhan
 * @since 05-12
 */
public interface IAccountInfoService extends IService<AccountInfo> {


    /**
     * 李四账户增加金额
     * @param accountNo 银行卡号
     * @param amount 金额
     */
    void updateAccountBalance(String accountNo, Double amount);

}
