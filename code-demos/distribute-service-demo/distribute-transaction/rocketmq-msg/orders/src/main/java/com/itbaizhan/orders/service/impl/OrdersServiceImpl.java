package com.itbaizhan.orders.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.itbaizhan.orders.entity.Orders;
import com.itbaizhan.orders.entity.TxLog;
import com.itbaizhan.orders.mapper.OrdersMapper;
import com.itbaizhan.orders.mapper.TxLogMapper;
import com.itbaizhan.orders.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itbaizhan.orders.tx.TxMessage;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.lang.model.element.VariableElement;
import java.time.LocalDateTime;
import java.util.UUID;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author itbaizhan
 * @since 05-21
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

    @Resource
    RocketMQTemplate rocketMQTemplate;

    @Resource
    private TxLogMapper txLogMapper;

    /**
     * 提交订单
     * @param productId 商品编号
     * @param payCount 购买数量
     */
    @Override
    public void submitOrders(Long productId, Integer payCount) {

        // 1. 生成全局分布式事务编号
        String txNo = UUID.randomUUID().toString().replace("-","");
        // 2. 封装消息
        TxMessage txMessage = new TxMessage();
        txMessage.setTxNo(txNo);
        txMessage.setPayCount(payCount);
        txMessage.setProductId(productId);
        // 3. 转json
        JSONObject jsonObject = new JSONObject();
        //  {“message”:{"商品id"：1，"购买数量“:1}}
        jsonObject.put("message",txMessage);
        Message<String> message = MessageBuilder.withPayload(jsonObject.toJSONString()).build();
        // 发送事务消息  消息不允许消费
        rocketMQTemplate.sendMessageInTransaction("tx_order_group","topic_txmsg",message,null);

    }


    /**
     * 添加订单
     * @param productId 商品编号
     * @param payCount 购买数量
     */
    @Override
    public void save(Long productId, Integer payCount) {
        Orders orders = new Orders();
        orders.setCreateTime(LocalDateTime.now());
        orders.setOrderNo(UUID.randomUUID().toString().replace("-",""));
        orders.setProductId(productId);
        orders.setPayCount(payCount);
        baseMapper.insert(orders);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void sumbitOrderAndSaveTxNo(TxMessage txMessage) {
        // 1. 根据事务id查询事务日志信息
        TxLog txLog = txLogMapper.selectById(txMessage.getTxNo());
        // 2. 判断事务是否存在
        if (txLog != null){
            return ;
        }
        // 3. 生成订单
        this.save(txMessage.getProductId(),txMessage.getPayCount());

        // 4. 添加事务日志

        TxLog txLog1 = new TxLog();
        txLog1.setTxNo(txMessage.getTxNo());
        txLog1.setCreateTime(LocalDateTime.now());
        txLogMapper.insert(txLog1);
    }


}
