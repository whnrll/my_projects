package org.example.common.starter.jwt.client.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 描述：jwt客户端属性
 *
 * @author xutao
 * @Date 2023-02-28 21:16:23
 */
@Data
@ConfigurationProperties(prefix = "jwt.authentication.client")
public class JwtClientProperties {
    /**
     * 签发人
     */
    private String issuer;

    /**
     * 密钥
     */
    private String cipher;

    /**
     * 过期时间（单位：分）
     */
    private Integer expireMinute = 10;

    /**
     * 刷新间隔时间（单位：分）
     */
    private Integer refreshIntervalMinute = 10;
}
