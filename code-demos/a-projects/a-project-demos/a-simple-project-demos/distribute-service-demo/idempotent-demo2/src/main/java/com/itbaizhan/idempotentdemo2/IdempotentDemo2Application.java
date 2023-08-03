package com.itbaizhan.idempotentdemo2;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@MapperScan("com.itbaizhan.idempotentdemo2.mapper")
@SpringBootApplication
public class IdempotentDemo2Application {

    public static void main(String[] args) {
        SpringApplication.run(IdempotentDemo2Application.class, args);
        log.info("**************** success ************");
    }

}
