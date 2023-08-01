package com.bjsxt.classmanager.service;

import com.bjsxt.pojo.Classes;

// 班级管理服务接口
public interface ClassManagerService {
    Classes createClass(Classes classes);
    Classes getClassById(Long id);
}
