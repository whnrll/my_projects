package com.itbaizhan.utils;

import com.itbaizhan.config.WxPayConfig;
import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;
import com.wechat.pay.contrib.apache.httpclient.util.AesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 微信通知验签
 */
@Slf4j
public class WxVerifierUtils {




    /**
     * 验签
     * @param request
     * @param verifier
     * @param body
     * Content-Type: application/json; charset=utf-8
     * Content-Length: 2204
     * Connection: keep-alive
     * Keep-Alive: timeout=8
     * Content-Language: zh-CN
     * Request-ID: e2762b10-b6b9-5108-a42c-16fe2422fc8a
     * Wechatpay-Nonce: c5ac7061fccab6bf3e254dcf98995b8c
     * Wechatpay-Signature: CtcbzwtQjN8rnOXItEBJ5aQFSnIXESeV28Pr2YEmf9wsDQ8Nx25ytW6FXBCAFdrr0mgqngX3AD9gNzjnNHzSGTPBSsaEkIfhPF4b8YRRTpny88tNLyprXA0GU5ID3DkZHpjFkX1hAp/D0fva2GKjGRLtvYbtUk/OLYqFuzbjt3yOBzJSKQqJsvbXILffgAmX4pKql+Ln+6UPvSCeKwznvtPaEx+9nMBmKu7Wpbqm/+2ksc0XwjD+xlvlECkCxfD/OJ4gN3IurE0fpjxIkvHDiinQmk51BI7zQD8k1znU7r/spPqB+vZjc5ep6DC5wZUpFu5vJ8MoNKjCu8wnzyCFdA==
     * Wechatpay-Timestamp: 1554209980
     * Wechatpay-Serial: 5157F09EFDC096DE15EBE81A47057A7232F1B8E1
     * Cache-Control: no-cache, must-revalidate
     * @return
     */
    public static boolean verifier(HttpServletRequest request, Verifier verifier, String body) throws UnsupportedEncodingException {

        // 1. 随机串
        String nonce = request.getHeader("Wechatpay-Nonce");
        // 2. 获取微信传递过来签名
        String signature = request.getHeader("Wechatpay-Signature");
        // 3. 证书序列号
        String serial = request.getHeader("Wechatpay-Serial");
        // 4. 时间戳
        String timestamp = request.getHeader("Wechatpay-Timestamp");

        // 5. 构造签名串
        /**
         * 应答时间戳\n
         * 应答随机串\n
         * 应答报文主体\n
         */
        String signstr = Stream.of(timestamp,nonce,body).collect(Collectors.joining("\n","","\n"));


        return verifier.verify(serial,signstr.getBytes("utf-8"),signature);
    }


    /**
     * 解密
     * @param bodyMap
     * @param apiv3
     * @return
     * @throws GeneralSecurityException
     */
    public static String decrypt(Map<String ,Object> bodyMap,String apiv3) throws GeneralSecurityException {

        // 1. 获取通知数据
        Map<String,String> resourceMap = (Map<String, String>) bodyMap.get("resource");
        // 2. 获取密文
        String ciphertext = resourceMap.get("ciphertext");
        // 3. 获取随机串
        String nonce = resourceMap.get("nonce");
        // 4. 获取附加信息
        String associated_data = resourceMap.get("associated_data");
        log.info("密文=>{}",ciphertext);
        // 5. 构建aes对象
        AesUtil aesUtil = new AesUtil(apiv3.getBytes(StandardCharsets.UTF_8));
        // 6.解密 aes  apiv3
        String plainTest = aesUtil.decryptToString(associated_data.getBytes(StandardCharsets.UTF_8),nonce.getBytes(StandardCharsets.UTF_8),ciphertext);
        log.info("明文=>{}",plainTest);

        return plainTest;

    }





    public static void main(String[] args) {

        String collect = Stream.of("a", "b", "c").collect(Collectors.joining("?", "=", "%"));
        System.out.println(collect);


    }

}
