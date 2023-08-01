package com.itbaizhan.account;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 主启动类
 */
@SpringBootApplication
@Slf4j
@MapperScan("com.itbaizhan.account.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class AccountMain7070 {
    public static void main(String[] args) {
        SpringApplication.run(AccountMain7070.class,args);
        log.info("******** 账户微服务启动成功 ********");
    }
}
