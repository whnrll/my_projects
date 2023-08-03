package org.example.common.service.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import org.example.common.exception.ComponentException;
import org.example.common.exception.constants.ApplicationErrorCodeEnum;
import org.springframework.integration.redis.util.RedisLockRegistry;

import lombok.extern.log4j.Log4j2;

/**
 * 描述：Redis 分布式锁组件
 * Spring Integration 对Redis分布式锁处理做了封装，
 * 它是一个轻量级的ESB（企业服务总线）， 提供了基于Spring的EIP(Enterprise Integration Patterns, 企业集成模式)的实现。
 * 采用Spring Integration 作为分布式锁实现方案， 是因为Spring较高抽象设计能力， 可以支持接入众多存储组件作为分布式锁的实现方案，
 * 比如GemFire（高性能的分布式内存对象缓存系统）、JDBC数据库、Redis、Zookeeper，如果要替换其他存储组件， 不用再修改代码， 更改配置即可。
 *
 * 在使用分布式锁之前， 需要考虑以下问题， 锁的粒度是到什么？ 如果没有拿到锁，该如何处理？
 * 
 *
 * @author xutao
 * @date 2023-08-03 20:22:13
 * @since 1.0.0
 */
@Log4j2
public class RedisLockService {

    private RedisLockRegistry redisLockRegistry;

    private int waitTime;

    /**
     * 初始化
     * 
     * @param redisLockRegistry
     * @param waitTime
     */
    public RedisLockService(RedisLockRegistry redisLockRegistry, int waitTime) {
        this.redisLockRegistry = redisLockRegistry;
        this.waitTime = waitTime;
    }

    /**
     * 尝试加锁，不成功，则抛出异常
     * 
     * @return
     * @throws ComponentException
     */
    public boolean tryLock(Object key) throws ComponentException {
        try {
            Lock lock = redisLockRegistry.obtain(String.valueOf(key));
            boolean isLock = lock.tryLock(waitTime, TimeUnit.SECONDS);
            if (!isLock) {
                throw new ComponentException(ApplicationErrorCodeEnum.WAITING_UNLOCK);
            }
            return true;
        } catch (ComponentException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ComponentException(ApplicationErrorCodeEnum.TRY_LOCK_FAIL);
        }

    }

    /**
     * 解锁处理
     * 
     * @return
     * @throws ComponentException
     */
    public void unlock(Object key) {
        try {
            Lock lock = redisLockRegistry.obtain(String.valueOf(key));
            lock.unlock();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
