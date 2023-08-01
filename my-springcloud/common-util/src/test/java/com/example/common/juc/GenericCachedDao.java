package com.example.common.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 描述：模拟真实场景缓存更新策略
 *
 * @author xutao
 * @date 2023-07-07 22:03:48
 * @since 1.0.0
 */
@Slf4j
public class GenericCachedDao {
    /**
     * 这里使用 JVM 缓存模拟 redis
     */
    Map<String, String> cache = new HashMap<>();

    /**
     * 这里使用 JVM 缓存模拟 数据库
     */
    Map<String, String> dbData = new HashMap<>();

    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    // 读取操作
    public String getData(String key) {
        // 加读锁，防止其他线程修改缓存
        readWriteLock.readLock().lock();
        try {
            String value = cache.get(key);
            // 如果缓存命中，返回
            if(value != null) {
                return value;
            }
        } finally {
            // 释放读锁
            readWriteLock.readLock().unlock();
        }

        //如果缓存没有命中，从数据库中加载
        readWriteLock.writeLock().lock();
        try {
            // 细节，为防止重复查询数据库, 再次验证
            // 因为get 方法上面部分是可能多个线程进来的, 可能已经向缓存填充了数据
            String value = cache.get(key);
            if(value == null) {
                value = getDataFromDb(key);
                cache.put(key, value);
            }
            return value;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    // 更新数据
    public void updateData(String key, String value) {
        // 加写锁
        readWriteLock.writeLock().lock();
        try {
            // 更新 db
            updateDataToDb(key, value);

            // 清空缓存
            cache.remove(key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    private String getDataFromDb(String key) throws InterruptedException {
        // 等待 300ms 模拟数据库查询耗时
        Thread.sleep(300);
        return dbData.get(key);
    }

    private String updateDataToDb(String key, String value) throws InterruptedException {
        // 等待 1000ms 模拟数据库更新耗时
        Thread.sleep(1000);
        return dbData.put(key, value);
    }

    public static void main(String[] args) throws InterruptedException {
        // 待更新的key
        String key = "rwLock";
        GenericCachedDao cachedDao = new GenericCachedDao();
        cachedDao.cache.put(key, "init");
        cachedDao.dbData.put(key, "init");

        // 启动300个线程查询缓存
        for (int i = 0; i < 300; i++) {
            int random = new Random().nextInt(1000);
            if (random > 900) {
                // 更新数据
                int finalI = i;
                Thread thread = new Thread(() -> {
                    String newValue = "up-to-" + finalI;
                    log.info("更新数据为：{}", newValue);
                    cachedDao.updateData(key, newValue);
                }, "update-thread-" + i);
                thread.start();
            } else {
                // 查询数据
                Thread thread = new Thread(() -> {
                    log.info("查询结果：{}", cachedDao.getData(key));
                }, "query-thread-" + i);
                thread.start();
            }
        }

        Thread.sleep(60000);
        System.out.println("数据更新结束");
        System.out.println("cache:" + cachedDao.cache.get(key));
        System.out.println("db:" + cachedDao.dbData.get(key));
    }
}
