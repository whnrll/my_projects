package org.example.controller;

import java.util.Objects;

import org.example.MySpringBootTest;
import org.example.util.StreamUtil;
import org.example.util.http.HttpRequestParam;
import org.example.util.http.OkHttpUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Slf4j
public class HttpControllerTest extends MySpringBootTest {

    @Autowired
    private OkHttpClient okHttpClient;

    @Autowired
    private OkHttpClient defaultOkHttpClient;

    @Test
    @DisplayName("测试自定义的 OkHttpClient 请求阻塞")
    public void httpBlockTest() {
        OkHttpUtil client = new OkHttpUtil(okHttpClient);
        String url = "http://localhost:8080/http/block/15";
        Response response = null;
        try {
            response = client.httpGetSync(HttpRequestParam.builder().url(url).build());
            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if (Objects.nonNull(body)) {
                    log.info("请求成功：{}", body.string());
                }
            } else {
                log.info("请求失败：{}", response.message());
            }
        } catch (Exception e) {
            log.error("请求错误：{}", e.getMessage());
        } finally {
            StreamUtil.close(response);
        }
    }

    @Test
    @DisplayName("测试默认的 OkHttpClient 请求阻塞")
    public void httpBlockTest2() {
        OkHttpUtil client = new OkHttpUtil(defaultOkHttpClient);
        String url = "http://localhost:8080/http/block/100";
        Response response = null;
        try {
            response = client.httpGetSync(HttpRequestParam.builder().url(url).build());
            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if (Objects.nonNull(body)) {
                    log.info("请求成功：{}", body.string());
                }
            } else {
                log.info("请求失败：{}", response.message());
            }
        } catch (Exception e) {
            log.error("请求错误：{}", e.getMessage());
        } finally {
            StreamUtil.close(response);
        }
    }
}
