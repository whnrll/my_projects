package com.hmdp.utils;

import com.hmdp.entity.User;

/**
 * 描述：
 *
 * @author xutao
 * @date 2023-04-08 21:35:49
 * @since 1.0.0
 */
public class UserHolder {
    private static final ThreadLocal<User> USER_THREAD_LOCAL = new ThreadLocal<>();

    public static void saveUser(User userId){
        USER_THREAD_LOCAL.set(userId);
    }

    public static User getUser(){
        return USER_THREAD_LOCAL.get();
    }

    public static void removeUser(){
        USER_THREAD_LOCAL.remove();
    }
}
