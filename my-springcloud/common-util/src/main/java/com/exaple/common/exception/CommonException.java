package com.exaple.common.exception;

import com.exaple.common.enums.StatusCode;

public class CommonException extends RuntimeException {
    private Integer code;

    private String message;

    public CommonException(String message) {
        super(message);
        this.code = StatusCode.FAILED.getCode();
        this.message = message;
    }

    public CommonException(StatusCode statusCode) {
        super(statusCode.getMessage());
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
    }
}
