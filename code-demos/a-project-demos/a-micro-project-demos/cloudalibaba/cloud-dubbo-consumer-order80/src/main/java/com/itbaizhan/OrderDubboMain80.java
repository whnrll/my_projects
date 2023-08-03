package com.itbaizhan;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 主启动类
 */
@SpringBootApplication
@Slf4j
@EnableDiscoveryClient
public class OrderDubboMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderDubboMain80.class,args);
        log.info("************* OrderDubboMain80 启动成功 ********");
    }
}
