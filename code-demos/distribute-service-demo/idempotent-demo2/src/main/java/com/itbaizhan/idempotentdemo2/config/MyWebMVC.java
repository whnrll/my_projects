package com.itbaizhan.idempotentdemo2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

@Configuration
public class MyWebMVC implements WebMvcConfigurer {

    @Autowired
    private MyInceptor myInceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 白名单  忽略拦截
        ArrayList<String> strings = new ArrayList<>();
        strings.add("/user/index");
        strings.add("/user/toadduser");
        strings.add("/user/findById");
        strings.add("/user/update");
        registry.addInterceptor(myInceptor).excludePathPatterns(strings);

    }
}
