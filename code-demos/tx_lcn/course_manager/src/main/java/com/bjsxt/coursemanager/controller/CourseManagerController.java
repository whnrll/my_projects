package com.bjsxt.coursemanager.controller;

import com.bjsxt.coursemanager.service.CourseManagerService;
import com.bjsxt.pojo.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseManagerController {
    @Autowired
    private CourseManagerService service;
    @RequestMapping("/createCourse")
    public Course createCourse(Course course){
        return  service.createCourse(course);
    }
}
