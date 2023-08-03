package org.example.payment.zhifubao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 账户实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("account_info")
public class AccountInfo {

    //id
    @TableId
    private Long id;
    //户主姓名
    @TableField("account_name")
    private String accountName;
    //银行卡号
    @TableField("account_no")
    private String accountNo;
    //账户密码
    @TableField("account_password")
    private String accountPassword;
    //账户余额
    @TableField("account_balance")
    private Double accountBalance;



}
