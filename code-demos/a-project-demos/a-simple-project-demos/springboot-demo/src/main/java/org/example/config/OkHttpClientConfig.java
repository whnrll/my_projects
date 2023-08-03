package org.example.config;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Slf4j
@Configuration
public class OkHttpClientConfig {

    @Bean
    public OkHttpClient defaultOkHttpClient() {
        return new OkHttpClient();
    }

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient().newBuilder()
                .connectTimeout(Duration.ofSeconds(30))
                .readTimeout(Duration.ofSeconds(30))
                .writeTimeout(Duration.ofSeconds(30))
                .callTimeout(Duration.ofMinutes(3))
                .addInterceptor(logInterceptor())
                .build();
    }

    @Bean
    public Interceptor logInterceptor() {
        return chain -> {
            Request request = chain.request();
            long start = System.currentTimeMillis();
            Response response = chain.proceed(request);
            long end = System.currentTimeMillis();
            log.debug("请求地址：{}， 请求耗时：{}毫秒", request.url(), end - start);
            return response;
        };
    }
}
