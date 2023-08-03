package org.example.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
public class JwtUtil {
    private static final int MILLISECOND_PER_MINUTE = 1000;

    /**
     * 描述：生成 jwt
     *
     * @param issuer
     * @param expireMinute
     * @param claims
     * @return {@link String }
     */
    public static String generateJwt(String issuer, int expireMinute, Object claims) {
        Calendar calendar = Calendar.getInstance();
        Date nowTime = calendar.getTime();

        calendar.add(Calendar.MILLISECOND, MILLISECOND_PER_MINUTE * expireMinute);
        Date expireTime = calendar.getTime();

        return Jwts.builder()
                .setIssuer(issuer)
                .setIssuedAt(nowTime)
                .setExpiration(expireTime)
                .setClaims(null)
                .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256), SignatureAlgorithm.HS256).compact();
    }

    public static boolean isValid(String token, String signingKey) {
        if (Objects.isNull(token)) {
            throw new IllegalArgumentException("token is null");
        }

        String[] split = token.split("\\.");
        if (split.length != 3) {
            throw new IllegalArgumentException("token is illegal");
        }

        String payLoad = split[1];
        byte[] decode = Base64.getUrlDecoder().decode(payLoad.getBytes(StandardCharsets.UTF_8));
        String str = new String(decode, StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, Object> claims = mapper.readValue(str, Map.class);
        } catch (Exception e) {
            log.error("deserialize claims exception: [{}]", e.getMessage());
            throw new RuntimeException("deserialize claims exception");
        }



        Jwt parse = Jwts.parser().setSigningKey(signingKey).parse(token);
        Header header = parse.getHeader();
        Object body = parse.getBody();
        return false;
    }

    public static void main(String[] args) {
        String jwt = generateJwt("xt", 2, null);
        boolean valid = isValid(jwt, "");
        System.out.println(valid);
    }
}
