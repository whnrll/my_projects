package com.bjsxt.studentmanager.feignclient;

import com.bjsxt.pojo.Course;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("lcn-course-manager")
public interface CourseManagerFeignClient {
    @RequestMapping("/createCourse")
    public Course createCourse(@RequestParam("courseName") String courseName);
}
