package com.itbaizhan.stock.message;

import com.alibaba.fastjson.JSONObject;
import com.itbaizhan.stock.service.IStockService;
import com.itbaizhan.stock.tx.TxMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RocketMQMessageListener(consumerGroup = "tx_stock_group",topic = "topic_txmsg")
public class StockTxMessageConsumer implements RocketMQListener<String> {

    @Autowired
    IStockService iStockService;

    @Override
    public void onMessage(String s) {
        log.info("**************** 库存微服务开始消费事务消息 ：{}",s);
        // 1.获取事务消息
        TxMessage txMessage = this.getTxMessage(s);
        // 2. 减库存
        iStockService.updateStock(txMessage);

    }

    private TxMessage getTxMessage(String  message){
        // 2. 字符串 转对象
        JSONObject jsonObject = JSONObject.parseObject(message);
        String message1 = jsonObject.getString("message");
        TxMessage txMessage = JSONObject.parseObject(message1, TxMessage.class);
        return txMessage;
    }

}
