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
public class PaymentDubboMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentDubboMain8001.class,args);
        log.info("************** PaymentDubboMain8001 启动成功 ********");

    }
}
