package com.muke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


/**
 * @author ziya
 * @date 2021/2/20 3:58 下午
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderAppliaction {

    @Bean
    @LoadBalanced
    public RestTemplate create() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderAppliaction.class);
    }
}
