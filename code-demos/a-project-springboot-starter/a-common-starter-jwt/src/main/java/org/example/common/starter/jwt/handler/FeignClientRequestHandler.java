package org.example.common.starter.jwt.handler;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.example.common.starter.jwt.util.JwtAuthTokenFactory;

/**
 * 描述：拦截 Feign 客户端请求，添加自定义处理
 *
 * @author xutao
 * @date 2023-08-04 02:47:12
 * @since 1.0.0
 */
public class FeignClientRequestHandler implements RequestInterceptor {
    private final JwtAuthTokenFactory jwtTokenFactory;

    public FeignClientRequestHandler(JwtAuthTokenFactory jwtAuthTokenFactory) {
        this.jwtTokenFactory = jwtAuthTokenFactory;
    }

    @Override
    public void apply(RequestTemplate template) {
        String token = jwtTokenFactory.createToken().getToken();
        template.header("token", token);
    }
}
