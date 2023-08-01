package org.example.redis;

import io.micrometer.core.instrument.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.example.MySpringBootTest;
import org.example.dto.UserDTO;
import org.example.dto.WebApiPic;
import org.example.util.JacksonUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class RedisTemplateTest extends MySpringBootTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    @DisplayName("set key value")
    public void set() {
        // 注意：RedisTemplate 使用的默认序列化器是 JdkSerializationRedisSerializer。在 redis 上的 key 实际会包含 \xx\xx 的前缀
        redisTemplate.opsForValue().set("test", "1");
        String test = (String) redisTemplate.opsForValue().get("test");
        Assertions.assertEquals("1", test);

        // 注意：StringRedisTemplate 使用的默认序列化器是 RedisSerializer
        stringRedisTemplate.opsForValue().set("test2", "1");
        String test2 = stringRedisTemplate.opsForValue().get("test2");
        Assertions.assertEquals("1", test2);
    }

    @Test
    @DisplayName("set key object")
    public void set2() {
        WebApiPic webApiPic = new WebApiPic();
        webApiPic.setPic(new ArrayList<>());
        webApiPic.getPic().add("p1");

        redisTemplate.opsForValue().set("pics", webApiPic);
        WebApiPic pics = (WebApiPic) redisTemplate.opsForValue().get("pics");
        System.out.println(pics);
    }

    @Test
    @DisplayName("set key object")
    public void set3() {
        UserDTO user1 = UserDTO.builder().id(1L).name("user1").age(15).build();
        redisTemplate.opsForValue().set("users:1", JacksonUtil.toJson(user1));

        String result = (String) redisTemplate.opsForValue().get("users:1");
        UserDTO userDTO = JacksonUtil.fromJSON(result, UserDTO.class);
        System.out.println(userDTO);
    }
}
