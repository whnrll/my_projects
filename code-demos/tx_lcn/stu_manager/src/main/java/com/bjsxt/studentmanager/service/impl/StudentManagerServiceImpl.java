package com.bjsxt.studentmanager.service.impl;

import com.bjsxt.pojo.Classes;
import com.bjsxt.pojo.Course;
import com.bjsxt.pojo.Student;
import com.bjsxt.studentmanager.feignclient.ClassManagerFeignClient;
import com.bjsxt.studentmanager.feignclient.CourseManagerFeignClient;
import com.bjsxt.studentmanager.mapper.StudentMapper;
import com.bjsxt.studentmanager.service.StudentManagerService;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Service
public class StudentManagerServiceImpl implements StudentManagerService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ClassManagerFeignClient classManagerFeignClient;
    @Autowired
    private CourseManagerFeignClient courseManagerFeignClient;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    @LcnTransaction
    public Student register(Student student) {
        // 远程服务调用。 如果要新增的学生对象中，班级主键cid为null，需要创建一个班级。
        // 班级的名称为新建班级+系统当前时间（yyyy-mm-dd）
        if(null == student.getCid()) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String className = "新建班级-" + sdf.format(date);
            Classes classes = classManagerFeignClient.createClass(className, System.nanoTime());
            student.setCid(classes.getId());
        }
        System.out.println("学生管理 - 新增学生 - 新增之前 ： " + student);
        // 设置主键
        student.setId(System.nanoTime());

        int rows = studentMapper.insertStudent(student);

        if(rows != 1){
            throw new RuntimeException("新增学生错误");
        }

        System.out.println("学生管理 - 新增学生 - 新增之后 ： " + student);

        // 新增学生的时候，给学生自动增加一门必修课。
        Course course = courseManagerFeignClient.createCourse("中国特色社会主义");
        if(course == null){
            // 有异常。
            throw new RuntimeException("新增课程出现错误");
        }

        // 模拟新增学生异常。
        int r = new Random().nextInt(1000) % 2;
        if(r == 1){
            throw new RuntimeException("测试新增学生错误，班级是否回滚，课程是否回滚");
        }

        return student;
    }

    @Override
    public Student findById(Long id) {
        System.out.println("学生管理 - 服务实现 - 主键查询学生 ： " + id);
        return studentMapper.selectById(id);
    }
}
