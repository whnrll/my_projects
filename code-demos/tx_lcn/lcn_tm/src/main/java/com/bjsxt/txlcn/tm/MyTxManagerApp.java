package com.bjsxt.txlcn.tm;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * EnableTransactionManagerServer - 开启事务管理器服务端。（事务协调器）
 */
@SpringBootApplication
@EnableTransactionManagerServer
public class MyTxManagerApp {
    public static void main(String[] args) {
        SpringApplication.run(MyTxManagerApp.class, args);
    }
}
