package util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述：原始访问令牌
 *
 * @author xutao
 * @Date 2023-02-28 22:28:13
 */
@Slf4j
public class RawAccessToken implements AccessToken {
    private Map<String, Object> claims;

    private String token;

    public RawAccessToken(String token) {
        this.token = token;
        if (Objects.isNull(token)) {
            throw new RuntimeException("token is null");
        }

        String[] split = token.split("\\.");
        if (split.length != 3) {
            throw new RuntimeException("token is illegal");
        }

        String payLoad = split[1];
        byte[] decode = Base64.getUrlDecoder().decode(payLoad.getBytes(StandardCharsets.UTF_8));
        String str = new String(decode, StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        try {
            claims = mapper.readValue(str, Map.class);
        } catch (Exception e) {
            log.error("deserialize claims exception: [{}]", e.getMessage());
            throw new RuntimeException("deserialize claims exception");
        }
    }

    public void verify(String token) throws Exception {
        Jwt parse = Jwts.parser().setSigningKey(token).parse(this.token);
    }

    @Override
    public String getToken() {
        return this.token;
    }

    @Override
    public String getIssuer() {
        return (String)this.claims.get("iss");
    }

    @Override
    public Date getIssuerAt() {
        return (Date)this.claims.get("iat");
    }

    @Override
    public Date getExpire() {
        return (Date)this.claims.get("exp");
    }
}
