package com.itbaizhan.orders;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主启动类
 */
@MapperScan("com.itbaizhan.orders.mapper")
@SpringBootApplication
@Slf4j
public class OrderMain9090 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain9090.class,args);
        log.info("***********  订单微服务启动成功 *******");
    }
}
