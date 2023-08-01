package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import util.JwtAuthTokenFactory;

/**
 * 描述：jwt身份验证配置
 *
 * @author xutao
 * @Date 2023-02-25 23:15:48
 */
@Configuration
public class JwtAuthClientConfiguration {
    @Bean
    public JwtClientProperties jwtClientProperties() {
        return new JwtClientProperties();
    }

    @Bean
    public JwtAuthTokenFactory jwtTokenFactory(JwtClientProperties jwtClientProperties) {
        JwtAuthTokenFactory instance = JwtAuthTokenFactory.getInstance();
        instance.setJwtClientProperties(jwtClientProperties);
        return instance;
    }
}
