package com.bjsxt.classmanager.mapper;

import com.bjsxt.pojo.Classes;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

// 班级数据访问接口
@Mapper
public interface ClassMapper {
    // 新增数据
    @Insert("insert into lcn_class(id, class_name, create_time) values(#{id}, #{className}, #{createDate})")
    // 主键自增处理注解
    // @SelectKey(statement = "select @@identity as id", keyColumn = "id", keyProperty = "id", before = false, resultType = Long.class)
    int insertClass(Classes classes);

    // 主键查询
    @Select("select id, class_name as className, create_time as createDate from lcn_class where id = #{id}")
    Classes selectById(Long id);
}
