package org.example.common.service.utils;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * 描述：Bean 助手工具类
 *
 * @author xutao
 * @date 2023-08-03 20:25:07
 * @since 1.0.0
 */
public class BeanAssistant {
    /**
     * 获取对象为 null 的属性
     *
     * @param source 源对象
     * @return 所有为 null 的属性
     */
    public static Set<String> getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        return emptyNames;
    }

    /**
     * 拷贝对象属性，排除非 null 属性
     *
     * @param src 源对象
     * @param target 目标对象
     */
    public static void copyNotNullProps(Object src, Object target) {
        Set<String> emptyNames = getNullPropertyNames(src);
        BeanUtils.copyProperties(src, target, emptyNames.toArray(new String[0]));
    }

    /**
     * 拷贝对象属性，排除非 null 属性和指定属性
     *
     * @param src 源对象
     * @param target 目标对象
     */
    public static void copyNotNullProps(Object src, Object target, String... excludeNames) {
        Set<String> emptyNames = getNullPropertyNames(src);
        for(String excludeName : excludeNames) {
            emptyNames.add(excludeName);
        }
        BeanUtils.copyProperties(src, target, emptyNames.toArray(new String[0]));
    }
}
