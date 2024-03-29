package com.itbaizhan.sonwflake.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {

    @TableId(type = IdType.ASSIGN_ID)// 雪花算法
    private Long id;

    private String name;

    private Integer age;

    private String email;
}
