package com.bjsxt.coursemanager.service.impl;

import com.bjsxt.coursemanager.service.CourseManagerService;
import com.bjsxt.pojo.Course;
import com.codingapi.txlcn.tc.annotation.TccTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseManagerServiceImpl implements CourseManagerService {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 可以实现不支持XA事务协议的数据源的事务管理。 如：MongoDB
     * @param course
     * @return
     */
    @Override
    @TccTransaction
    public Course createCourse(Course course) {
        // 返回的对象中，主键是存在的。
        Course result = mongoTemplate.insert(course);
        if(result != null){
            return result;
        }
        return null;
    }

    /**
     * 确认事务方法，就是提交事务。
     * @param course
     */
    public void confirmCreateCourse(Course course){
        // 没有任何实现
        System.out.println("提交MongoDB事务管理。 confirmCreateCourse(Course course)");
    }

    public void cancelCreateCourse(Course course){
        // 回滚事务。MongoDB没有事务回滚，只能删除新增的数据。
        System.out.println("要删除的数据：" + course);
        mongoTemplate.remove(course);
        System.out.println("回滚MongoDB事务管理。 cancelCreateCourse(Course course)");
    }
}
