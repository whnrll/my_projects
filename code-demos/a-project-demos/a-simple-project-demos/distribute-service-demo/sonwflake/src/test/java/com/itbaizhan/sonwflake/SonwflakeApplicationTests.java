package com.itbaizhan.sonwflake;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.itbaizhan.sonwflake.config.SnowflakeConfig;
import com.itbaizhan.sonwflake.entity.User;
import com.itbaizhan.sonwflake.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
class SonwflakeApplicationTests {

    @Resource
    UserMapper userMapper;

    @Test
    void contextLoads() {
        //参数1为终端ID 机房
       //参数2为数据中心ID 机器
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        System.out.println(snowflake.nextId());
        System.out.println(snowflake.nextId());
        System.out.println(snowflake.nextId());
        System.out.println(snowflake.nextId());
        System.out.println(snowflake.nextId());
        System.out.println(snowflake.nextId());
        System.out.println(snowflake.nextId());
    }


    @Test
    void getId() {
        long id = new SnowflakeConfig().getId();
        System.out.println(id);
    }


    @Test
    void createUser() {

        User user = new User();
        user.setName("张三");
        user.setAge(18);
        user.setEmail("23472@qq.com");
        userMapper.insert(user);

    }
}
