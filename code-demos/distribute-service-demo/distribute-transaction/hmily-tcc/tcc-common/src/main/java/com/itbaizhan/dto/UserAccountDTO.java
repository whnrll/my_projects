package com.itbaizhan.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用户账户DTO类
 */
@Data
public class UserAccountDTO implements Serializable {

    // 自定义事务ID
    private String txNo;
    // 转出账号
    private String sourceAccountNo;
    // 转入账号
    private String targetAccountNo;
    // 金额
    private BigDecimal bigDecimal;

}
