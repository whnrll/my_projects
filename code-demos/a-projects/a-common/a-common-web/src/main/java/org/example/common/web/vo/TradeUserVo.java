package org.example.common.web.vo;

import javax.validation.constraints.Size;

import lombok.Data;
import org.example.common.web.vo.base.BaseVo;

/**
 * <p>Description: </p>
 * @date 2020/1/7
 * @author 贺锟 
 * @version 1.0
 * <p>Copyright:Copyright(c)2019</p>
 */
@Data
public class TradeUserVo extends BaseVo {


    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 用户名称
     */
    @Size(min = 1, max = 32,message = "姓名长度必须为1到32")
    private String name;

    /**
     * 用户密码
     */
    @Size(min = 1, max = 32,message = "密码长度必须为1到32")
    private String userPwd;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 公司ID
     */
    private Long companyId;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 地址
     */
    private String address;



}
