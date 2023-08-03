package com.imooc.food.restaurantservicemanager;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(value = "com.imooc", annotationClass= Mapper.class)
@ComponentScan("com.imooc")
public class RestaurantServiceManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantServiceManagerApplication.class, args);
    }

}
