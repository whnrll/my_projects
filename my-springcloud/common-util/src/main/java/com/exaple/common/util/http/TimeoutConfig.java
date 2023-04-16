package com.exaple.common.util.http;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TimeoutConfig {
    private long readTimeout;
    private long writeTimeout;
    /**
     * 连接超时时间
     */
    private long socketTimeout;
    /**
     * 连接超时时间
     */
    private long connectTimeout;
}
