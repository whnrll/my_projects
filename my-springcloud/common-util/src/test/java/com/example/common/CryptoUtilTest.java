package com.example.common;

import com.exaple.common.util.CryptoUtil;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CryptoUtilTest {
    @Test
    public void generateKeyBytes() throws Exception {
        String key = CryptoUtil.generateRsaPublicAndPrivateKey(2048);
        System.out.println(key);
    }
}
