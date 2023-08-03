package org.example.common.starter.jwt.web;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.example.common.starter.jwt.handler.RequestHandlerInterceptor;
import org.example.common.starter.jwt.service.TokenChecker;

/**
 * 描述：jwt mvc配置
 *
 * @author xutao
 * @Date 2023-02-28 22:30:27
 */
public class JwtMvcConfigurer implements WebMvcConfigurer {
    private final TokenChecker tokenChecker;

    public JwtMvcConfigurer(TokenChecker tokenChecker) {
        this.tokenChecker = tokenChecker;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestHandlerInterceptor(tokenChecker)).addPathPatterns("/**");
    }
}
