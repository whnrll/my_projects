package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 描述：模拟 http 请求阻塞
 *
 * @author xutao
 * @date 2023-04-14 22:59:20
 * @since 1.0.0
 */
@RestController
@RequestMapping("/http")
@Slf4j
public class HttpController {

    /**
     * 描述：睡眠 sleepSecondTimes 秒后返回 ok
     *
     * @param sleepSecondTimes 睡眠时间，秒
     * @return {@link String }
     */
    @GetMapping("/block/{sleepSecondTimes}")
    public String block(@PathVariable("sleepSecondTimes") long sleepSecondTimes) {
        try {
            log.info("开始睡眠：{}秒", sleepSecondTimes);
            TimeUnit.SECONDS.sleep(sleepSecondTimes);
            return "ok";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
