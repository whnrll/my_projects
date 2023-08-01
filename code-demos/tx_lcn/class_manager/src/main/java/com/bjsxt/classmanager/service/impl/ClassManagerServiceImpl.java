package com.bjsxt.classmanager.service.impl;

import com.bjsxt.classmanager.mapper.ClassMapper;
import com.bjsxt.classmanager.service.ClassManagerService;
import com.bjsxt.pojo.Classes;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 班级管理服务实现
@Service
public class ClassManagerServiceImpl implements ClassManagerService {
    @Autowired
    private ClassMapper classMapper;
    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    @LcnTransaction
    public Classes createClass(Classes classes) {
        System.out.println("班级管理系统 - 服务实现 - 新增班级前 ： " + classes);
        // 使用系统当前时间作为主键。
        classes.setId(System.nanoTime());
        int rows = classMapper.insertClass(classes);
        if(rows != 1){ // 新增失败。 回滚事务
            throw new RuntimeException("新增班级错误");
        }
        System.out.println("班级管理系统 - 服务实现 - 新增班级后 ： " + classes);
        return classes;
    }

    @Override
    public Classes getClassById(Long id) {
        System.out.println("班级管理系统 - 服务实现 - 主键查询班级 ： " + id);
        return classMapper.selectById(id);
    }
}
