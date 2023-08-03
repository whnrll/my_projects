package org.example.common.starter.jwt.server.properties;

import lombok.Data;
import org.example.common.starter.jwt.service.UserStore;

/**
 * 描述：jwt server 用户配置
 *
 * @author xutao
 * @date 2023-08-04 02:51:41
 * @since 1.0.0
 */
@Data
public class JwtServerProperties {
    private UserStore userStore;
}
