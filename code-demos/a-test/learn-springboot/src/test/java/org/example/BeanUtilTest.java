package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.enums.Gender;
import org.example.vo.Student;
import org.example.vo.Student2;
import org.example.vo.Teacher;
import org.example.vo.Teacher2;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 描述：
 *
 * @author xutao
 * @date 2023-03-28 23:19:45
 * @since 1.0.0
 */
@Slf4j
public class BeanUtilTest {
    @Test
    public void copy() {
        Teacher teacher = new Teacher();
        teacher.setId(1);
        teacher.setName("老师1");

        Student student = new Student();
        student.setName("s1");
        student.setGender(Gender.MAN);
        teacher.setStudents(new ArrayList<>(Arrays.asList(student)));

        Teacher2 teacher2 = new Teacher2();

        BeanUtils.copyProperties(teacher, teacher2);
        System.out.println(teacher2);

        for (int i = 0; i < teacher2.getStudents().size(); i++) {
            System.out.println(teacher2.getStudents().get(i).getClass());
        }

    }
}
