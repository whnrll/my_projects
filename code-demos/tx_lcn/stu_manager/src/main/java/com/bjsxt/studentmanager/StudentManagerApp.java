package com.bjsxt.studentmanager;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@MapperScan(basePackages = {"com.bjsxt.studentmanager.mapper"})
@EnableDistributedTransaction
public class StudentManagerApp {
    public static void main(String[] args) {
        SpringApplication.run(StudentManagerApp.class, args);
    }
}
