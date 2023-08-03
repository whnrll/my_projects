package com.itbaizhan;


import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 主启动类
 */
@MapperScan("com.itbaizhan.mapper")
@SpringBootApplication
@Slf4j
@EnableDiscoveryClient
public class Bank2Main6002 {

    public static void main(String[] args) {
        SpringApplication.run(Bank2Main6002.class,args);
        log.info("************** bank2 启动成功 **********");
    }

}
