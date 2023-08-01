package com.itheima.driver.controller;

import com.itheima.model.AlarmMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/driver/skywalking")
public class AlarmMessageController {

    /***
     * 接收警告信息
     * @param alarmMessageList
     */
    @PostMapping("/webhook")
    public void webhook(@RequestBody List<AlarmMessage> alarmMessageList) {
        for (AlarmMessage alarmMessage : alarmMessageList) {
            System.out.println("webhook:" + alarmMessage);
        }
    }
}