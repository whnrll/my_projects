package com.example.mybatis.entity;

import lombok.Data;

/**
 * 描述：db_my_springcloud.tb_user
 *
 * @author xutao
 * @date 2023-04-16 18:03:50
 * @since 1.0.0
 */
@Data
public class TbUser {
    private Long id;

    private String username;

    private String address;
}