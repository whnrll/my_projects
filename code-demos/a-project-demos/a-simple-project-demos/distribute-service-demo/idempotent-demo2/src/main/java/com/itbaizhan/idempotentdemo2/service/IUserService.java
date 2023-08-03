package com.itbaizhan.idempotentdemo2.service;

import com.itbaizhan.idempotentdemo2.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author itbaizhan
 * @since 06-07
 */
public interface IUserService extends IService<User> {


    /**
     *
     * @return
     */
    List<User> findAll();


    /**
     * 根据用户id查询用户
     * @param id 用户id
     * @return
     */
    User findById(Long id);


    /**
     * 创建用户
     * @param name
     * @param age
     * @return
     */
    Integer create(String name,Integer age);


    /**
     * 更新操作
     * @param user
     * @return
     */
    Integer update(User user);


    /**
     * 更新操作年纪
     * @param user
     * @return
     */
    Integer updateAge(User user);
}
