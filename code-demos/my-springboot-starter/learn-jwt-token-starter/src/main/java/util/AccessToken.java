package util;

import java.util.Date;

/**
 * 描述：访问令牌
 *
 * @author xutao
 * @Date 2023-02-28 21:09:18
 */
public interface AccessToken {
    /**
     * 描述：获得令牌
     *
     * @return {@link String }
     */
    String getToken();

    /**
     * 描述：获得令牌发行人
     *
     * @return {@link String }
     */
    String getIssuer();

    /**
     * 描述：获得令牌发行时间
     *
     * @return {@link Date }
     */
    Date getIssuerAt();

    /**
     * 描述：获得令牌发行过期时间
     *
     * @return {@link Date }
     */
    Date getExpire();
}
