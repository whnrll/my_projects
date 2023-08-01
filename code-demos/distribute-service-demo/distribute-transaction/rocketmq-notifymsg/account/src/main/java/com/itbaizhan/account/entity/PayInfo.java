package com.itbaizhan.account.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
@TableName("pay_info")
public class PayInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 充值记录流水号
     */
    @TableId
      private String txNo;

    /**
     * 账户
     */
    private String accountNo;

    /**
     * 充值金额
     */
    private BigDecimal payAmount;

    /**
     * 充值结果
     */
    private String payResult;

    /**
     * 充值时间
     */
    private LocalDateTime payTime;


}
