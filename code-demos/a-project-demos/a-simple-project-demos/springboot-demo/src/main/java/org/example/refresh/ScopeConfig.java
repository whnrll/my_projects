package org.example.refresh;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScopeConfig {


    /**
     * 描述：自定义 Scope 配置
     *
     * @return {@link CustomScopeConfigurer }
     */
    @Bean
    public CustomScopeConfigurer customScopeConfigurer() {
        CustomScopeConfigurer customScopeConfigurer = new CustomScopeConfigurer();
        Map<String, Object> map = new HashMap<>();
        map.put(BeanRefreshScope.SCOPE_REFRESH, BeanRefreshScope.getInstance());
        customScopeConfigurer.setScopes(map);
        return customScopeConfigurer;
    }
}
