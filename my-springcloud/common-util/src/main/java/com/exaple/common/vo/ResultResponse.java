package com.exaple.common.vo;

import java.io.Serializable;

import com.exaple.common.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述：通用响应对象定义
 * {
 * "code": 0,
 * "message": "",
 * "data": {}
 * }
 *
 * @author xutao
 * @date 2023-04-16 00:02:58
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultResponse<T> implements Serializable {

    private static final long serialVersionUID = 8159895465918935084L;

    /** 错误码 */
    private Integer code;

    /** 错误消息 */
    private String message;

    /** 泛型响应数据 */
    private T data;

    public ResultResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultResponse(ResultCode statusCode) {
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
    }

    public ResultResponse(ResultCode statusCode, T data) {
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
        this.data = data;
    }

    /**
     * 描述：成功，没有数据
     * {"code":0, "message":"success"}
     *
     * @return {@link ResultResponse }
     */
    public static ResultResponse success() {
        return new ResultResponse(ResultCode.SUCCESS);
    }

    /**
     * 描述：成功，有数据
     * {"code":0, "message":"success", "data":{}}
     *
     * @param data 数据
     * @return {@link ResultResponse }<{@link T }>
     */
    public static <T> ResultResponse<T> success(T data) {
        return new ResultResponse(ResultCode.SUCCESS, data);
    }

    /**
     * 描述：失败，没有数据
     * {"code":xxx, "message":"xxx"}
     *
     * @param statusCode
     * @return {@link ResultResponse }
     */
    public static ResultResponse failure(ResultCode statusCode) {
        return new ResultResponse(statusCode);
    }

    /**
     * 描述：失败，有数据
     * {"code":xxx, "message":"xxx", "data":{}}
     *
     * @param statusCode statusCode
     * @param data data
     * @return {@link ResultResponse }<{@link T }>
     */
    public static <T> ResultResponse<T> failure(ResultCode statusCode, T data) {
        return new ResultResponse(statusCode, data);
    }
}
