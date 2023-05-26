package com.example.hotel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BaseSpringTest {
    @Test
    @DisplayName("测试服务是否可以启动")
    public void contextLoad() {}
}
