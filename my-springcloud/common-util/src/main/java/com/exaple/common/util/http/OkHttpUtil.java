package com.exaple.common.util.http;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.lang.Assert;
import com.exaple.common.util.JacksonUtil;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;

import com.alibaba.fastjson.JSON;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

@Slf4j
@Data
public class OkHttpUtil {
    private OkHttpClient client;

    public OkHttpUtil() {
        this.client = defaultOkHttpClient();
    }

    private OkHttpClient defaultOkHttpClient() {
        return new OkHttpClient.Builder()
            // 默认读取超时时间
            .readTimeout(10000, TimeUnit.MILLISECONDS)
            // 默认连接超时时间
            .connectTimeout(10000, TimeUnit.MILLISECONDS)
            // 默认写入超时时间
            .writeTimeout(10000, TimeUnit.MILLISECONDS)
            // 关闭默认重试拦截器
            .retryOnConnectionFailure(false)
            // 添加自定义重试拦截器
            .addInterceptor(new RetryInterceptor()).build();
    }

    public OkHttpUtil(OkHttpClient client) {
        this.client = client;
    }

    public <T> T get(String url, Map<String, String> headers, Map<String, Object> params, Class<T> res) {
        // 1.创建 client

        // 2.创建 Request
        Request request = new Request.Builder().url(buildHttpUrl(url, params)).headers(buildHeaders(headers)).get().build();

        // 3.创建 Call
        Call call = client.newCall(request);

        // 4.调用 call 的同步请求方法
        try {
            Response response = call.execute();
            Assert.notNull(response.body(), () -> new RuntimeException("http get response body is null"));
            String string = response.body().string();
            if (res.isAssignableFrom(String.class)) {
                return (T) string;
            }

            return JacksonUtil.fromJSON(string, res);
        } catch (Exception e) {
            log.error("http get failed, {}", e.getMessage());
            throw new RuntimeException("http get failed");
        }
    }

    private Headers buildHeaders(Map<String, String> headers) {
        if (MapUtils.isNotEmpty(headers)) {
            return Headers.of(headers);
        }

        return Headers.of(new HashMap<>());
    }

    private String buildHttpUrl(String url, Map<String, Object> params) {
        if (StringUtils.isBlank(url)) {
            throw new IllegalArgumentException("url is correct!");
        }

        if (MapUtils.isNotEmpty(params)) {
            HttpUrl.Builder build = new HttpUrl.Builder();
            params.forEach((k, v) -> {
                if (Objects.nonNull(v) && StringUtils.isNotBlank(v.toString())) {
                    build.addQueryParameter(k, v.toString());
                }
            });
            return build.toString();
        }

        return url;
    }


    /**
     * 描述：同步 post
     *
     * @param param param
     * @return {@link Response }
     * @throws IOException
     */
    public Response syncPost(HttpRequestParam param) throws IOException {
        Request.Builder request = this.httpPost(param);
        return client.newCall(request.build()).execute();
    }

    /**
     * 描述：异步 post
     *
     * @param param param
     * @param okHttpCallback 回调函数
     */
    public void asyncPost(HttpRequestParam param, OkHttpCallback okHttpCallback) {
        Request.Builder request = this.httpPost(param);
        this.async(request, okHttpCallback);
    }


    /**
     * 同步get请求
     */
    public Response getSync(HttpRequestParam param) throws IOException {
        Request.Builder request = this.httpGet(param);
        return client.newCall(request.build()).execute();
    }

    public void getAsync(HttpRequestParam param, OkHttpCallback callback) {
        Request.Builder request = this.httpGet(param);
        this.async(request, callback);
    }

    private Request.Builder httpGet(HttpRequestParam param) {
        Request.Builder request = new Request.Builder().get();
//        setRequestUrl(request, param.getUrl(), param.getParamMap());
//        setHeader(request, param.getHeaderMap());

        // 自定义超时时间
        if (Objects.nonNull(param.getTimeoutConfig())) {
            request.tag(TimeoutConfig.class, param.getTimeoutConfig());
        }

        // 自定义重试次数
        if (Objects.nonNull(param.getRetryRequestParam())) {
            request.tag(ParentRetryRequestParam.class, param.getRetryRequestParam());
        }

        return request;
    }

    /**
     * 构造post请求
     *
     * @param param
     * @return
     */
    private Request.Builder httpPost(HttpRequestParam param) {
        Request.Builder request = new Request.Builder();
        request.url(param.getUrl());

        // 设置请求头
//        setHeader(request, param.getHeaderMap());

        // 自定义超时时间
        if (Objects.nonNull(param.getTimeoutConfig())) {
            request.tag(TimeoutConfig.class, param.getTimeoutConfig());
        }

        // 自定义重试次数
        if (Objects.nonNull(param.getRetryRequestParam())) {
            request.tag(ParentRetryRequestParam.class, param.getRetryRequestParam());
        }

        // 设置请求体
        setBody(request, param.getIsForm(), param.getBody());

        return request;
    }

    /**
     * 描述：设置请求体
     *
     * @param builder builder
     * @param isForm 是否表单提交
     * @param body 请求体
     */
    private void setBody(Request.Builder builder, Boolean isForm, Object body) {
        if (BooleanUtils.isTrue(isForm)) {
            List<NameValuePair> bodyForm = JSON.parseArray(JSON.toJSONString(body), NameValuePair.class);
            setFormBody(builder, bodyForm);
        } else {
            setPostBody(builder, body);
        }
    }

    /**
     * 表单方式提交
     *
     * @param bodyForm
     * @return
     */
    private void setFormBody(Request.Builder request, List<NameValuePair> bodyForm) {
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        bodyForm.stream().map(v -> formBodyBuilder.add(v.getName(), v.getValue()));
        RequestBody requestBody = formBodyBuilder.build();
        request.post(requestBody);
    }

    /**
     * json方式提交
     *
     * @return
     */
    private void setPostBody(Request.Builder request, Object body) {
        String jsonStr = "";
        if (Objects.nonNull(body)) {
            jsonStr = JSON.toJSONString(body);
        }

        RequestBody requestBody = RequestBody.create(jsonStr, MediaType.parse("application/json; charset=utf-8"));
        request.post(requestBody);
    }


    /**
     * 异步请求 接口回调
     *
     * @param okHttpCallback
     */
    public void async(Request.Builder request, OkHttpCallback okHttpCallback) {
        client.newCall(request.build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                okHttpCallback.failed(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                okHttpCallback.success(call, response);
            }
        });
    }


    /**
     * 生成安全套接字工厂，用于https请求的证书跳过
     *
     * @return
     */
    private static SSLSocketFactory createSSLSocketFactory(TrustManager[] trustAllCerts) {
        SSLSocketFactory ssfFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, SecureRandom.getInstanceStrong());
            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ssfFactory;
    }

    private static TrustManager[] buildTrustManagers() {
        return new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {

                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {

                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                }
        };
    }

}
