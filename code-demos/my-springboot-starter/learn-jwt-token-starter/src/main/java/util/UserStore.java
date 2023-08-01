package util;

/**
 * 描述：用户存储
 *
 * @author xutao
 * @Date 2023-02-28 22:52:50
 */
public interface UserStore {

    /**
     * 描述：添加访问令牌
     *
     * @param user   用户
     * @param cipher 签名密钥
     */
    void add(String user, String cipher);

    /**
     * 描述：获取签名密钥
     *
     * @param user 用户
     * @return {@link String }
     */
    String getCipher(String user);

    /**
     * 描述：检查用户是否存在
     *
     * @param user user
     * @return boolean
     */
    boolean exist(String user);
}
