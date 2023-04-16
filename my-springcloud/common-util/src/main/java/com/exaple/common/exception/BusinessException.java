package com.exaple.common.exception;

/**
 * 描述：业务逻辑层异常
 *
 * @author xutao
 * @Date 2023-03-08 21:35:50
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
