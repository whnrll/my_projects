package com.exaple.common.enums;

/**
 * 描述：状态码
 *
 * @author xutao
 * @date 2023-04-16 18:17:50
 * @since 1.0.0
 */
public enum ResultCode {
    FAILED(-1, "failed"),
    SUCCESS(0, "success"),

    NO_PERMISSION(401, "You do not have permission!"),

    SYSTEM_ERROR(500, "system abnormality, please contact the administrator for handling!"),
    DATABASE_ERROR(600, "data access error!"),

    PARAMS_IS_INVALID(700, "Parameter verification failed!"),



    ;

    private final int code;

    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
