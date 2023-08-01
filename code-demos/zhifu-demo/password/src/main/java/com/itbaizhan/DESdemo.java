package com.itbaizhan;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 对称加密 des
 */
public class DESdemo {

    public static void main(String[] args) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, IOException {
        //原文
        String input = "itbaizhan";
        // 私钥 秘钥
        String key = "12345678";
        // 算法
        String algorithhm = "DES";

        // 加密
//        String s = encrypetDES(input, key, algorithhm);
        // 密文：    x/+fElKdLigCPNowM8Plbg==
//        System.out.println(s);

        String mi = "x/+fElKdLigCPNowM8Plbg==";

        //解密
        String s = decryptDES(mi, key, algorithhm);
        System.out.println(s);

    }

    // 加密
    private static String  encrypetDES(String input ,String key,String algorithhm) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        // 获取对称加密对象
        Cipher cipher = Cipher.getInstance(algorithhm);

        // 创建加密规则
        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), algorithhm);

        // 初始化加密模式和算法
        // ENCRYPT_MODE 加密模式
        // DECRYPT_MODE 解密模式
        cipher.init(Cipher.ENCRYPT_MODE,sks);

        // 加密
        byte[] bytes = cipher.doFinal(input.getBytes());
        BASE64Encoder encoder = new BASE64Encoder();

        return encoder.encode(bytes);
    }



    // 解密
    private static String  decryptDES(String input ,String key,String algorithhm) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException {

        // 获取对称加密对象
        Cipher cipher = Cipher.getInstance(algorithhm);

        // 创建加密规则
        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), algorithhm);

        // 初始化加密模式和算法
        // ENCRYPT_MODE 加密模式
        // DECRYPT_MODE 解密模式
        cipher.init(Cipher.DECRYPT_MODE,sks);

        // 加密
        // 解码
        BASE64Decoder encoder = new BASE64Decoder();
        byte[] bytes = cipher.doFinal(encoder.decodeBuffer(input));

        // 明文
        return new String(bytes);
    }
}
