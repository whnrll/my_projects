package com.hmdp.service.impl;

import static com.hmdp.constant.SystemConstants.USER_NICKNAME_PREFIX;
import static com.hmdp.constant.UserConstants.SESSION_LOGIN_CODE;
import static com.hmdp.constant.UserConstants.SESSION_LOGIN_USER;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.map.MapUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.dto.LoginFormDTO;
import com.hmdp.dto.Result;
import com.hmdp.dto.UserDTO;
import com.hmdp.entity.User;
import com.hmdp.mapper.UserMapper;
import com.hmdp.service.IUserService;
import com.hmdp.utils.RegexUtils;
import com.hmdp.utils.UserHolder;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result sendCode(String phone, HttpSession httpSession) {
        // 1. 校验手机号
        if (RegexUtils.isPhoneInvalid(phone)) {
            // 2. 不符合，返回错误信息
            return Result.fail("手机号无效");
        }

        // 3. 符合，生成验证码
        String code = RandomUtil.randomNumbers(6);

        // 4. 保存验证码到 session
        httpSession.setAttribute(SESSION_LOGIN_CODE, code);

        // 5. 发送验证码
        log.debug("发送验证码成功，验证码：{}", code);

        // 6. 返回成功
        return Result.ok();
    }

    @Override
    public Result login(LoginFormDTO loginForm, HttpSession httpSession) {
        // 1. 校验手机号和验证码
        String phone = loginForm.getPhone();
        if (RegexUtils.isPhoneInvalid(phone)) {
            // 2. 不符合，返回错误信息
            return Result.fail("手机号无效");
        }
        String code = loginForm.getCode();
        if (RegexUtils.isCodeInvalid(code)) {
            // 2. 不符合，返回错误信息
            return Result.fail("验证码无效");
        }
        Object cacheCode = httpSession.getAttribute(SESSION_LOGIN_CODE);
        if (cacheCode == null || !cacheCode.equals(code)) {
            return Result.fail("验证码无效");
        }

        // 3. 符合，校验用户是否存在
        User user = query().eq("phone", phone).one();
        if (user == null) {
            // 4. 不存在，创建新用户并存储到数据库
            user = createNewUser(phone);
            save(user);
        }

        // 5. 存在，保存用户到 session
        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        httpSession.setAttribute(SESSION_LOGIN_USER, userDTO);

        // 6. 返回用户信息
        return Result.ok(userDTO);
    }

    @Override
    public Result me() {
        return Result.ok(UserHolder.getUser());
    }

    private User createNewUser(String phone) {
        User user = new User();
        user.setPhone(phone);
        user.setNickName(USER_NICKNAME_PREFIX + RandomUtil.randomString(10));
        return user;
    }
}
