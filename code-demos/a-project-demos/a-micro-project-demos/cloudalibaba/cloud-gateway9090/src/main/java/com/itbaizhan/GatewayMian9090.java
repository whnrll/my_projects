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
public class GatewayMian9090 {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMian9090.class,args);
        log.info("***********  GatewayMian9090 启动成功 ********");
    }
}
