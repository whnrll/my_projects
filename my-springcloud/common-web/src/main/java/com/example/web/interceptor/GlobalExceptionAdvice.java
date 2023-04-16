package com.example.web.interceptor;

import com.exaple.common.exception.BusinessException;
import com.exaple.common.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述：全局异常处理
 *
 * @author xutao
 * @date 2023-04-16 18:13:28
 * @since 1.0.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = BusinessException.class)
    public CommonResponse<String> handlerBusinessException(
            HttpServletRequest req, Exception ex
    ) {

        CommonResponse<String> response = new CommonResponse<>(
                -1, "business error"
        );
        response.setData(ex.getMessage());
        log.error("commerce service has error: [{}]", ex.getMessage(), ex);
        return response;
    }
}
