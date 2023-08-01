package com.example.common.juc;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class TestReentrantReadWrite {
    /**
     * 描述：验证读读共享模式
     *
     * @throws InterruptedException
     */
    @Test
    public void readReadMode() throws InterruptedException {
        ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock r = rw.readLock();
        ReentrantReadWriteLock.WriteLock w = rw.writeLock();

        Thread thread0 = new Thread(() -> {
            r.lock();
            try {
                Thread.sleep(2000);
                System.out.println("Thread 1 running " + new Date());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                r.unlock();
            }
        },"t1");

        Thread thread1 = new Thread(() -> {
            r.lock();
            try {
                Thread.sleep(2000);
                System.out.println("Thread 2 running " + new Date());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                r.unlock();
            }
        },"t2");

        thread0.start();
        thread1.start();

        thread0.join();
        thread1.join();
    }

    /**
     * 描述：验证读写互斥模式
     *
     * @throws InterruptedException
     */
    @Test
    public void readWriteMode() throws InterruptedException {
        ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock r = rw.readLock();
        ReentrantReadWriteLock.WriteLock w = rw.writeLock();

        Thread thread0 = new Thread(() -> {
            r.lock();
            try {
                Thread.sleep(2000);
                System.out.println("Thread 1 running " + new Date());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                r.unlock();
            }
        },"t1");

        Thread thread1 = new Thread(() -> {
            w.lock();
            try {
                Thread.sleep(2000);
                System.out.println("Thread 2 running " + new Date());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                w.unlock();
            }
        },"t2");

        thread0.start();
        thread1.start();

        thread0.join();
        thread1.join();
    }


}
