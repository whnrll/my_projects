package com.itbaizhan.itbaizhanlock.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZookeeperConfig {


    /**
     * 创建Cuator客户端
     * @return
     */
    @Bean
    public CuratorFramework getZkClient(){
        // 1. 创建cuator客户端
        CuratorFramework client = CuratorFrameworkFactory.builder()
                // zk链接地址
                .connectString("localhost:2181")
                // 回话超时时间
                .sessionTimeoutMs(5000)
                // 链接创建超时时间
                .connectionTimeoutMs(5000)
                // 重试策略
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        client.start();
        return client;
    }

}
