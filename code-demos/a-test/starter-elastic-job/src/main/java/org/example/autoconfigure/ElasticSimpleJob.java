package org.example.autoconfigure;

import java.lang.annotation.*;

/**
 * 描述：Elastic SimpleJob
 *
 * @author xutao
 * @Date 2023-03-12 00:19:37
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ElasticSimpleJob {
    String jobName() default "";

    String cron() default "";

    int shardingTotalCount() default 1;

    boolean overwrite() default false;
}
