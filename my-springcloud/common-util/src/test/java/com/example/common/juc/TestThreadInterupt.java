package com.example.common.juc;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class TestThreadInterupt {

    @Test
    public void testInterrupted() {
        boolean isInterrupted = Thread.currentThread().isInterrupted();
        log.info("是否被中断：{}", isInterrupted);

        boolean interrupted = Thread.interrupted();
        log.info("线程中断：{}", interrupted);

    }
}
