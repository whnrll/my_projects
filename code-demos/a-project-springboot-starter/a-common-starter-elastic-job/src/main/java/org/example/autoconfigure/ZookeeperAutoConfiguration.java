package org.example.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

/**
 * 描述：Zookeeper 自动配置类
 *
 * @author xutao
 * @Date 2023-03-11 23:35:20
 */
@Configuration
// 只有当 elastic.zookeeper.enable=true时，或者 elastic.zookeeper.enable 属性不存在时，配置类生效
@ConditionalOnProperty(prefix = "elastic.zookeeper", name = "enable", havingValue = "true", matchIfMissing = true)
@Import(ZookeeperProperties.class)
public class ZookeeperAutoConfiguration {

    @Bean(initMethod = "init")
    public CoordinatorRegistryCenter registryCenter(ZookeeperProperties zookeeperProperties) {
        ZookeeperConfiguration configuration =
            new ZookeeperConfiguration(zookeeperProperties.getServerLists(), zookeeperProperties.getNamespace());
        return new ZookeeperRegistryCenter(configuration);
    }
}
