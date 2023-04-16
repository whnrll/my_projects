package com.example.common;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.exaple.common.entity.Student;
import com.exaple.common.entity.Teacher;
import com.exaple.common.enums.Gender;
import com.exaple.common.util.JacksonUtil;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 描述：JacksonUtil Test
 *
 * @author xutao
 * @Date 2023-02-26 23:11:41
 */
@Slf4j
public class JacksonUtilTest {
    @Test
    public void t1() {
        Student student1 = new Student("0001", "梦云", new Date(), Gender.GIRL);
        String toJson = JacksonUtil.formatSerialize(student1);
        System.out.println(toJson);
    }

    @Test
    public void t2() {
        Student student1 = new Student("0001", "梦云", new Date(), Gender.GIRL);
        Teacher teacher1 = new Teacher(1, "许涛", Collections.singletonList(student1));
        String toJson = JacksonUtil.toJson(teacher1);
        System.out.println(toJson);
    }

    @Test
    public void t3() throws Exception{
        String json = "{\n" +
                "  \"id\" : 1,\n" +
                "  \"name\" : \"许涛\",\n" +
                "  \"students\" : [ {\n" +
                "    \"no\" : \"0001\",\n" +
                "    \"name\" : \"梦云\",\n" +
                "    \"birth\" : \"2023-02-26 23:24:23\",\n" +
                "    \"gender\" : \"GIRL\"\n" +
                "  } ]\n" +
                "}";

        ObjectMapper mapper = JacksonUtil.getMapper();
        JsonNode jsonNode = mapper.readTree(json);
        JsonNode students = jsonNode.get("students");
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i).getNodeType());
            System.out.println(students.get(i).get("name"));
            System.out.println(students.get(i).get("birth"));
        }

        TypeReference<Teacher> typeReference = new TypeReference<Teacher>() {};
        Teacher teacher = mapper.readValue(json, typeReference);
        System.out.println(teacher);
    }

    @Test
    public void t4() {
        Student student1 = new Student("0001", "梦云", new Date(), Gender.GIRL);
        Student student2 = new Student("0002", "梦云2", new Date(), Gender.GIRL);
        List<Student> students = Arrays.asList(student1, student2);
        String toJson = JacksonUtil.toJson(students);
        System.out.println(toJson);
    }

    @Test
    public void t5() {
        String json = "[{\"no\":\"0001\",\"name\":\"梦云\",\"birth\":\"2023-02-26 23:59:50\",\"gender\":\"GIRL\"},{\"no\":\"0002\",\"name\":\"梦云2\",\"birth\":\"2023-02-26 23:59:50\",\"gender\":\"GIRL\"}]";
        TypeReference<List<Student>> listTypeReference = new TypeReference<List<Student>>() {};
        List<Student> students = JacksonUtil.toObject(json, listTypeReference);
        System.out.println(students);
    }

    @Test
    public void deserializeArray() {
        String[] obj = {"{\"name\": \"xt1\"}", "{\"name\": \"xt2\"}"};
        List<Student> students = JacksonUtil.fromJSON(obj, Student.class);
        System.out.println(students);
    }
}
