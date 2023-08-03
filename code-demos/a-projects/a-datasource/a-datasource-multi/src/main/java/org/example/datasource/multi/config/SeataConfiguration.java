package org.example.datasource.multi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.seata.spring.annotation.GlobalTransactionScanner;

/**
 * 描述：seata 分布式事务配置
 *
 * @author xutao
 * @date 2023-08-03 22:09:44
 * @since 1.0.0
 */
@Configuration
public class SeataConfiguration {

    /**
     * 获取服务的名称
     */
    @Value("${spring.application.name}")
    private String applicationId;

    @Bean
    public GlobalTransactionScanner globalTransactionScanner() {
        GlobalTransactionScanner globalTransactionScanner = new GlobalTransactionScanner(applicationId,
                "stock_trade_group");
        return globalTransactionScanner;
    }
}
