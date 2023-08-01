package com.itbaizhan.payment.service.impl;

import com.alibaba.fastjson.JSON;
import com.itbaizhan.payment.entity.PayInfo;
import com.itbaizhan.payment.mapper.PayInfoMapper;
import com.itbaizhan.payment.service.IPayInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author itbaizhan
 * @since 05-23
 */
@Slf4j
@Service
public class PayInfoServiceImpl extends ServiceImpl<PayInfoMapper, PayInfo> implements IPayInfoService {


    @Resource
    RocketMQTemplate template;

    /**
     * 添加交易记录
     * @param accountNo 账户编号
     * @param payAmount 金额
     */
    @Override
    public PayInfo savePayment(String accountNo, BigDecimal payAmount) {
        PayInfo payInfo = new PayInfo();
        // 交易水流id
        payInfo.setTxNo(UUID.randomUUID().toString().replace("-",""));
        payInfo.setAccountNo(accountNo);
        payInfo.setPayAmount(payAmount);
        payInfo.setPayResult("success");
        payInfo.setPayTime(LocalDateTime.now());
        // 保存数据库
        int result = baseMapper.insert(payInfo);
        if (result > 0){
            log.info("************ 充值微服务向账户微服务发送消息 ******");
            template.convertAndSend("topic_nofitymsg", JSON.toJSONString(payInfo));
            return payInfo;
        }
      return null;
    }

    /**
     * 查询交易记录
     * @param txNo 交易流水
     * @return
     */
    @Override
    public PayInfo getById(String txNo) {
        return baseMapper.selectById(txNo);
    }
}
