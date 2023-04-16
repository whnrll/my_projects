package com.hmdp.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * 描述：Redis 分布式ID 生成器
 *
 * @author xutao
 * @date 2023-04-10 21:18:27
 * @since 1.0.0
 */
@Component
@Slf4j
public class RedisIdWorker {
    /**
     * 开始时间戳
     */
    private static final long BEGIN_TIMESTAMP = LocalDateTime.of(2023, 1, 1, 8, 0, 0).toEpochSecond(ZoneOffset.UTC);

    /**
     * 序列号位数
     */
    private static final int COUNT_BITS = 32;

    private static final String DISTRIBUTE_ID_PREFIX = "icr:";

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 描述：生成业务的分布式ID。
     *
     * @param businessCode 业务码
     * @return long
     */
    public long nextId(String businessCode) {
        // 生成时间戳
        LocalDateTime now = LocalDateTime.now();
        long timestamp = now.toEpochSecond(ZoneOffset.UTC) - BEGIN_TIMESTAMP;

        // 获取当前日期，精确到天
        String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy:MM:dd"));

        // 使用 redis 自增命令生成序列号
        long count = redisTemplate.opsForValue().increment(DISTRIBUTE_ID_PREFIX + businessCode + ":" + nowDate);
        return timestamp << COUNT_BITS | count;
    }

    public long nextId2() {
        return redisTemplate.opsForValue().increment("icr:distribute:id");
    }
}
