package com.itbaizhan;

import com.itbaizhan.config.DBConfig1;
import com.itbaizhan.config.DBConfig2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 主启动类
 */
@Slf4j
@EnableConfigurationProperties(value = {DBConfig1.class, DBConfig2.class})
@SpringBootApplication
@EnableTransactionManagement
public class AtomikosMain6003 {
    public static void main(String[] args) {
        SpringApplication.run(AtomikosMain6003.class,args);
        log.info("************** AtomikosMain6003 启动成功  *********");
    }
}
