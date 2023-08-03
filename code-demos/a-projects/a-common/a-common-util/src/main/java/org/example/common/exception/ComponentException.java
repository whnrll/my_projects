package org.example.common.exception;

import org.example.common.exception.constants.IErrorCodeEnum;

/**
 * 描述：自定义组件异常
 *
 * @author xutao
 * @date 2023-08-03 19:59:47
 * @since 1.0.0
 */
public class ComponentException extends AbstractException {

    /**
     *
     */
    private static final long serialVersionUID = 2333790764399190094L;

    /**
     * 错误码枚举信息
     */
    private IErrorCodeEnum errorCodeEnum;

    /**
     * 扩展的错误信息
     */
    private String extendErrorMessage;

    public ComponentException(IErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getCode() + ":" + errorCodeEnum.getMessage());
        this.errorCodeEnum = errorCodeEnum;
    }

    public ComponentException(IErrorCodeEnum errorCodeEnum, String extendErrorMessage) {
        super(errorCodeEnum.getCode() + ":" + errorCodeEnum.getMessage() + "[" + extendErrorMessage + "]");
        this.errorCodeEnum = errorCodeEnum;
        this.extendErrorMessage = extendErrorMessage;
    }

    public IErrorCodeEnum getErrorCodeEnum() {
        return errorCodeEnum;
    }

    public void setErrorCodeEnum(IErrorCodeEnum errorCodeEnum) {
        this.errorCodeEnum = errorCodeEnum;
    }

    public String getExtendErrorMessage() {
        return extendErrorMessage;
    }

    public void setExtendErrorMessage(String extendErrorMessage) {
        this.extendErrorMessage = extendErrorMessage;
    }

}
