package org.example.common.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * MD5消息摘要算法
 */
public class MD5demo {
    public static void main(String[] args) throws NoSuchAlgorithmException {

        // 1. 原文
        String input = "itbaizhan";// xDCo+ksHiej/JY6FqeBZcg==

        // 2. 算法
        String algorithm = "MD5";

        // 3. 获取消息摘要对象
        MessageDigest instance = MessageDigest.getInstance(algorithm);

        // 4. 获取消息摘要的字节数组
        byte[] digest = instance.digest(input.getBytes());

        // 5. base64编码
        Base64.Encoder encoder = Base64.getEncoder();
        System.out.println(encoder.encodeToString(digest));

    }
}
