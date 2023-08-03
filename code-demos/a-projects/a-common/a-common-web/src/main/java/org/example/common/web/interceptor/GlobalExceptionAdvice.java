package org.example.common.web.interceptor;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;

import cn.hutool.core.exceptions.ExceptionUtil;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述：全局异常处理
 *
 * @author xutao
 * @date 2023-04-16 18:13:28
 * @since 1.0.0
 */
@Slf4j
@RestControllerAdvice(basePackages = {"com.example"}) // basePackages 指定生效范围
public class GlobalExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public ResultResponse<String> exception(Exception e) {
        ExceptionUtil.printStack(e, log, "system error!");
        return ResultResponse.failure(ResultCode.SYSTEM_ERROR, e.getMessage());
    }

    /**
     * 描述：处理单个参数校验失败抛出的异常
     *
     * @param e e
     * @return {@link ResultResponse }
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultResponse constraintViolationException(ConstraintViolationException e) {
        List<String> errorList = CollectionUtil.newArrayList();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            StringBuilder message = new StringBuilder();
            Path path = violation.getPropertyPath();
            String[] pathArr = StrUtil.splitToArray(path.toString(), ".");
            String msg = message.append(pathArr[1]).append(violation.getMessage()).toString();
            errorList.add(msg);
        }

        ExceptionUtil.printStack(e, log, "parameter invalid! {}", errorList);
        return ResultResponse.failure(ResultCode.PARAMS_IS_INVALID, errorList);
    }

    @ExceptionHandler(BindException.class)
    public ResultResponse<String> bindException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult.hasErrors()) {

        }
        ExceptionUtil.printStack(e, log, "parameter invalid! {}", e.getMessage());
        return ResultResponse.failure(ResultCode.PARAMS_IS_INVALID, e.getMessage());
    }

}
