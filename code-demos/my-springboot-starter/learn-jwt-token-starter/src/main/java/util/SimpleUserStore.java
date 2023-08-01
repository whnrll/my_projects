package util;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 描述：存储用户签名
 *
 * @author xutao
 * @Date 2023-02-28 22:57:31
 */
public class SimpleUserStore implements UserStore {
    private final ConcurrentHashMap<String, String> users = new ConcurrentHashMap<>();

    @Override
    public void add(String user, String cipher) {
        users.put(user, cipher);
    }

    @Override
    public String getCipher(String user) {
        return users.get(user);
    }

    @Override
    public boolean exist(String user) {
        return users.contains(user);
    }
}
