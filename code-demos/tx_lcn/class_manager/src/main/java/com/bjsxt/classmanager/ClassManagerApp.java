package com.bjsxt.classmanager;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 开启TX-LCN分布式事务注解支持。
 * 让服务代码中，有事务管理机制的方法上的
 * @LcnTransaction  @TccTransaction  @TxcTransaction注解生效。
 */
@SpringBootApplication
@MapperScan(basePackages = "com.bjsxt.classmanager.mapper")
@EnableDistributedTransaction
public class ClassManagerApp {
    public static void main(String[] args) {
        SpringApplication.run(ClassManagerApp.class, args);
    }
}
