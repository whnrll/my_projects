package com.hmdp.interceptor;

import static com.hmdp.constant.UserConstants.SESSION_LOGIN_USER;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.hmdp.dto.UserDTO;
import com.hmdp.utils.UserHolder;

public class LoginUserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 1. 从 session 获取用户
        HttpSession session = request.getSession();
        UserDTO loginUser = (UserDTO)session.getAttribute(SESSION_LOGIN_USER);

        // 2. 校验用户是否存在
        if (loginUser == null) {
            // 3. 不存在，拦截
            response.setStatus(401);
            return false;
        }

        // 4. 存在，保存用户到 ThreadLocal
        UserHolder.saveUser(loginUser);

        // 5. 放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
        Exception ex) {
        UserHolder.removeUser();
    }
}
