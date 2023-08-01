package com.bjsxt.classmanager.controller;

import com.bjsxt.classmanager.service.ClassManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bjsxt.pojo.Classes;

@RestController
public class ClassManagerController {
    @Autowired
    private ClassManagerService classManagerService;
    // 创建班级
    @RequestMapping("/createClass")
    public Classes createClass(Classes classes){
        System.out.println("班级管理系统 - 创建班级 ： " + classes);
        return classManagerService.createClass(classes);
    }
    // 根据主键查询班级。
    @RequestMapping("/getClassById")
    public Classes getClassById(Long id){
        System.out.println("班级管理系统 - 主键查询班级 ： " + id);
        return classManagerService.getClassById(id);
    }
}
