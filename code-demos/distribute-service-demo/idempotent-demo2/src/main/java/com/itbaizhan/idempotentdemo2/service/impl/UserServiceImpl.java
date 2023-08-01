package com.itbaizhan.idempotentdemo2.service.impl;

import com.itbaizhan.idempotentdemo2.entity.User;
import com.itbaizhan.idempotentdemo2.mapper.UserMapper;
import com.itbaizhan.idempotentdemo2.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author itbaizhan
 * @since 06-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    /**
     * 查询用户
     * @return
     */
    @Override
    public List<User> findAll() {
        return baseMapper.selectList(null);
    }

    /**
     * 根据用户id查询用户信息
     * @param id 用户id
     * @return
     */
    @Override
    public User findById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 创建用户
     * @param name
     * @param age
     * @return
     */
    @Override
    public Integer create(String name, Integer age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return baseMapper.insert(user);
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    @Override
    public Integer update(User user) {
        return baseMapper.updateById(user);
    }


    /**
     * 更新年纪
     * @param user
     * @return
     */
    @Override
    public Integer updateAge(User user) {
        return baseMapper.updateAge(user.getId(),user.getVersion());
    }
}
