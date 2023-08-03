package org.example.payment.zhifubao.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 账户信息
 * </p>
 *
 * @author itbaizhan
 * @since 05-15
 */
@Getter
@Setter
@TableName("user_account")
public class UserAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账户编号
     */
    @TableId
      private String accountNo;

    /**
     * 账户名称
     */
    private String accountName;

    /**
     * 账户余额
     */
    private BigDecimal accountBalance;

    /**
     * 转账金额
     */
    private BigDecimal transferAmount;


}
