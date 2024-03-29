package com.itbaizhan;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*

主启动类
*/
@MapperScan("org.example.payment.zhifubao.mapper")
@SpringBootApplication
@Slf4j
@EnableDiscoveryClient
@EnableFeignClients
public class Bank1Main6001 {
    public static void main(String[] args) {
        SpringApplication.run(Bank1Main6001.class,args);
        log.info("************* bank1 启动成功  **********");
    }
}
