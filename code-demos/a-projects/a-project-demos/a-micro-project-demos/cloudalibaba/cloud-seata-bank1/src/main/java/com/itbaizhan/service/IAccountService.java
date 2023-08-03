package com.itbaizhan.service;

/***
 * 转账接口
 */
public interface IAccountService {

    /**
     * 转账
     * @param accountNo 银行卡号
     * @param amount 转账金额
     */
    void updateAccountBalance(String accountNo, Double amount);


}
