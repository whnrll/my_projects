package com.hmdp;

import com.hmdp.utils.RedisIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class RedisIdWorkerTest extends HmDianPingApplicationTests {
    @Autowired
    private RedisIdWorker redisIdWorker;

    private static ExecutorService es = Executors.newFixedThreadPool(500);

    @BeforeAll
    public static void beforeAll() {

    }

    @AfterAll
    public static void afterAl() {

    }

    @Test
    @DisplayName("生成自增ID")
    public void generateDistributeID() {
        // 模拟300个线程并发生成id
        CountDownLatch count = new CountDownLatch(30);

        long beginTimestamp = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        for (int i = 0; i < 30; i++) {
            es.submit(() -> {
                // 模拟每个线程生成100个id
                for (int j = 0; j < 100; j++) {
//                    long orderId = redisIdWorker.nextId("order");
                    long orderId = redisIdWorker.nextId2();
                    System.out.println("orderId: " + orderId);
                }

                count.countDown();
            });


        }

        try {
            count.await();
        } catch (InterruptedException e) {
            log.error("主线程被中断，错误信息：{}", e.getMessage());
        }

        long endTimestamp = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        System.out.println("生成ID耗时：" + (endTimestamp - beginTimestamp) + "ms");
    }
}
