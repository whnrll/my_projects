# 基于 SpringBoot Actuator 的监控服务器
## 搭建监控服务器的步骤
```text
1. 添加SpringBoot Admin Starter 自动配置依赖
2. 在启动类上添加注解 @EnableAdminServer
3. Admin Server Web UI 访问地址：http://localhost:8001/admin-server
4. 添加 Admin Server 的登录认证功能
（1）添加 SpringSecurity 依赖
（2）修改配置文件，增加 SpringSecurity 配置
    spring:
      cloud:
        nacos:
          discovery:
            metadata:
              user.name: admin
              user.password: admin   
（3）实现 WebSecurityConfigurerAdapter 的 configure(HttpSecurity http) 方法，进行 http 相关配置              
5. 将其他服务接入 Admin Server 监控管理服务
（1）修改要接入的服务配置
    spring:
      cloud:
        nacos:
          discovery:
            metadata:
              management:
                context-path: ${server.servlet.context-path}/actuator
```