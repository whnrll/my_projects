package com.itbaizhan;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 主启动类
 */
//开启fegin扫描
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class OrderOpenFeignMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderOpenFeignMain80.class,args);
        log.info("********* OrderOpenFeignMain80 启动成功 ********");
    }
}
