package com.itbaizhan.stock;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主启动类
 */
@MapperScan("com.itbaizhan.stock.mapper")
@SpringBootApplication
@Slf4j
public class StockMain9091 {
    public static void main(String[] args) {
        SpringApplication.run(StockMain9091.class,args);
        log.info("************ 库存微服务启动成功 ************");
    }
}
