package org.example.payment;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 主启动类
 */
@Slf4j
@MapperScan("org.example.payment.zhifubao.mapper")
@SpringBootApplication
@EnableScheduling//开启任务调度
public class PaymentDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentDemoApplication.class, args);
        log.info("*************** 支付系统启动成功 ************");
    }

}
