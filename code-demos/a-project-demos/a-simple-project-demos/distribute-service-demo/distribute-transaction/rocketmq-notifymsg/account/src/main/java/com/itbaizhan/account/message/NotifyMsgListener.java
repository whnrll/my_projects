package com.itbaizhan.account.message;

import com.alibaba.fastjson.JSON;
import com.itbaizhan.account.entity.PayInfo;
import com.itbaizhan.account.service.IAccountInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 监听通知消息
 */
@Component
@Slf4j
@RocketMQMessageListener(consumerGroup = "consumer_group",topic = "topic_nofitymsg")
public class NotifyMsgListener implements RocketMQListener<String> {

    @Autowired
    private IAccountInfoService iAccountInfoService;

    @Override
    public void onMessage(String s) {
        log.info("账户微服务接受到了RocketMQ 消息 :{}",s);
        // 1. 字符串转对象
        PayInfo payInfo = JSON.parseObject(s, PayInfo.class);
        // 2. 判断这个消息是否是成功
        if (payInfo != null && "success".equals(payInfo.getPayResult())){
            // TODO 更新账户操作
            iAccountInfoService.updateAccount(payInfo);
        }

        log.info("更新账户余额完毕:{}",s);

    }


}
