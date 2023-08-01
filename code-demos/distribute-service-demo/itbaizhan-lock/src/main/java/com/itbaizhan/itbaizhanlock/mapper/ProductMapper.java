package com.itbaizhan.itbaizhanlock.mapper;

import com.itbaizhan.itbaizhanlock.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author itbaizhan
 * @since 05-26
 */
public interface ProductMapper extends BaseMapper<Product> {


    /**
     * 根据ID 查新商品信息
     * @param id
     * @return
     */
    Product findById(@Param("id") Integer id);


    int updateProduct(@Param("id") Integer id,@Param("count") Integer count,@Param("version") Integer version);


}
