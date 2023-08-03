package org.example.common.exception;

import org.example.common.exception.constants.IErrorCodeEnum;

import lombok.Getter;

/**
 * 描述：自定义业务异常
 *
 * @author xutao
 * @date 2023-08-03 20:00:03
 * @since 1.0.0
 */
@Getter
public class BusinessException extends AbstractException {

    /**
     *
     */
    private static final long serialVersionUID = -1L;

    /**
     * 错误码
     */
    private IErrorCodeEnum errorCodeEnum;

    public BusinessException(IErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getCode() + ":" + errorCodeEnum.getMessage());
        this.errorCodeEnum = errorCodeEnum;
    }

    public IErrorCodeEnum getErrorCodeEnum() {
        return errorCodeEnum;
    }

}
