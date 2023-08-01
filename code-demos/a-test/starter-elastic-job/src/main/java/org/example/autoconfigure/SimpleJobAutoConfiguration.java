package org.example.autoconfigure;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;

/**
 * 描述：简单工作汽车配置
 *
 * @author xutao
 * @Date 2023-03-12 00:26:04
 */
@Configuration
@ConditionalOnBean(CoordinatorRegistryCenter.class)
@AutoConfigureAfter(ZookeeperAutoConfiguration.class)
public class SimpleJobAutoConfiguration {
    private final ApplicationContext applicationContext;

    private final CoordinatorRegistryCenter registryCenter;

    public SimpleJobAutoConfiguration(ApplicationContext applicationContext, CoordinatorRegistryCenter registryCenter) {
        this.applicationContext = applicationContext;
        this.registryCenter = registryCenter;
    }

    @PostConstruct
    public void initSimpleJob() {
        // 获取所有被 @ElasticSimpleJob 注释的 bean 实列
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(ElasticSimpleJob.class);
        for (Map.Entry<String, Object> entry : beans.entrySet()) {
            Object instance = entry.getValue();

            // 获取 @ElasticSimpleJob 注释的 bean 的接口
            Class<?>[] interfaces = instance.getClass().getInterfaces();
            if (ArrayUtils.isEmpty(interfaces)) {
                // 如果未实现接口，则跳过
                continue;
            }

            for (Class<?> superInterface : interfaces) {
                if (superInterface == SimpleJob.class) {
                    // 获取注解 ElasticSimpleJob
                    ElasticSimpleJob annotation = instance.getClass().getAnnotation(ElasticSimpleJob.class);

                    // 作业核心配置.
                    JobCoreConfiguration coreConfiguration = JobCoreConfiguration
                        .newBuilder(annotation.jobName(), annotation.cron(), annotation.shardingTotalCount()).build();
                    // 简单作业配置
                    SimpleJobConfiguration simpleJobConfiguration =
                        new SimpleJobConfiguration(coreConfiguration, instance.getClass().getCanonicalName());

                    // Lite作业配置
                    LiteJobConfiguration liteJobConfiguration =
                        LiteJobConfiguration.newBuilder(simpleJobConfiguration).build();

                    // 基于Spring的作业启动器.
                    SpringJobScheduler springJobScheduler =
                        new SpringJobScheduler((SimpleJob)instance, registryCenter, liteJobConfiguration);
                    springJobScheduler.init();
                }
            }
        }
    }
}
