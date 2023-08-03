package org.example.payment.zhifubao.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 读取数据源配置 slave
 */
@Data
@ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource.slave")
public class DBConfig2 {

    private  String url;
    private  String username;
    private  String password;
    private  String driverClassName;

}
