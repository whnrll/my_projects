package com.itheima.order.config;

import com.itheima.interceptor.MyRequestInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class InterceptorConfiguration {


    @Bean
    public RequestInterceptor interceptor() {
        return  new MyRequestInterceptor();
    }
}