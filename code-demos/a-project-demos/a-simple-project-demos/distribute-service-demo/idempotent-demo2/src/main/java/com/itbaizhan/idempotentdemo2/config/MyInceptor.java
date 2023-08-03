package com.itbaizhan.idempotentdemo2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@Component
public class MyInceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate redisTemplate;


    /**
     * 之前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // HandlerMethod 封装很多属性， 在访问请求方法的时候可以方便的访问到访问的参数 方法上的注解
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 获得方法
        Method method = handlerMethod.getMethod();
        // 判断这个方法有没有添加幂等的注解
        boolean methodAnnotationPresent = method.isAnnotationPresent(ApiIdempotentAnn.class);
        // 判断是否开启幂等性处理。
        if(methodAnnotationPresent &&  method.getAnnotation(ApiIdempotentAnn.class).value()){
            // 验证接口幂等性
            boolean result = this.checkToken(request);
            if (result){
                // 放行
                return true;
            }else {
                response.setContentType("application/json;charset=utf-8");
                PrintWriter writer = response.getWriter();
                writer.print("重复调用");
                writer.close();
                response.flushBuffer();
                return false;
            }
        }
        return false;
    }


    /**
     * 验证token有效性
     * @param request
     * @return
     */
    public boolean checkToken(HttpServletRequest request){
        // 获取token
        String token = request.getParameter("token");
        // 判断
        if (null == token || "".equals(token)){
            return false;
        }
        return redisTemplate.delete(token);

    }
}
