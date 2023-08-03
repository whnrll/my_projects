package com.itbaizhan.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 读取数据源配置 master
 */
@Data
@ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource.master")
public class DBConfig1 {

    private  String url;
    private  String username;
    private  String password;
    private  String driverClassName;

}
