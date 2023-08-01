package com.bjsxt.studentmanager.mapper;

import com.bjsxt.pojo.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentMapper {
    @Insert("insert into lcn_student(id, name, age, c_id) values(#{id}, #{name}, #{age}, #{cid})")
    int insertStudent(Student student);
    @Select("select id, name, age, c_id as cid from lcn_student where id = #{id}")
    Student selectById(Long id);
}
