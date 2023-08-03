package org.example.micro.driver.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.micro.driver.model.Driver;


@Mapper
public interface DriverMapper {

    @Select("select * from driver where id=#{id}")
    Driver findById(String id);

    @Update("update driver set status=#{status} where id=#{id}")
    void update(@Param("id") String id,@Param("status") Integer status);
}
