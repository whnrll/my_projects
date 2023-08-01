package org.example.refresh;

import org.springframework.beans.factory.annotation.Value;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

/**
 * 描述：更新配置
 *
 * @author xutao
 * @Date 2023-02-27 22:48:26
 */
@Data
@Configuration
@RefreshScope
@PropertySource(name = "dynamic", value = "classpath:application-dynamic.properties")
public class RefreshConfig {
    @Value("${name}")
    private String name;

    @Value("${list}")
    private List<String> list;
}
