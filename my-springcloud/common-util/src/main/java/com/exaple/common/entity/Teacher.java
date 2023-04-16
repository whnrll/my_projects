package com.exaple.common.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述：老师
 *
 * @author xutao
 * @Date 2023-02-26 23:17:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    private Integer id;

    private String name;

    private List<Student> students;
}
