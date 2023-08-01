package com.bjsxt.studentmanager.service;

import com.bjsxt.pojo.Student;

// 学生管理服务接口
public interface StudentManagerService {
    Student register(Student student);
    Student findById(Long id);
}
