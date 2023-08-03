package com.itbaizhan.payment;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 主启动类
 */
@SpringBootApplication
@Slf4j
@EnableDiscoveryClient
@MapperScan("com.itbaizhan.payment.mapper")
public class PaymentMain7071 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain7071.class,args);
        log.info("************* 充值微服务启动成功 *********");
    }
}
