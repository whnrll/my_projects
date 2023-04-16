package com.example.common;

import com.exaple.common.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class ExceptionUtilTest {

    @Test
    public void t1() {
        try {
            throwE1();
        } catch (Exception e) {
            ExceptionUtil.print(e, log, "方法t1执行异常");
        }
    }

    @Test
    public void t2() {
        try {
            throwE1();
        } catch (Exception e) {
            ExceptionUtil.print(e, log, "方法{}执行异常", "t2");
        }
    }

    @Test
    public void t3() {
        try {
            throwE1();
        } catch (Exception e) {
            ExceptionUtil.print(e, log, "方法t3执行异常{}, {}", "t3");
        }
    }

    @Test
    public void t4() {
        try {
            throwE1();
        } catch (Exception e) {
            ExceptionUtil.printStack(e, log, "方法{}执行异常", "t4");
        }
    }


    private void throwE1() {
        int num = 1/0;
    }
}
