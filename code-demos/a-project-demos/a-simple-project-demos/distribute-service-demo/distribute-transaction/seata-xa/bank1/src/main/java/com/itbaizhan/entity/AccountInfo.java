package org.example.payment.zhifubao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author itbaizhan
 * @since 05-12
 */
@Getter
@Setter
@TableName("account_info")
public class AccountInfo implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 户主姓名
     */
    private String accountName;

    /**
     * 银行卡号
     */
    private String accountNo;

    /**
     * 帐户密码
     */
    private String accountPassword;

    /**
     * 帐户余额
     */
    private Double accountBalance;


}
