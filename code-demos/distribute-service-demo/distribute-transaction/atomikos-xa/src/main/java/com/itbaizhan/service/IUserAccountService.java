package com.itbaizhan.service;


import java.math.BigDecimal;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author itbaizhan
 * @since 05-13
 */
public interface IUserAccountService  {


    /**
     * 跨库转账
     * @param sourceAccountNo 转出账户
     * @param targetSourceNo 转入账户
     * @param bigDecimal 金额
     */
    void transofer(String sourceAccountNo, String targetSourceNo, BigDecimal bigDecimal);

}
