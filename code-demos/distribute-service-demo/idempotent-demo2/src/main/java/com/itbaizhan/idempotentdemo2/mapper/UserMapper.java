package com.itbaizhan.idempotentdemo2.mapper;

import com.itbaizhan.idempotentdemo2.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author itbaizhan
 * @since 06-07
 */
public interface UserMapper extends BaseMapper<User> {

    Integer updateAge(@Param("id") Long id,@Param("version") Integer version);

}
