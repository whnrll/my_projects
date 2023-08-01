package com.bjsxt.coursemanager;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDistributedTransaction
public class CourseManagerApp {
    public static void main(String[] args) {
        SpringApplication.run(CourseManagerApp.class, args);
    }
}
