package com.exaple.common.exception;

import com.exaple.common.enums.ResultCode;

public class CommonException extends RuntimeException {
    private Integer code;

    private String message;

    public CommonException(String message) {
        super(message);
        this.code = ResultCode.FAILED.getCode();
        this.message = message;
    }

    public CommonException(ResultCode statusCode) {
        super(statusCode.getMessage());
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
    }
}
