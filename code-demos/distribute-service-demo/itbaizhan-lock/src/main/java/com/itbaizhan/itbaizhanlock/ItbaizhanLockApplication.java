package com.itbaizhan.itbaizhanlock;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@MapperScan("com.itbaizhan.itbaizhanlock.mapper")
@SpringBootApplication
public class ItbaizhanLockApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItbaizhanLockApplication.class, args);
        log.info("************ 订单服务启动成功 ***********");
    }

}
