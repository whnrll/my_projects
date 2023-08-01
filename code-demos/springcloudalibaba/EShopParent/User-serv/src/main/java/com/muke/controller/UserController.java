package com.muke.com.muke.com.muke.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author ziya
 * @date 2021/2/22 12:41 上午
 */
@RestController
public class UserController {
    @GetMapping("/user/{userId}")
    public String getUserName(@PathVariable  Integer userId){
        return "子牙老师";
    }
}
