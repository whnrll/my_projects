package com.itbaizhan;


import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主启动
 */
@MapperScan("org.example.payment.zhifubao.mapper")
@SpringBootApplication
@Slf4j
public class Bank01Main6004 {
    public static void main(String[] args) {
        SpringApplication.run(Bank01Main6004.class,args);
        log.info("****************  转出微服务启动成功 *******");
    }
}
