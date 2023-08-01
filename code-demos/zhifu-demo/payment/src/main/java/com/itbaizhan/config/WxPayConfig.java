package com.itbaizhan.config;

import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.AutoUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import lombok.Data;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.lang.model.element.VariableElement;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;

/**
 *微信支付配置文件
 */
@Data
@Configuration
@PropertySource("classpath:wxpay.properties")
@ConfigurationProperties(prefix = "wxpay")
public class WxPayConfig {

    // 商户号
    private String mchId;

    // 商户API证书序列号
    private String mchSerialNo;

    // 商户私钥文件
    private String privateKeyPath;

    // APIv3密钥
    private String apiV3Key;

    // APPID
    private String appid;

    // 微信服务器地址
    private String domain;

    // 接收结果通知地址
    private String notifyDomain;


    /**
     * 获取商户私钥文件
     * @return
     */
    @Bean
    public PrivateKey getPrivateKey(){
        // 加载商户私钥（privateKey：私钥字符串）
        try {
            PrivateKey privateKey = PemUtil
                    .loadPrivateKey(new FileInputStream(privateKeyPath));
            return privateKey;
        } catch (FileNotFoundException e) {
            throw  new RuntimeException("私钥文件不存在",e);
        }
    }


    /**
     * 加载证书
     * @return
     */
    @Bean
    public AutoUpdateCertificatesVerifier getVerifier(PrivateKey privateKey) throws UnsupportedEncodingException {

        // 1、私钥签名对象  第一个参数 商户API证书序列号   第二个参数  私钥
        PrivateKeySigner privateKeySigner = new PrivateKeySigner(mchSerialNo, privateKey);

        // 2、身份认证
        WechatPay2Credentials wechatPay2Credentials = new WechatPay2Credentials(mchId, privateKeySigner);

        // 3、加载平台证书（mchId：商户号,mchSerialNo：商户证书序列号,apiV3Key：V3密钥）
        AutoUpdateCertificatesVerifier verifier = new AutoUpdateCertificatesVerifier(wechatPay2Credentials,apiV3Key.getBytes("utf-8") );

        return verifier;
    }


    /**
     * 获取HTTP请求对象
     * @return
     */
    @Bean(name = "wxPayHttpClient")
    public CloseableHttpClient getWxHttpClient(PrivateKey privateKey,AutoUpdateCertificatesVerifier autoUpdateCertificatesVerifier){
        // 初始化httpClient
        CloseableHttpClient httpClient = WechatPayHttpClientBuilder.create()
                .withMerchant(mchId, mchSerialNo, privateKey)
                .withValidator(new WechatPay2Validator(autoUpdateCertificatesVerifier)).build();
        return httpClient;
    }





}
