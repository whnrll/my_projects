package com.itbaizhan.itbaizhanlock.utils;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁
 */
@Slf4j
@Component
public class DistributedRedisLock {

    @Autowired
    private RedissonClient redissonClient;


    /**
     * 加锁
     *
     * @param lockNmae
     * @return
     */
    public Boolean lock(String lockNmae) {
        // 1. 判断 redisclient是否为空
        if (redissonClient == null) {
            log.info("DistributedRedisLock redission  client  is  null");
            return false;
        }


        try {
            // 2. 加锁
            RLock lock = redissonClient.getLock("anyLock");
            // 3. 添加过期时间
            // 加锁以后10秒钟自动解锁
            // 无需调用unlock方法手动解锁
            lock.lock(10, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 释放锁
     *
     * @param lockNmae
     * @return
     */
    public Boolean unlock(String lockNmae) {
        // 1. 判断 redisclient是否为空
        if (redissonClient == null) {
            log.info("DistributedRedisLock redission  client  is  null");
            return false;
        }
        try {
            RLock lock = redissonClient.getLock("anyLock");
            lock.unlock();
            // 释放锁
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
