package com.example.common.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

@Slf4j
public class ShareLockDemo {
    private final Sync sync;

    public ShareLockDemo(int shareLockCount) {
        this.sync = new Sync(shareLockCount);
    }

    /**
     * 描述：加锁（获取不到锁会阻塞当前线程）
     */
    public void lock() {
        sync.acquireShared(1);
    }

    /**
     * 描述：锁锁（获取不到锁会阻塞当前线程）
     */
    public void unlock() {
        sync.releaseShared(1);
    }

    private static class Sync extends AbstractQueuedSynchronizer {
        /**
         * 共享锁可以被几个线程同时获取
         */
        private final int count;

        public Sync(int count) {
            this.count = count;
        }

        @Override
        protected int tryAcquireShared(int arg) {
            assert arg == 1;

            // 自旋
            for (;;) {
                // 检查共享锁释锁是否还可以被获取
                int c = getState();

                // 表示共享锁不能被获取，线程阻塞等待获取锁
                if (c >= count) {
                    return -1;
                }

                int next = c + 1;
                // cas 尝试获取共享锁
                if (compareAndSetState(c, next)) {
                    return 1;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            assert arg == 1;

            // 自旋
            for (;;) {
                int c = getState();

                // 没有锁可以释放了
                if (c == 0) {
                    return false;
                }

                // 释放锁
                int next = c - 1;
                if (compareAndSetState(c, next)) {
                    return true;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 测试共享锁
        ShareLockDemo shareLockDemo = new ShareLockDemo(3);


        for (int i = 0; i < 7; i++) {
            Thread thread = new Thread(() -> {
                shareLockDemo.lock();
                try {
                    log.info("lock success");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    log.error("lock interrupted ", e);
                } finally {
                    shareLockDemo.unlock();
                    log.info("unlock success");
                }
            });

            thread.start();
        }

        Thread.currentThread().join();
    }
}
