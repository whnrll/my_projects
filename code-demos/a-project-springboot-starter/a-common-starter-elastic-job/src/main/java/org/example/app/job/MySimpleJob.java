package org.example.app.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.example.autoconfigure.ElasticSimpleJob;

/**
 * 描述：我简单工作
 *
 * @author xutao
 * @Date 2023-03-12 00:22:52
 */
@Slf4j
@ElasticSimpleJob(jobName = "my-simple-job", cron = "0/10 * * * * ?", shardingTotalCount = 2, overwrite = true)
public class MySimpleJob implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("shardingContext: {}", shardingContext);
    }
}
