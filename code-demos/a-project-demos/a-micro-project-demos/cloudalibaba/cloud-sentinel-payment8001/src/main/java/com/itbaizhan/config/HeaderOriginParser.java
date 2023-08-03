package org.example.payment.zhifubao.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 判断来源
 */
@Component
public class HeaderOriginParser implements RequestOriginParser {

    public String parseOrigin(HttpServletRequest request) {

        // 1. 获取请求头
        String origin = request.getHeader("origin");
        // 2. 非空判断
        if (StringUtils.isEmpty(origin)){
            return "blank";
        }
        return origin;
    }
}
