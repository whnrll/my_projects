package com.example.mybatis.mapper;

import com.example.mybatis.entity.TbHotel;
import com.example.mybatis.entity.TbHotelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbHotelMapper {
    long countByExample(TbHotelExample example);

    int deleteByExample(TbHotelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbHotel record);

    int insertSelective(TbHotel record);

    List<TbHotel> selectByExample(TbHotelExample example);

    TbHotel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbHotel record, @Param("example") TbHotelExample example);

    int updateByExample(@Param("record") TbHotel record, @Param("example") TbHotelExample example);

    int updateByPrimaryKeySelective(TbHotel record);

    int updateByPrimaryKey(TbHotel record);
}