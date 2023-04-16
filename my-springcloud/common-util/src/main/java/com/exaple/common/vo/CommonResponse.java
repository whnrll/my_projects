package com.exaple.common.vo;

import java.io.Serializable;

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
    private T Data;

    public CommonResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
