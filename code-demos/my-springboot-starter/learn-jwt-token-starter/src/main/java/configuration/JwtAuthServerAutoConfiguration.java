package configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties(JwtServerProperties.class)
@Import(JwtAuthServerConfiguration.class)
@ConditionalOnProperty(havingValue = "jwt.authentication.server.enable", matchIfMissing = true)
public class JwtAuthServerAutoConfiguration {

}
