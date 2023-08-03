package org.example.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.example.service.IRefreshService;
import org.example.util.UUIDUtil;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.stereotype.Service;

@Service
public class RefreshConfigImpl implements IRefreshService, EnvironmentAware {
    private Environment environment;

    @Override
    public void updateConfig() {
        // 模拟配置文件更新
        Map<String, Object> config = new HashMap<>();
        config.put("refresh.name", UUIDUtil.generateUUID());
        config.put("refresh.desc", UUIDUtil.generateUUID());

        MapPropertySource propertySource = new MapPropertySource("refresh_config", config);
        if (environment instanceof ConfigurableEnvironment) {
            ConfigurableEnvironment configurableEnvironment = (ConfigurableEnvironment) environment;
            configurableEnvironment.getPropertySources().addFirst(propertySource);
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
