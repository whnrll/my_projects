package com.itbaizhan.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 读取支付宝配置
 */
@Data
@Configuration
@PropertySource("classpath:zfbpay.properties")
@ConfigurationProperties(prefix = "alipay")
public class ZfbPayConfig {

    // 应用id
    private  String appId;
    // 应用私钥
    private  String privateKey;
    // 支付宝公钥
    private  String publicKey;
    // 网关
    private  String gateway;
    // 回调地址
    private  String notify_url;


    /**
     * 设置支付宝客户端
     */
    @Bean
    public AlipayClient setAlipayClient(){
        //  网关  appid  应用私钥  格式  编码格式  支付宝公钥  加密算法
       return  new DefaultAlipayClient(gateway,appId,privateKey,"json","UTF-8",publicKey,"RSA2");

    }




}
