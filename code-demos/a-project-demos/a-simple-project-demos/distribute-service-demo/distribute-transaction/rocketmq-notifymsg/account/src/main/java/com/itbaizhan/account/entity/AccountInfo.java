package com.itbaizhan.account.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author itbaizhan
 * @since 05-23
 */
@Getter
@Setter
@TableName("account_info")
public class AccountInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId
      private Integer id;

    /**
     * 账户
     */
    private String accountNo;

    /**
     * 账户名
     */
    private String accountName;

    /**
     * 账户余额
     */
    private BigDecimal accountBalance;


}
