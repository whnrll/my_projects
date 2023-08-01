package com.bjsxt.studentmanager.controller;

import com.bjsxt.pojo.Student;
import com.bjsxt.studentmanager.service.StudentManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentManagerController {
    @Autowired
    private StudentManagerService studentManagerService;

    @RequestMapping("/createStudent")
    public Student createStudent(Student student){
        System.out.println("学生管理 - 控制器 - 创建学生 ： " + student);
        return studentManagerService.register(student);
    }

    @RequestMapping("/findById")
    public Student findById(Long id){
        System.out.println("学生管理 - 控制器 - 主键查询学生 ： " + id);
        return studentManagerService.findById(id);
    }
}
