package com.example.mybatis.entity;

import lombok.Data;

/**
 * 描述：db_my_springcloud.tb_order
 *
 * @author xutao
 * @date 2023-04-16 18:03:26
 * @since 1.0.0
 */
@Data
public class TbOrder {
    private Long id;

    private Long userId;

    private String name;

    private Long price;

    private Integer num;
}