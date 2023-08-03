package org.example.common.starter.jwt.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import org.example.common.starter.jwt.client.properties.JwtClientProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.example.common.starter.jwt.service.AccessToken;
import org.example.common.starter.jwt.service.impl.JwtAccessToken;

/**
 * 描述：jwt 访问令牌工厂
 *
 * @author xutao
 * @Date 2023-02-28 21:34:32
 */
@Slf4j
public class JwtAuthTokenFactory {
    private static final int MILLISECOND_PER_MINUTE = 60 * 1000;

    private JwtClientProperties jwtClientProperties;

    private AccessToken current;

    private static final JwtAuthTokenFactory INSTANCE = new JwtAuthTokenFactory();

    private JwtAuthTokenFactory() {}

    public static JwtAuthTokenFactory getInstance() {
        return INSTANCE;
    }

    /**
     * 描述：生成或者刷新 jwt token
     *
     * @return {@link AccessToken }
     */
    public AccessToken createToken() {
        // token 不存在，则先创建一个
        if (Objects.isNull(current)) {
            log.info("Prepare to create token");
            current = token();
            return current;
        }

        // token 存在，则刷过期时间
        long now = System.currentTimeMillis();
        if (now > current.getIssuerAt().getTime() + jwtClientProperties.getRefreshIntervalMinute() * MILLISECOND_PER_MINUTE) {
            log.info("Time to refresh token");
            current = token();
        }

        return current;
    }

    public AccessToken token() {
        Calendar calendar = Calendar.getInstance();
        Date nowTime = calendar.getTime();
        calendar.add(Calendar.MILLISECOND, MILLISECOND_PER_MINUTE * jwtClientProperties.getExpireMinute());
        Date expireTime = calendar.getTime();
        String token = Jwts.builder().setIssuer(jwtClientProperties.getIssuer()).setIssuedAt(nowTime)
            .setExpiration(expireTime).setClaims(null)
            .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256), SignatureAlgorithm.HS256).compact();
        return new JwtAccessToken(token, jwtClientProperties.getIssuer(), nowTime, expireTime);
    }

    public void setJwtClientProperties(JwtClientProperties jwtClientProperties) {
        this.jwtClientProperties = jwtClientProperties;
    }
}
