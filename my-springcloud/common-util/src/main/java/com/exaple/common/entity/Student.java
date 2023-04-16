package com.exaple.common.entity;

import java.util.Date;


import com.exaple.common.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述：学生
 *
 * @author xutao
 * @Date 2023-02-26 23:14:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // 忽略反序列化时，json 里存在而 bean 里面未定义的字段导致的错误
public class Student {
    private String no;

    private String name;

    private Date birth;

    private Gender gender;
}
