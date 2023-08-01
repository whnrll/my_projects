package configuration;

import com.alibaba.nacos.common.http.handler.RequestHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import handler.FeignClientRequestHandler;
import util.JwtAuthTokenFactory;

/**
 * Jwt token 客户端认证自动配置类
 */
@Configuration
@ConditionalOnProperty(havingValue = "jwt.authentication.client.enable", matchIfMissing = true)
@EnableConfigurationProperties(JwtClientProperties.class)
@Import(JwtAuthClientConfiguration.class)
public class JwtAuthClientAutoConfiguration {

    @Bean
    @ConditionalOnClass(RequestHandler.class)
    public FeignClientRequestHandler feignClientRequestHandler(JwtAuthTokenFactory jwtAuthTokenFactory) {
        return new FeignClientRequestHandler(jwtAuthTokenFactory);
    }
}
