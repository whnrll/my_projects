package org.example.payment.zhifubao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 流控规则控制层
 */
@RestController
public class FlowLimitController {

    /**
     * 测试
     * @return
     */
    @GetMapping("testA")
    public String  testA(){
        return "testA";
    }

    /**
     * 测试关联模式
     * @return
     */
    @GetMapping("testB")
    public String  testB(){
        return "testB";
    }


    /**
     * 测试慢调用
     * RT 平均响应时间
     * @return
     */
    @GetMapping("testC")
    public String  testC(Integer id) throws InterruptedException {
        if (id == 1){
            TimeUnit.SECONDS.sleep(1);
        }

        return "testC";
    }


    /**
     * 测试异常比例
     * @return
     */
    @GetMapping("testD")
    public String  testD(Integer id) throws InterruptedException {
        if (id == 1){
            throw new RuntimeException("故意抛出异常，触发异常比例熔断");
        }
        return "testD";
    }

    /**
     * 测试异常数
     * @return
     */
    @GetMapping("testF")
    public String  testF(Integer id) throws InterruptedException {
        if (id == 1){
            throw new RuntimeException("故意抛出异常，触发异常数熔断");
        }
        return "testF";
    }
}
