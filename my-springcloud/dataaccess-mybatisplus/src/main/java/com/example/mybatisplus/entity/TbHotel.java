package com.example.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 描述：tb_hotel
 *
 * @author xutao
 * @date 2023-04-22 00:30:45
 * @since 1.0.0
 */
@Data
@TableName("tb_hotel")
public class TbHotel {
    @TableId(type = IdType.INPUT)
    private Long id;

    private String name;

    private String address;

    private Integer price;

    private Integer score;

    private String brand;

    private String city;

    private String starName;

    private String business;

    private String latitude;

    private String longitude;

    private String pic;
}