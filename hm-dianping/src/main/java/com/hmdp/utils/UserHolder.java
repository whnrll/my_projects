package com.hmdp.utils;

import com.hmdp.dto.UserDTO;
import com.hmdp.entity.User;

/**
 * 描述：
 *
 * @author xutao
 * @date 2023-04-08 21:35:49
 * @since 1.0.0
 */
public class UserHolder {
    private static final ThreadLocal<UserDTO> USER_THREAD_LOCAL = new ThreadLocal<>();

    public static void saveUser(UserDTO userDTO){
        USER_THREAD_LOCAL.set(userDTO);
    }

    public static UserDTO getUser(){
        return USER_THREAD_LOCAL.get();
    }

    public static void removeUser(){
        USER_THREAD_LOCAL.remove();
    }
}
