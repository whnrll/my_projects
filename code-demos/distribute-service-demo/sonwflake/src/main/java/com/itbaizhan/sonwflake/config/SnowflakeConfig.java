package com.itbaizhan.sonwflake.config;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SnowflakeConfig {

    // 机房
    private long workerId = 0;
    // 机器
    private long datacenterId = 1;

    Snowflake snowflake = IdUtil.getSnowflake(workerId, datacenterId);


    @PostConstruct //  构造完成之后就执行初始化工作
    public void init(){
        workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
    }

    /**
     * 生成id
     * @return
     */
    public synchronized long getId(){
        return snowflake.nextId();
    }

}
