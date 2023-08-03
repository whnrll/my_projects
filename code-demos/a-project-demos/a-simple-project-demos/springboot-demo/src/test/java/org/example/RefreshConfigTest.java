package org.example;

import java.util.HashMap;
import java.util.Map;

import org.example.refresh.RefreshConfig;
import org.example.refresh.RefreshConfigUtil;
import org.example.util.UUIDUtil;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

/**
 * 描述：刷新配置测试
 *
 * @author xutao
 * @Date 2023-02-27 23:49:43
 */
@Slf4j
public class RefreshConfigTest extends MySpringBootTest {
    @Autowired
    private RefreshConfig refreshConfig;

    @Test
    public void t1() {
        System.out.println(refreshConfig);
    }

    @Test
    public void t2() {
        Assertions.assertEquals("xt", refreshConfig.getName());
        RefreshConfigUtil.updateValue("name", "xxx");
        Assertions.assertEquals("xxx", refreshConfig.getName());
    }

    @Test
    public void t3() {
        System.out.println("更新前：" + refreshConfig);
        Map<String, Object> map = new HashMap<>();
        map.put("name", UUIDUtil.generateUUID());
        map.put("list", "001,002");
        map.put("undefine", "xxx");
        RefreshConfigUtil.updateAll(map);
        System.out.println("更新后：" + refreshConfig);
    }
}
