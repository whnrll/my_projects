package com.muke.service;

import com.muke.dao.SkillOrderRepository;
import com.muke.entity.SkillEntity;
import com.muke.entity.SkillGood;
import com.muke.entity.SkillOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import static com.muke.service.SkillGoodService.SKILL_GOODS_ONLY;
import static com.muke.service.SkillGoodService.SKILL_GOODS_QUEUE;

@Component
public class MutilThreadOrder {
    @Autowired
    private ProductService productService;
    @Autowired
    private SkillOrderRepository skillOrderRepository;
    @Autowired
    private RedisTemplate redisTemplate;


    @Async
    public void createOrder() throws Exception {
        System.out.println("开始异步抢单");
        SkillEntity skillEntity = (SkillEntity) redisTemplate.boundListOps(SkillGoodService.SKILL_GOODS_LIST).rightPop();
        if (skillEntity == null) {
            return;
        }
        Long productId = skillEntity.getProductId();
        String userId = skillEntity.getUserId();
        SkillGood skillGood = productService.getGoodById(productId);
        if (skillGood == null) {
            throw new Exception("商品已经被抢光拉");
        }
        Long stockId = (Long) redisTemplate.boundListOps(SKILL_GOODS_QUEUE + productId).rightPop();
        if (stockId == null) {
            System.out.println("该商品已被秒杀完毕");
            redisTemplate.boundHashOps(SKILL_GOODS_ONLY).delete(userId);
            redisTemplate.boundHashOps(SkillGoodService.SKILL_GOODS_PHONE).delete(skillGood.getId());
            skillGood.setStockCount(0);
            productService.update(skillGood);
            return;
        }
        SkillOrder skillOrder = new SkillOrder();
        skillOrder.setMoney(skillGood.getCostPrice());
        skillOrder.setPayTime(new Date());
        skillOrder.setStatus("0");
        skillOrder.setUserId(userId);
        skillOrder.setCreateTime(new Date());
        skillOrder.setSkillId(productId);
        skillOrderRepository.save(skillOrder);
        System.out.println("结束异步抢单");
    }
}
