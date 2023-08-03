package org.example.common.starter.jwt.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.example.common.starter.jwt.service.TokenChecker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class RequestHandlerInterceptor implements HandlerInterceptor {
    private final TokenChecker tokenChecker;

    public RequestHandlerInterceptor(TokenChecker tokenChecker) {
        this.tokenChecker = tokenChecker;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            log.error("token is blank");
            return false;
        }

        try {
            return tokenChecker.verify(token);
        } catch (Exception e) {
            log.error("check token exception: [{}]", e.getMessage());
            return false;
        }
    }
}
