package org.example.refresh;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class RefreshConfigUtil implements EnvironmentAware {
    private static ConfigurableEnvironment environment;

    @Override
    public void setEnvironment(Environment environment) {
        RefreshConfigUtil.environment = (ConfigurableEnvironment) environment;
    }

    /**
     * 描述：更新所有自定义配置文件中的属性
     *
     * @param newValue 新值
     */
    public static void updateAll(Map<String, Object> newValue) {
        MutablePropertySources propertySources = environment.getPropertySources();

        propertySources.stream().forEach(propertySource -> {
            String name = propertySource.getName();
            String type = propertySource.getClass().getName();
            log.info("propertySource name is: [{}], type is: [{}]", name, type);
            if (propertySource instanceof MapPropertySource) {
                MapPropertySource mapPropertySource = (MapPropertySource) propertySource;
                if (name.equals("dynamic")) {
                    Map<String, Object> source = mapPropertySource.getSource();

                    // TODO 新旧 source MD5 后比较是否一致，不一致则更新 source，否则不更新

                    propertySources.replace(name, new MapPropertySource(name, newValue));
                }
            }
        });

        // 刷新缓存
        BeanRefreshScope.clear();
    }

    /**
     * 描述：更新属性源中的指定 key 的属性
     *
     * @param key      关键
     * @param newValue 新值
     */
    public static void updateValue(String key, Object newValue) {
        MutablePropertySources propertySources = environment.getPropertySources();

        propertySources.stream().forEach(propertySource -> {
            if (propertySource instanceof MapPropertySource) {
                MapPropertySource mapPropertySource = (MapPropertySource) propertySource;
                if (mapPropertySource.containsProperty(key)) {
                    String name = mapPropertySource.getName();
                    Map<String, Object> source = mapPropertySource.getSource();
                    Map<String, Object> newSource = new HashMap<>(source.size());
                    newSource.putAll(source);
                    newSource.put(key, newValue);
                    propertySources.replace(name, new MapPropertySource(name, newSource));
                }
            }
        });

        // 刷新缓存
        BeanRefreshScope.clear();
    }
}
