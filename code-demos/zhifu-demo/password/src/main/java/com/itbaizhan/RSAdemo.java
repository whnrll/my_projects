package com.itbaizhan;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.commons.io.FileUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 非对称加密  公钥  私钥
 */
public class RSAdemo {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {

        // 1. 原文
        String input = "itbaizhan";
        // 2. 加密算法
        String  algorithm = "RSA";
        // 3. 生成密钥文件
//        generateKey(algorithm,"a.pub","a.pri");
        // 公钥加密  -》  私钥解密
        // 4. 获取公钥
//        PublicKey publicKey = getPublicKey("a.pub", algorithm);
        // 5. 加密
//        String s = encryptRSA(input, publicKey, algorithm);
        // JH5swnaY3PDXBwSo/TAipeChZDzFA83+92uGL0MVMmwMgxyyqfiNTLmbj4j/lq50pm5j13vHuKzeV1MUpqySni9D8Qqx4SLuy/3dANLUVuZM3A5+ou+kaasW2hfJq6/2pnfumAqalwwHA4dVBX43aAkkpFP8ByOqj1mUpLoGd6SUYflXN8ioJ4brOc9A9w6lk31bhSBWfQYJQUkPy0qe9Y9VUCG+ScwJWBQtjTCOIq+ae4DNOqIahbE2Bipb0bNJt5kv5ntduxVgyWPqHuTEO9CvOqfI6xpfOebzSeMh8CFJ/p7eHTDJ1ml1z+MUn55vLUQkhaKJsjtCnIrmtBDYFA==
//        System.out.println(s);


        // 密文
        String s = "JH5swnaY3PDXBwSo/TAipeChZDzFA83+92uGL0MVMmwMgxyyqfiNTLmbj4j/lq50pm5j13vHuKzeV1MUpqySni9D8Qqx4SLuy/3dANLUVuZM3A5+ou+kaasW2hfJq6/2pnfumAqalwwHA4dVBX43aAkkpFP8ByOqj1mUpLoGd6SUYflXN8ioJ4brOc9A9w6lk31bhSBWfQYJQUkPy0qe9Y9VUCG+ScwJWBQtjTCOIq+ae4DNOqIahbE2Bipb0bNJt5kv5ntduxVgyWPqHuTEO9CvOqfI6xpfOebzSeMh8CFJ/p7eHTDJ1ml1z+MUn55vLUQkhaKJsjtCnIrmtBDYFA==";
        // 读取私钥
        PrivateKey privatKey = getPrivatKey("a.pri", algorithm);
        // 解密
        String s1 = decryptRSA(s, privatKey, algorithm);
        System.out.println(s1);


    }


    /**
     * 生成秘钥
     * @param algorithm 算法
     * @param pubPath 公钥保存路径
     * @param priPath 私钥保存路径
     */
    public static  void generateKey(String algorithm,String pubPath,String priPath) throws NoSuchAlgorithmException, IOException {
        // 1. 获取密钥生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        // 2. 获取密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 3. 获取公钥
        PublicKey publicKey = keyPair.getPublic();
        // 4. 获取私钥
        PrivateKey privateKey = keyPair.getPrivate();
        // 5. 获取byte数组
        byte[] publicKeyEncoded = publicKey.getEncoded();
        byte[] privateKeyEncoded = privateKey.getEncoded();
        // 6. 编码BASE64编码
        String publicKeyString = Base64.encode(publicKeyEncoded);
        String privateKeyString = Base64.encode(privateKeyEncoded);
        // 7. 保存文件
        FileUtils.writeStringToFile(new File(pubPath),publicKeyString, Charset.forName("UTF-8"));
        FileUtils.writeStringToFile(new File(priPath),privateKeyString, Charset.forName("UTF-8"));

    }


    /**
     * 读取公钥文件
     * @param publicPath
     * @param algorithm
     * @return
     */
    public static PublicKey getPublicKey(String publicPath,String algorithm) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {

        // 1. 读取文件
        String s = FileUtils.readFileToString(new File(publicPath), Charset.forName("UTF-8"));
        // 2. 获取密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        // 3. 构建秘钥规范
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decode(s));
        return keyFactory.generatePublic(x509EncodedKeySpec);

    }



    /**
     * 读取公钥文件
     * @param PrivatPath 私钥路径
     * @param algorithm 算法
     * @return
     */
    public static PrivateKey getPrivatKey(String PrivatPath,String algorithm) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {

        // 1. 读取文件
        String s = FileUtils.readFileToString(new File(PrivatPath), Charset.forName("UTF-8"));
        // 2. 获取密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        // 3. 构建秘钥规范
        PKCS8EncodedKeySpec pes = new PKCS8EncodedKeySpec(Base64.decode(s));
        return keyFactory.generatePrivate(pes);

    }



    /**
     * 加密
     * @param input 原文
     * @param key 密钥
     * @param algorithm 算法
     * @return
     */
    public static String encryptRSA(String input,Key key,String algorithm) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // 1. 创建加密对象
        Cipher cipher = Cipher.getInstance(algorithm);
        // 2. 初始化加密
        cipher.init(Cipher.ENCRYPT_MODE,key);
        // 3. 公钥加密
        byte[] bytes = cipher.doFinal(input.getBytes());
        return Base64.encode(bytes);
    }

    /**
     * 解密
     * @param input 原文
     * @param key 密钥
     * @param algorithm 算法
     * @return
     */
    public static String decryptRSA(String input,Key key,String algorithm) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // 1. 创建加密对象
        Cipher cipher = Cipher.getInstance(algorithm);
        // 2. 初始化加密
        cipher.init(Cipher.DECRYPT_MODE,key);
        // 3. 由于密文base64编码
        byte[] decode = Base64.decode(input);
        // 4. 对密文解密
        byte[] bytes = cipher.doFinal(decode);
        return new String(bytes);
    }



}
