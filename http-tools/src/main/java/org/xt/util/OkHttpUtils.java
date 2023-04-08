package org.xt.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
        CLIENT = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.MINUTES)
                .connectTimeout(10, TimeUnit.MINUTES)
                .build();
    }

    public static Response get(String url, Map<String, String> headers) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .headers(Headers.of(headers))
                .get()
                .build();

        return CLIENT.newCall(request).execute();
    }

    public static Response post(String url, Map<String, String> headers, String requestBody) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .headers(Headers.of(headers))
                .post(RequestBody.create(requestBody, MediaType.parse("application/json; charset=utf-8")))
                .build();

        return CLIENT.newCall(request).execute();
    }

    public static Response post(String url, Map<String, String> headers, Map<String, String> requestBody) throws Exception {
        FormBody body = new FormBody.Builder()

                .add("your_param_1", "your_value_1")
                .add("your_param_2", "your_value_2")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .headers(Headers.of(headers))
                .post(body)
                .build();

        return CLIENT.newCall(request).execute();
    }

}
