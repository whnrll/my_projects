package com.hmdp.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.entity.UserInfo;
import com.hmdp.mapper.UserInfoMapper;
import com.hmdp.service.IUserInfoService;

import lombok.extern.slf4j.Slf4j;

/**
 * 描述：用户服务
 *
 * @author xutao
 * @date 2023-04-09 15:37:26
 * @since 1.0.0
 */
@Slf4j
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
