//package com.example.common;
//
//import java.io.IOException;
//
//import org.example.util.OkHttpUtils;
//import org.example.util.http.*;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import lombok.extern.slf4j.Slf4j;
//import okhttp3.Dispatcher;
//import okhttp3.HttpUrl;
//import okhttp3.Request;
//import okhttp3.Response;
//
//
//@Slf4j
//public class OkHttpUtilTest {
//    @Test
//    @DisplayName("测试 OkHttpUtils get()")
//    public void req1() {
//        String url = "https://www.xxxx.com/";
//        Response response;
//        try {
//            response = OkHttpUtils.get(url, null);
//            log.info(response.body().string());
//            response.close();
//        } catch (Exception e) {
//            log.error("http 请求异常：{}", e.getMessage(), e);
//            throw new RuntimeException("http 请求异常：" + e.getMessage());
//        }
//    }
//
//    /**
//     * 测试同步get请求 超时+重试
//     */
//    @Test
//    public void test4() throws IOException {
//        OkHttpUtil okHttpUtil = new OkHttpUtil();
//        HttpRequestParam requestParam = HttpRequestParam.builder()
//                .url("https://github.com")
//                .timeoutConfig(TimeoutConfig.builder().socketTimeout(1).build())
//                .retryRequestParam(new ParentRetryRequestParam(1, 3))
//                .build();
//
//        Dispatcher dispatcher = okHttpUtil.getClient().dispatcher();
//        // 最大请求数量
//        dispatcher.setMaxRequests(64);
//        // 同一域名同时存在的最大请求数量
//        dispatcher.setMaxRequestsPerHost(8);
//        okHttpUtil.httpGetSync(requestParam);
//    }
//
//    @Test
//    public void url() {
//        Request request = new Request.Builder().url("https://github.com/user/100").build();
//
//        HttpUrl httpUrl = request.url();
//        log.info("请求地址：{}", httpUrl.url());
//        log.info("是否https：{}", httpUrl.isHttps());
//        log.info("请求协议：{}", httpUrl.url().getProtocol());
//        log.info("请求host：{}", httpUrl.host());
//        log.info("请求port：{}", httpUrl.port());
//        log.info("请求资源路径：{}", httpUrl.encodedPath());
//    }
//}
