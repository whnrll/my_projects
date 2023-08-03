package org.example.common.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

import com.google.gson.JsonSerializer;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

/**
 * 描述：json 工具类
 *
 * @author xutao
 * @Date 2023-02-26 22:14:34
 */
@Slf4j
public class JacksonUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    // 日起格式化
    private static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    static {
        // 序列化的时候序列对象的那些属性
        // JsonInclude.Include.ALWAYS 所有属性
        // JsonInclude.Include.NON_DEFAULT 属性为默认值不序列化
        // JsonInclude.Include.NON_EMPTY 属性为 空（“”） 或者为 NULL 都不序列化
        // JsonInclude.Include.NON_NULL 属性为NULL 不序列化
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // 取消默认转换 timestamps 形式（springboot 默认就是禁用的）
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 所有的日期格式都统一为以下的样式，即 yyyy-MM-dd HH:mm:ss
        MAPPER.setDateFormat(new SimpleDateFormat(STANDARD_FORMAT));

        // 处理不同的时区偏移格式
        MAPPER.registerModule(new JavaTimeModule());

        // 大小写不敏感
        MAPPER.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

        // 忽略空Bean转json的错误（比如使用 new Object() 作为返回值无法序列化的错误）
        MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // 忽略get返回this的错误
        MAPPER.configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, false);

        // 忽略 Json 中存在 Java 对象木有的字段
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 忽略 transient 修饰的属性
        MAPPER.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true);

    }

    private JacksonUtil() {}

    public static ObjectMapper getMapper() {
        return MAPPER;
    }

    /**
     * 对象转Json格式字符串
     */
    public static String toJson(Object object) {
        if (object == null) {
            return null;
        }
        try {
            return object instanceof String ? (String)object : MAPPER.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Object TO Json String 字符串输出(输出空字符)
     */
    public static String toJsonEmpty(Object object) {
        if (object == null) {
            return null;
        }
        try {
            MAPPER.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
                @Override
                public void serialize(Object param, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                    throws IOException {
                    // 设置返回null转为 空字符串""
                    jsonGenerator.writeString("");
                }
            });
            return MAPPER.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Json 转为 Java Bean
     */
    public static <T> T fromJSON(String text, Class<T> clazz) {
        if (StringUtils.isEmpty(text) || clazz == null) {
            return null;
        }
        try {
            return MAPPER.readValue(text, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> fromJSON(Object object, Class<T> clazz) {
        if (Objects.isNull(object)) {
            return null;
        }


        try {
            TypeReference<List<T>> typeReference = new TypeReference<List<T>>() {};
            return MAPPER.readValue(MAPPER.writeValueAsString(object), typeReference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Json 转 Object
     */
    public static <T> T toObject(String text, TypeReference<T> typeReference) {
        try {
            if (StringUtils.isEmpty(text) || typeReference == null) {
                return null;
            }
            return (typeReference.getType().equals(String.class) ? (T) text : MAPPER.readValue(text, typeReference));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 描述：格式序列化
     *
     * @param data 数据
     * @return {@link String }
     */
    public static <T> String formatSerialize(T data) {
        try {
            return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T, V> String serializeWithView(T data, Class<V> view) {
        try {
            return MAPPER.writerWithView(view).writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T, V> T deSerializeWithView(String data, Class<T> clazz, Class<V> view) {
        try {
            return MAPPER.readerWithView(view).readValue(data, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <S, T> T convert(S source, Class<T> clazz) {
        return MAPPER.convertValue(source, clazz);
    }

    public static <S, T> T convert(S source, TypeReference<T> typeReference) {
        return MAPPER.convertValue(source, typeReference);
    }
}
