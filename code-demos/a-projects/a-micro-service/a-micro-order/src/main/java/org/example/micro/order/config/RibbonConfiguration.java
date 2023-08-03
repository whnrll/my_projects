package com.itheima.order.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RibbonConfiguration {

    /***
     * 负载均衡算法设置
     */
    @Bean
    public IRule randomRule(){
        // 轮询算法
//        return new RoundRobinRule();
        //随机算法
        return new RandomRule();
        //重试算法
//        return new RetryRule();
        //加权法
        //return new ZoneAvoidanceRule();
    }
}
