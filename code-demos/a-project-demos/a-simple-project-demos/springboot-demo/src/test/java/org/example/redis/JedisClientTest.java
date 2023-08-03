package org.example.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * 描述：Jedis 使用测试
 *
 * @author xutao
 * @date 2023-04-06 23:50:19
 * @since 1.0.0
 */
@Slf4j
public class JedisClientTest {
    private static JedisPool pool;
    @BeforeAll
    public static void beforeAll() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大空闲连接
        poolConfig.setMaxIdle(10);
        // 最大连接
        poolConfig.setMaxTotal(15);
        // 最小连接
        poolConfig.setMinIdle(5);
        // 最大等待时间
        poolConfig.setMaxWait(Duration.ofMillis(1000));

        pool = new JedisPool(poolConfig,"bigdata01", 6379, 1000, "001225");
    }

    @AfterAll
    public static void afterAll() {
        pool.close();
    }

    @Test
    @DisplayName("演示 String 类型 set/get")
    public void t1() {
        try(Jedis jedis = pool.getResource()) {
            String result = jedis.set("user", "xt");
            log.info("set key result={}", result);

            String user = jedis.get("user");
            log.info("get key result={}", user);
        }
    }

    @Test
    @DisplayName("演示 List 类型 lpush")
    public void t2() {
        try(Jedis jedis = pool.getResource()) {
            long result = jedis.lpush("students", "s1", "s2");
            log.info("lpush result={}", result);
        }
    }
}
