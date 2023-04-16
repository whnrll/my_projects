package com.exaple.common.vo;

import java.io.Serializable;

import com.exaple.common.enums.StatusCode;
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
public class CommonResponse<T> implements Serializable {

    private static final long serialVersionUID = 8159895465918935084L;

    /** 错误码 */
    private Integer code;

    /** 错误消息 */
    private String message;

    /** 泛型响应数据 */
    private T data;

    public CommonResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonResponse(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
    }

    public CommonResponse(StatusCode statusCode, T data) {
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
        this.data = data;
    }

    /**
     * 描述：成功，没有数据
     * {"code":0, "message":"success"}
     *
     * @return {@link CommonResponse }
     */
    public static CommonResponse success() {
        return new CommonResponse(StatusCode.SUCCESS);
    }

    /**
     * 描述：成功，有数据
     * {"code":0, "message":"success", "data":{}}
     *
     * @param data 数据
     * @return {@link CommonResponse }<{@link T }>
     */
    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse(StatusCode.SUCCESS, data);
    }

    /**
     * 描述：失败，没有数据
     * {"code":xxx, "message":"xxx"}
     *
     * @param statusCode
     * @return {@link CommonResponse }
     */
    public static CommonResponse failed(StatusCode statusCode) {
        return new CommonResponse(statusCode);
    }

    /**
     * 描述：失败，有数据
     * {"code":xxx, "message":"xxx", "data":{}}
     *
     * @param statusCode statusCode
     * @param data data
     * @return {@link CommonResponse }<{@link T }>
     */
    public static <T> CommonResponse<T> failed(StatusCode statusCode, T data) {
        return new CommonResponse(statusCode, data);
    }
}
