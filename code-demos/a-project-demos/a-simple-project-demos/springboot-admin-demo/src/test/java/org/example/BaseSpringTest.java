package org.example;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 描述：基础 Spring 测试，其他需要 Spring 测试环境的继承该类即可
 *
 * @author xutao
 * @Date 2023-02-26 18:40:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootAdminApplication.class)
public class BaseSpringTest {
}
