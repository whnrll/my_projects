package com.itbaizhan;

import com.sun.xml.internal.messaging.saaj.util.Base64;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5消息摘要算法
 */
public class MD5demo {
    public static void main(String[] args) throws NoSuchAlgorithmException {

        // 1. 原文
        String input = "itbaizhan";//xDCo+ksHiej/JY6FqeBZcg==

        // 2. 算法
        String algorithm = "MD5";

        // 3. 获取消息摘要对象
        MessageDigest instance = MessageDigest.getInstance(algorithm);

        // 4. 获取消息摘要的字节数组
        byte[] digest = instance.digest(input.getBytes());

        // 5. base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        System.out.println(encoder.encode(digest));


    }
}
