package org.example.limit;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述：限流算法-计数器限流
 *
 * @author xutao
 * @date 2023-07-10 22:38:46
 * @since 1.0.0
 */
public class CountLimit {
    /**
     * 窗口大小，单位：ms
     */
    private static final long INTERVAL = 1000;

    /**
     * 每个窗口最多放行3个请求
     */
    private static final long LIMIT = 3;

    /**
     * 窗口左边界
     */
    private static long leftTimeWindow = System.currentTimeMillis();

    private static AtomicInteger count = new AtomicInteger();

    /**
     * 描述：0---1---2---3
     *
     * @return boolean
     */
    public static boolean tryAcquire() {
        long nowTime = System.currentTimeMillis();
        String name = Thread.currentThread().getName();

        if (count.incrementAndGet() <= LIMIT && nowTime < leftTimeWindow + INTERVAL) {
            System.out.println(name + "，累计请求数：" + count.get());
            return true;
        }

        // 还在统计的时间窗口
        if (nowTime < leftTimeWindow + INTERVAL) {
            System.out.println(name + "请稍后重试。当前窗口累计请求数：" + count.get());
            return false;
        }

        // 不在统计的时间窗口，重新初始化时间窗口
        synchronized (CountLimit.class) {
            if (nowTime > leftTimeWindow + INTERVAL) {
                System.out.println("初始化时间窗口");
                leftTimeWindow = nowTime;
                count.set(0);
            }
        }

        if (count.incrementAndGet() <= LIMIT && nowTime < leftTimeWindow + INTERVAL) {
            System.out.println(name + "，累计请求数：" + count.get());
            return true;
        }

        System.out.println(name + "请稍后重试。当前窗口累计请求数：" + count.get());
        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        // 线程池，用于多线程模拟测试
        ExecutorService pool = Executors.newFixedThreadPool(10);
        // 被限制的次数
        AtomicInteger limited = new AtomicInteger(0);
        // 线程数
        final int threads = 2;
        // 每条线程的执行轮数
        final int turns = 20;
        // 同步器
        CountDownLatch countDownLatch = new CountDownLatch(threads);
        long start = System.currentTimeMillis();
        for (int i = 0; i < threads; i++) {
            pool.submit(() -> {
                try {
                    for (int j = 0; j < turns; j++) {
                        boolean flag = tryAcquire();
                        if (!flag) {
                            // 被限制的次数累积
                            limited.getAndIncrement();
                        }
                        Thread.sleep(200);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 等待所有线程结束
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        float time = (System.currentTimeMillis() - start) / 1000F;
        // 输出统计结果
        System.out.println("限制的次数为：" + limited.get() + ",通过的次数为：" + (threads * turns - limited.get()));
        System.out.println("限制的比例为：" + (float)limited.get() / (float)(threads * turns));
        System.out.println("运行的时长为：" + time + "s");
    }
}
