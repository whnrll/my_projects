package com.itbaizhan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("nacos")
public class ConfigController {

    @Value("${nacos.config}")
    private String config;

    /**
     * 获取配置文件
     * @return
     */
    @GetMapping("getConfig")
    public String getConfig(){
        return config;
    }

}
