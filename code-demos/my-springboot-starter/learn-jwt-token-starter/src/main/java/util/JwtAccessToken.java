package util;

import java.util.Date;

/**
 * 描述：jwt访问令牌
 *
 * @author xutao
 * @Date 2023-02-28 21:12:44
 */
public class JwtAccessToken implements AccessToken {
    private final String token;

    private final String issuer;

    private final Date issuerAt;

    private final Date expire;

    public JwtAccessToken(String token, String issuer, Date issuerAt, Date expire) {
        this.token = token;
        this.issuer = issuer;
        this.issuerAt = (Date) issuerAt.clone();
        this.expire = (Date) expire.clone();
    }

    @Override
    public String getToken() {
        return this.token;
    }

    @Override
    public String getIssuer() {
        return this.issuer;
    }

    @Override
    public Date getIssuerAt() {
        return (Date) this.issuerAt.clone();
    }

    @Override
    public Date getExpire() {
        return (Date) this.expire.clone();
    }
}
