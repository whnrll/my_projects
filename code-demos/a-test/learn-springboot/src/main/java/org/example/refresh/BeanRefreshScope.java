package org.example.refresh;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 描述：动态刷新 Bean
 *
 * @author xutao
 * @Date 2023-02-27 23:28:23
 */
@Slf4j
public class BeanRefreshScope implements Scope {
    public static final String SCOPE_REFRESH = "refresh";

    private static final BeanRefreshScope INSTANCE = new BeanRefreshScope();

    private final ConcurrentHashMap<String, Object> beanMap = new ConcurrentHashMap<>();

    private BeanRefreshScope() {}

    /**
     * 描述：获得实例
     *
     * @return {@link BeanRefreshScope }
     */
    public static BeanRefreshScope getInstance() {
        return INSTANCE;
    }

    /**
     * 描述：清理 bean 缓存
     */
    public static void clear() {
        INSTANCE.beanMap.clear();
    }

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        log.info("BeanRefreshScope, get bean name: [{}]", name);
        return beanMap.computeIfAbsent(name, bean -> objectFactory.getObject());
    }

    @Override
    public Object remove(String name) {
        return beanMap.remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
