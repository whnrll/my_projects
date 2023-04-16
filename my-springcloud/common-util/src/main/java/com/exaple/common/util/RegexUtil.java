package com.exaple.common.util;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 描述：正则测试工具类
 *
 * @author xutao
 * @Date 2023-02-26 19:03:18
 */
public class RegexUtil {
    public static final String EMAIL = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";

    /**
     * 正则匹配
     *
     * @param text 要匹配的文本
     * @param regex 正则表达式
     * @return boolean
     */
    public static boolean mathches(String text, String regex) {
        if (StringUtils.isAnyBlank(text, regex)) {
            return false;
        }

        Pattern compile = Pattern.compile(regex);
        return compile.matcher(text).matches();
    }
}
