package org.example.config;

import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import javax.net.ssl.SSLContext;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * 描述：http客户端配置
 *
 * @author xutao
 * @Date 2023-03-10 22:52:25
 */
@Slf4j
@Configuration
public class HttpClientConfig {
    @Autowired
    private HttpClientPoolConfig httpClientPoolConfig;

    @Bean(name = "defaultRestTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 初始化RestTemplate,并加入spring的Bean工厂，由spring统一管理
     */
    @Bean(name = "httpClientRestTemplate")
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return createRestTemplate(factory);
    }

    /**
     * 描述：创建 HTTP 客户端工厂
     *
     * @return {@link ClientHttpRequestFactory }
     */
    @Bean(name = "clientHttpRequestFactory")
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory requestFactory =
            new HttpComponentsClientHttpRequestFactory(httpClient());
        // 连接超时
        requestFactory.setConnectTimeout(httpClientPoolConfig.getConnectTimeout());
        // 数据读取超时时间，即SocketTimeout
        requestFactory.setReadTimeout(httpClientPoolConfig.getReadTimeout());
        // 从连接池获取请求连接的超时时间，不宜过长，必须设置，比如连接不够用时，时间过长将是灾难性的
        requestFactory.setConnectionRequestTimeout(httpClientPoolConfig.getConnectionRequestTimout());
        return requestFactory;
    }

    /**
     * 描述：配置httpClient
     *
     * @return {@link CloseableHttpClient }
     */
    @Bean
    public HttpClient httpClient() {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        try {
            // 设置信任 ssl 访问
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (arg0, arg1) -> true).build();
            SSLConnectionSocketFactory sslConnectionSocketFactory =
                new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                // 注册 http 请求
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                // 注册 https 请求
                .register("https", sslConnectionSocketFactory).build();

            // 使用 Httpclient 连接池的方式配置(推荐)，同时支持 netty，okHttp 以及其他 http 框架
            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
            // 连接池最大连接数
            connectionManager.setMaxTotal(httpClientPoolConfig.getMaxTotalConnect());
            // 连接池同路由最大并发数
            connectionManager.setDefaultMaxPerRoute(httpClientPoolConfig.getMaxConnectPerRoute());

            RequestConfig requestConfig = RequestConfig.custom()
                // 返回数据的超时时间
                .setSocketTimeout(20000)
                // 连接上服务器的超时时间
                .setConnectTimeout(10000)
                // 从连接池中获取连接的超时时间
                .setConnectionRequestTimeout(1000).build();

            return httpClientBuilder.setDefaultRequestConfig(requestConfig)
                // 配置连接池
                .setConnectionManager(connectionManager)
                // 设置默认请求头
                .setDefaultHeaders(getDefaultHeaders())
                // 设置长连接保持策略
                .setKeepAliveStrategy(connectionKeepAliveStrategy())
                // 重试次数
                .setRetryHandler(new DefaultHttpRequestRetryHandler(httpClientPoolConfig.getRetryTimes(), true))
                // 设置 SSLContext
                .setSSLContext(sslContext).build();
        } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
            log.error("初始化HTTP连接池出错", e);
            return null;
        }
    }

    /**
     * 描述：配置长连接保持策略
     *
     * @return {@link ConnectionKeepAliveStrategy }
     */
    public ConnectionKeepAliveStrategy connectionKeepAliveStrategy() {
        return (response, context) -> {
            // Honor 'keep-alive' header
            HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
            while (it.hasNext()) {
                HeaderElement he = it.nextElement();
                String param = he.getName();
                String value = he.getValue();
                if (value != null && "timeout".equalsIgnoreCase(param)) {
                    try {
                        return Long.parseLong(value) * 1000;
                    } catch (NumberFormatException ignore) {
                        log.error("解析长连接过期时间异常", ignore);
                    }
                }
            }
            HttpHost target = (HttpHost)context.getAttribute(HttpClientContext.HTTP_TARGET_HOST);
            // 如果请求目标地址,单独配置了长连接保持时间,使用该配置
            Optional<Map.Entry<String, Integer>> any =
                Optional.ofNullable(httpClientPoolConfig.getKeepAliveTargetHost()).orElseGet(HashMap::new).entrySet()
                    .stream().filter(e -> e.getKey().equalsIgnoreCase(target.getHostName())).findAny();
            // 否则使用默认长连接保持时间
            return any.map(en -> en.getValue() * 1000L).orElse(httpClientPoolConfig.getKeepAliveTime() * 1000L);
        };
    }

    /**
     * 描述：设置请求头
     *
     * @return {@link List }<{@link Header }>
     */
    private List<Header> getDefaultHeaders() {
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("User-Agent",
            "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36"));
        headers.add(new BasicHeader("Accept-Encoding", "gzip,deflate"));
        headers.add(new BasicHeader("Accept-Language", "zh-CN"));
        headers.add(new BasicHeader("Connection", "Keep-Alive"));
        return headers;
    }

    /**
     * 描述：创建 RestTemplate
     *
     * @param factory 工厂
     * @return {@link RestTemplate }
     */
    private RestTemplate createRestTemplate(ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate(factory);

        // 我们采用 RestTemplate 内部的 MessageConverter，重新设置 StringHttpMessageConverter 字符集，解决中文乱码问题
        List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
        converterList
            .removeIf(httpMessageConverter -> StringHttpMessageConverter.class == httpMessageConverter.getClass());
        Charset defaultCharset = Charset.forName(httpClientPoolConfig.getCharset());
        converterList.add(1, new StringHttpMessageConverter(defaultCharset));

        // 设置错误处理器
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());

        return restTemplate;
    }
}
