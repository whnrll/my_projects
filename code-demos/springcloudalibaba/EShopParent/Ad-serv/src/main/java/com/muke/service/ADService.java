package com.muke.service;

import com.muke.entity.AdEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ADService {
    @Autowired
    private RedisTemplate redisTemplate;
    public static final String AD_LIST = "AD_LIST";

    public List<AdEntity> getADList() {
        List<AdEntity> list = redisTemplate.boundHashOps(AD_LIST).values();
        return list;
    }

    public void modify(AdEntity adEntity){
        redisTemplate.boundHashOps(AD_LIST).put(adEntity.getId(),adEntity);
    }

    public void delete(Integer id){
        redisTemplate.boundHashOps(AD_LIST).delete(id);
    }
    @Scheduled(cron = "0/2 * * * * ?")
    public void prepareGood() {
        Set keys = redisTemplate.boundHashOps(AD_LIST).keys();
        int i=0;
        for (Object s : keys) {
            i++;
            System.out.println( " 广告剩余：" + i);
        }
    }
}
