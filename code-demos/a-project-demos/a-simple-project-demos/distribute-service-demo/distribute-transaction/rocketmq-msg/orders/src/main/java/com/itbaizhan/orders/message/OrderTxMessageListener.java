package com.itbaizhan.orders.message;

import com.alibaba.fastjson.JSONObject;
import com.itbaizhan.orders.entity.TxLog;
import com.itbaizhan.orders.mapper.TxLogMapper;
import com.itbaizhan.orders.service.IOrdersService;
import com.itbaizhan.orders.tx.TxMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 监听事务消息
 */
@Component
@Slf4j
@RocketMQTransactionListener(txProducerGroup = "tx_order_group")
public class OrderTxMessageListener implements RocketMQLocalTransactionListener {

    @Autowired
    private IOrdersService iOrdersService;


    @Resource
    private TxLogMapper txLogMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        log.info("***************** 订单微服务执行本地事务 **********");
        try {
            // 1.获取消息
            TxMessage txMessage = this.getTxMessage(message);
            // 2. 本地事务
            iOrdersService.sumbitOrderAndSaveTxNo(txMessage);
            log.info("订单微服务提交事务");

            // commit 生产者通知rockatmq消息 可以消费
            return RocketMQLocalTransactionState.COMMIT;
        }catch (Exception e){
            e.printStackTrace();
            log.info("订单微服务回滚事务");
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }


    /**
     * 回查事务
     * 因为网络异常或者其他原因  导致Rocketmq 消息状态 UNKNOWN
     * @param message
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        log.info("*************  回查订单事务 ************");
        // 1. 获取事务消息
        TxMessage txMessage = this.getTxMessage(message);
        // 2. 根据事务id查询事务信息
        TxLog txLog = txLogMapper.selectById(txMessage.getTxNo());
        // 3. 判断事务存在
        if (txLog != null){
            return RocketMQLocalTransactionState.COMMIT;
        }
        //UNKNOWN 生产者通知rocketmq继续查询消息的状态
          return RocketMQLocalTransactionState.UNKNOWN;
    }


    private TxMessage getTxMessage(Message message){
        String messageStr = new String((byte[]) message.getPayload());
        // 2. 字符串 转对象
        JSONObject jsonObject = JSONObject.parseObject(messageStr);
        String message1 = jsonObject.getString("message");
        TxMessage txMessage = JSONObject.parseObject(message1, TxMessage.class);
        return txMessage;
    }

}
