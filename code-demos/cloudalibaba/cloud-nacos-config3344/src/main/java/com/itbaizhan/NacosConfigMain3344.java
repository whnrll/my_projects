package com.itbaizhan;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@Slf4j
@EnableDiscoveryClient
public class NacosConfigMain3344 {
    public static void main(String[] args) {
        SpringApplication.run(NacosConfigMain3344.class,args);
        log.info("**********  NacosConfigMain3344 启动成功 *******");
    }
}
