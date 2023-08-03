package org.example.common.utils;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.helpers.MessageFormatter;

import lombok.extern.slf4j.Slf4j;

/**
 * 描述：异常打印
 *
 * @author xutao
 * @date 2023-04-16 18:38:41
 * @since 1.0.0
 */
@Slf4j
public class ExceptionUtil {
    public static void print(Throwable e, Logger logger, String message) {
        logger.error("{}: {}", message, e.getMessage());
    }

    /**
     * 描述：记录错误消息
     *
     * @param e e
     * @param logger logger
     * @param message 消息提示，可以使用占位符{}
     * @param args 消息提示占位符对应的参数值
     */
    public static void print(Throwable e, Logger logger, String message, Object ... args) {
        String info = message;
        if (Objects.nonNull(args)) {
            info = MessageFormatter.arrayFormat(message, new Object[]{args}).getMessage();
        }

        logger.error("{}: {}", info, e.getMessage());
    }

    /**
     * 描述：记录错误消息
     *
     * @param e e
     * @param logger logger
     * @param message 消息提示，可以使用占位符{}
     * @param args 消息提示占位符对应的参数值
     */
    public static void printStack(Throwable e, Logger logger, String message, Object ... args) {
        String info = message;
        if (Objects.nonNull(args)) {
            info = MessageFormatter.arrayFormat(message, new Object[]{args}).getMessage();
        }

        logger.error("{}: {}", info, e.getMessage(), e);
    }

    private static String desensitization(String message) {
        // TODO 实现日志脱敏
        return "";
    }
}
