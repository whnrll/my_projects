package org.example.datasource.multi;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.itcast"})
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.itcast.bulls.stock.deal.dao"})
public class MultiDataSourceApplication {
    public static void main(String[] args) {

    }
}
