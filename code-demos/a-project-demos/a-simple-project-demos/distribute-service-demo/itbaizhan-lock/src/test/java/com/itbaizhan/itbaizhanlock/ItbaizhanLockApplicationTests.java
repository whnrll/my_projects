package com.itbaizhan.itbaizhanlock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class ItbaizhanLockApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void method1() {
        Boolean result = stringRedisTemplate.opsForValue().setIfAbsent("lock", "thread1", 30, TimeUnit.SECONDS);
        if (!result) {
            System.out.println("获得锁失败");
            return;
        }
        try {
            System.out.println("获得锁成功");
            // TODO 执行业务 创建订单
            method2();
        } finally {
            // 释放锁
            stringRedisTemplate.delete("lcok");
        }
    }
    @Test
    void method2() {
        Boolean result = stringRedisTemplate.opsForValue().setIfAbsent("lock", "thread1", 30, TimeUnit.SECONDS);
        if (!result) {
            System.out.println("获得锁失败,2");
            return;
        }
        try {
            System.out.println("获得锁成功， 2");
            // TODO 执行业务 创建订单
        } finally {
            // 释放锁
            stringRedisTemplate.delete("lcok");
        }
    }
}
