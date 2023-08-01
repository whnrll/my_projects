package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.config.HttpClientPoolConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ConfigTest extends MySpringBootTest{
    @Autowired
    private HttpClientPoolConfig httpClientPoolConfig;

    @Test
    public void t1() {
        System.out.println(httpClientPoolConfig);
    }
}
