package com.exaple.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 描述：用户名和密码
 *
 * @author xutao
 * @Date 2023-02-28 21:07:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsernameAndPassword {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
