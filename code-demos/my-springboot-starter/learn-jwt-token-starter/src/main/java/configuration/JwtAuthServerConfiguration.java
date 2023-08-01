package configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import lombok.extern.slf4j.Slf4j;
import util.SimpleUserStore;
import util.TokenChecker;
import util.UserStore;

@Slf4j
@Configuration
@ConditionalOnProperty(havingValue = "jwt.authentication.server.enable", matchIfMissing = true)
@Import(JwtServerConfiguration.class)
public class JwtAuthServerConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "jwt.authentication.server")
    public UserStore simpleUserStore() {
        return new SimpleUserStore();
    }

    @Bean
    public TokenChecker tokenChecker(UserStore userStore) {
        return new JwtTokenChecker(userStore);
    }


    @Bean
    @ConditionalOnMissingBean
    public UserStore userStore(@Autowired(required = false) JwtServerProperties jwtServerProperties) {
        if (jwtServerProperties.getUserStore() == null) {
            log.warn("UserStore is null");
            return new SimpleUserStore();
        }

        SimpleUserStore simpleUserStore = new SimpleUserStore();
        return simpleUserStore;
    }


    @Bean
    public JwtMvcConfigurer jwtMvcConfigurer(TokenChecker tokenChecker) {
        return new JwtMvcConfigurer(tokenChecker);
    }
}
