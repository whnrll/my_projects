package com.itbaizhan.sonwflake;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.itbaizhan.sonwflake.mapper")
@SpringBootApplication
public class SonwflakeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SonwflakeApplication.class, args);
    }

}
