package com.example.common.juc;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ExclusiveLockDemo implements Lock {
    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, @NotNull TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @NotNull
    @Override
    public Condition newCondition() {
        return null;
    }


    /**
     * 描述：自定义独占锁线程同步器
     * state=0 代表锁未被占用
     * state=1 代表锁已被占用
     *
     * @author xutao
     * @date 2023-07-07 23:13:06
     * @since 1.0.0
     */
    private static class Sync extends AbstractQueuedSynchronizer {
        /**
         * 描述：获取锁
         *
         * @param arg arg
         * @return boolean
         */
        @Override
        protected boolean tryAcquire(int arg) {
            assert arg == 1;

            // 使用 CAS 获取锁
            if (compareAndSetState(0, 1)) {
                // 设置抢占锁的线程为当前线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            assert arg == 1;

            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }

            // 设置抢占锁的线程为null
            setExclusiveOwnerThread(null);

            // 释放锁
            setState(0);
            return true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExclusiveLockDemo exclusiveLock = new ExclusiveLockDemo();

        new Thread(() -> {
            try {
                exclusiveLock.lock();
                System.out.println("thread1 get lock");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                exclusiveLock.unlock();
                System.out.println("thread1 release lock");
            }

        }).start();

        new Thread(() -> {
            try {
                exclusiveLock.lock();
                System.out.println("thread2 get lock");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                exclusiveLock.unlock();
                System.out.println("thread2 release lock");
            }

        }).start();

        Thread.currentThread().join();
    }

}
