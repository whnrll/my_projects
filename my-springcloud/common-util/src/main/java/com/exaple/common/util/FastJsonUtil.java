package com.exaple.common.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;

import lombok.extern.slf4j.Slf4j;

/**
 * 描述：FastJson 序列化工具
 *
 * @author xutao
 * @Date 2023-02-28 21:04:55
 */
@Slf4j
public class FastJsonUtil {

    /**
     * SerializerFeature属性如下：
     * QuoteFieldNames	输出key时是否使用双引号,默认为true
     * UseSingleQuotes	使用单引号而不是双引号,默认为false
     * WriteMapNullValue	是否输出值为null的字段,默认为false
     * WriteEnumUsingToString	Enum输出name()或者original,默认为false
     * UseISO8601DateFormat	Date使用ISO8601格式输出，默认为false
     * WriteNullListAsEmpty	List字段如果为null,输出为[],而非null
     * WriteNullStringAsEmpty	字符类型字段如果为null,输出为”“,而非null
     * WriteNullNumberAsZero	数值字段如果为null,输出为0,而非null
     * WriteNullBooleanAsFalse	Boolean字段如果为null,输出为false,而非null
     * SkipTransientField	如果是true，类中的Get方法对应的Field是transient，序列化时将会被忽略。默认为true
     * SortField	按字段名称排序后输出。默认为false
     * WriteTabAsSpecial	把\t做转义输出，默认为false	不推荐
     * PrettyFormat	结果是否格式化,默认为false
     * WriteClassName	序列化时写入类型信息，默认为false。反序列化是需用到
     * DisableCircularReferenceDetect	消除对同一对象循环引用的问题，默认为false
     * WriteSlashAsSpecial	对斜杠’/’进行转义
     * BrowserCompatible	将中文都会序列化为unicode格式，字节数会多一些，但是能兼容IE 6，默认为false
     * WriteDateUseDateFormat	全局修改日期格式,默认为false。JSON.DEFFAULT_DATE_FORMAT = “yyyy-MM-dd”;JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat);
     * DisableCheckSpecialChar	一个对象的字符串属性中如果有特殊字符如双引号，将会在转成json时带有反斜杠转移符。如果不需要转义，可以使用这个属性。默认为false
     * NotWriteRootClassName	含义
     * BeanToArray	将对象转为array输出
     * WriteNonStringKeyAsString	含义
     * NotWriteDefaultValue	含义
     * BrowserSecure	含义
     * IgnoreNonFieldGetter	含义
     * WriteEnumUsingName	含义
     */
    private static final SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, // 输出空字段
        SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
        SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
        SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
        SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
    };

    /**
     * 将对象转成json格式
     */
    public static String toJson(Object object) {
        return JSON.toJSONString(object);
    }

    /**
     * 功能：json转对象
     */
    public static <T> T fromJSON(String jsonStr, Class<T> clazz) {
        T obj = JSON.parseObject(jsonStr, clazz);
        return obj;
    }

    /**
     * 功能：json转list
     */
    public static <T> List<T> stringToList(String json, Class<T> clazz) {
        List<T> list = JSONObject.parseArray(json, clazz);
        return list;
    }

    /**
     * json字符串转成list中有map的
     */
    public static <T> T toObject(String json, TypeReference<T> typeReference) {
        return JSON.parseObject(json, typeReference);
    }

}
