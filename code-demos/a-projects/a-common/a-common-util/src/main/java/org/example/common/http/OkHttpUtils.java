package org.example.common.http;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.*;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.example.common.utils.UUIDUtil;
import org.jetbrains.annotations.NotNull;

import lombok.extern.slf4j.Slf4j;

/**
 * 描述：
 *
 * @author : xutao
 * @date : 2022-10-11 21:42
 */
@Slf4j
public class OkHttpUtils {

    private static final OkHttpClient CLIENT;

    static {
        CLIENT = new OkHttpClient.Builder().readTimeout(10, TimeUnit.MINUTES).connectTimeout(10, TimeUnit.MINUTES)
            .addInterceptor(new LogPrintInterceptor()).addInterceptor(new AddHeaderInterceptor()).build();
    }

    public static Response get(String url, Map<String, String> params, Map<String, String> headers) throws IOException {
        if (StringUtils.isBlank(url)) {
            throw new IllegalArgumentException("url is correct");
        }

        HttpUrl httpUrl = HttpUrl.parse(url);
        if (Objects.isNull(httpUrl)) {
            throw new IllegalArgumentException("url is correct");
        }

        HttpUrl.Builder httpBuilder = httpUrl.newBuilder();
        if (MapUtils.isNotEmpty(params)) {
            params.forEach(httpBuilder::addQueryParameter);
        }

        Request request = new Request.Builder().url(httpBuilder.build()).headers(Headers.of(headers)).get().build();
        return CLIENT.newCall(request).execute();
    }

    public static Response get(String url, Map<String, String> params) throws IOException {
        if (StringUtils.isBlank(url)) {
            throw new IllegalArgumentException("url is correct");
        }

        HttpUrl httpUrl = HttpUrl.parse(url);
        if (Objects.isNull(httpUrl)) {
            throw new IllegalArgumentException("url is correct");
        }

        HttpUrl.Builder httpBuilder = httpUrl.newBuilder();
        if (MapUtils.isNotEmpty(params)) {
            params.forEach(httpBuilder::addQueryParameter);
        }

        Request request = new Request.Builder().url(httpBuilder.build()).get().build();
        return CLIENT.newCall(request).execute();
    }

    public static Response post(String url, Map<String, String> headers, String requestBody) throws Exception {
        Request request = new Request.Builder().url(url).headers(Headers.of(headers))
            .post(RequestBody.create(requestBody, MediaType.parse("application/json; charset=utf-8"))).build();

        return CLIENT.newCall(request).execute();
    }

    public static Response post(String url, Map<String, String> headers, Map<String, String> requestBody)
        throws Exception {
        FormBody body = new FormBody.Builder()

            .add("your_param_1", "your_value_1").add("your_param_2", "your_value_2").build();

        Request request = new Request.Builder().url(url).headers(Headers.of(headers)).post(body).build();

        return CLIENT.newCall(request).execute();
    }

    /**
     * 描述：自定义日志记录拦截器
     * 参考文档：https://blog.csdn.net/kaifa1321/article/details/80428107?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_baidulandingword~default-5-80428107-blog-109107807.235^v28^pc_relevant_default_base1&spm=1001.2101.3001.4242.4&utm_relevant_index=8
     *
     * @author xutao
     * @date 2023-04-14 19:32:53
     * @since 1.0.0
     */
    private static class LogPrintInterceptor implements Interceptor {
        @NotNull
        @Override
        public Response intercept(@NotNull Chain chain) throws IOException {
            Request request = chain.request();

            // 打印请求信息：请求url/请求头/连接信息
            HttpUrl url = request.url();
            Headers requestHeaders = request.headers();
            Connection connection = chain.connection();
            log.info("【请求信息】url={}, headers={}, connection={}", url, requestHeaders, connection);

            // 执行请求
            long startTime = System.currentTimeMillis();
            Response response = chain.proceed(request);
            long endTime = System.currentTimeMillis();
            log.info("请求耗时：{}毫秒", endTime - startTime);

            // 打印响应信息：响应码/响应消息/响应头
            int code = response.code();
            Headers responseHeaders = response.headers();
            String message = response.message();
            log.info("【响应信息】code={}, message={}, responseHeaders={}", code, message, responseHeaders);

            // 注意 response.body() 只能读取一次
            return response;
        }
    }

    /**
     * 描述：拦截 OkHttp 请求，添加请求头
     *
     * @author xutao
     * @date 2023-04-14 20:09:39
     * @since 1.0.0
     */
    private static class AddHeaderInterceptor implements Interceptor {
        @NotNull
        @Override
        public Response intercept(@NotNull Chain chain) throws IOException {
            Request request = chain.request();

            // 开关启用添加请求头
            if (Boolean.parseBoolean(System.getProperty("token"))) {
                Request.Builder builder = request.newBuilder();
                builder.addHeader("token", UUIDUtil.generateUUID());
                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }

            return chain.proceed(request);
        }
    }

}
