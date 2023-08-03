package org.example.common.encrypt;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

import cn.hutool.core.codec.Base64;
import lombok.extern.slf4j.Slf4j;
import org.example.common.json.JacksonUtil;

/**
 * 描述：加解密工具
 *
 * @author xutao
 * @Date 2023-02-26 22:45:55
 */
@Slf4j
public class CryptoUtil {

    /**
     * 描述：生成rsa公钥和私钥
     *
     * @param keySize 长度
     * @return {@link String }
     * @throws Exception 异常
     */
    public static String generateRsaPublicAndPrivateKey(int keySize) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);

        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();

        String publicKeyStr = Base64.encode(publicKey.getEncoded());
        String privateKeyStr = Base64.encode(privateKey.getEncoded());

        Map<String, String> map = new HashMap<>(2);
        map.put("publicKeyStr", publicKeyStr);
        map.put("privateKeyStr", privateKeyStr);
        return JacksonUtil.formatSerialize(map);
    }

}
