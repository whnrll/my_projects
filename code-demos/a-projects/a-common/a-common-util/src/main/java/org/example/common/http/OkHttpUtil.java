package org.example.common.http;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.sun.net.httpserver.Headers;
import jdk.vm.ci.code.site.Call;
import okhttp3.*;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;

import com.alibaba.fastjson.JSON;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.security.auth.callback.Callback;

@Slf4j
@Data
public class OkHttpUtil {
    private OkHttpClient client;

    public OkHttpUtil() {
        this.client = defaultOkHttpClient();
    }

    private OkHttpClient defaultOkHttpClient() {
        return new OkHttpClient.Builder().eventListenerFactory(call -> null)
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

    /**
     * @param client
     */
    public OkHttpUtil(OkHttpClient client) {
        this.client = client;
    }

    /**
     * 同步post请求
     */
    public Response httpPostSync(HttpRequestParam param) throws IOException {
        Request.Builder request = this.httpPost(param);
        return client.newCall(request.build()).execute();
    }

    /**
     * 异步post请求
     */
    public void httpPostAsync(HttpRequestParam param, OkHttpCallback okHttpCallback) {
        Request.Builder request = this.httpPost(param);
        this.async(request, okHttpCallback);
    }

    /**
     * 同步get请求
     */
    public Response httpGetSync(HttpRequestParam param) throws IOException {
        Request.Builder request = this.httpGet(param);
        return client.newCall(request.build()).execute();
    }

    public void httpGetAsync(HttpRequestParam param, OkHttpCallback callback) {
        Request.Builder request = this.httpGet(param);
        this.async(request, callback);
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
        setHeader(request, param.getHeaderMap());

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
     * 构造get请求
     */
    private Request.Builder httpGet(HttpRequestParam param) {
        Request.Builder request = new Request.Builder().get();
        setRequestUrl(request, param.getUrl(), param.getParamMap());
        setHeader(request, param.getHeaderMap());

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
     * 描述：设置 get 请求 url
     *
     * @param request
     * @param url
     * @param parameters
     */
    private void setRequestUrl(Request.Builder request, String url, Map<String, Object> parameters) {
        HttpUrl httpUrl = HttpUrl.parse(url);
        if (Objects.isNull(httpUrl)) {
            throw new IllegalArgumentException("url is correct");
        }

        // 拼接get请求参数
        HttpUrl.Builder urlBuilder = httpUrl.newBuilder();
        if (MapUtils.isNotEmpty(parameters)) {
            parameters.forEach((k, v) -> {
                urlBuilder.addQueryParameter(k, String.valueOf(v));
            });
        }

        request.url(urlBuilder.build());
    }

    /**
     * 设置请求头
     *
     * @param request
     */
    private void setHeader(Request.Builder request, Map<String, String> headerMap) {
        if (Objects.nonNull(headerMap)) {
            request.headers(Headers.of(headerMap));
        }
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

    public Response get(String url, Map<String, String> params, Map<String, String> headers) throws IOException {
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
        return client.newCall(request).execute();
    }

    public Response post(String url, Map<String, String> headers, String requestBody) throws Exception {
        Request request = new Request.Builder().url(url).headers(Headers.of(headers))
            .post(RequestBody.create(requestBody, MediaType.parse("application/json; charset=utf-8"))).build();

        return client.newCall(request).execute();
    }

    public Response post(String url, Map<String, String> headers, Map<String, String> requestBody) throws Exception {
        FormBody body = new FormBody.Builder()

            .add("your_param_1", "your_value_1").add("your_param_2", "your_value_2").build();

        Request request = new Request.Builder().url(url).headers(Headers.of(headers)).post(body).build();

        return client.newCall(request).execute();
    }

}
