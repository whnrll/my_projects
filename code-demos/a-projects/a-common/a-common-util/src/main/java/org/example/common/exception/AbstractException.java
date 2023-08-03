package org.example.common.exception;

/**
 * 描述：抽象异常类
 *
 * @author xutao
 * @date 2023-08-03 19:59:12
 * @since 1.0.0
 */
public abstract class AbstractException extends Exception {
    private static final long serialVersionUUID = -1L;

    public AbstractException(String message) {
        super(message);
    }

}
