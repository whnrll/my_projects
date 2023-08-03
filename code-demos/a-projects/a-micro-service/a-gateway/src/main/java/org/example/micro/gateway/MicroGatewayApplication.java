package org.example.micro.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * 描述：微服务网关
 *
 * @author xutao
 * @date 2023-08-03 22:16:33
 * @since 1.0.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MicroGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroGatewayApplication.class, args);
    }
}
