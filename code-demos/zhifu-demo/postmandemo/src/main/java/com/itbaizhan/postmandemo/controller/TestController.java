package com.itbaizhan.postmandemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 测试API
 */
@RestController
@RequestMapping("/user")
public class TestController {


    /**
     * 测试get请求
     * @param username
     * @param password
     * @return
     */
    @GetMapping("/loginGet")
    public ResponseEntity testGet(String username ,String password){
        return ResponseEntity.ok(username.concat("===>").concat(password));
    }
    /**
     * 测试Post请求
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/loginPost")
    public ResponseEntity testPost(@RequestBody String username , String password){
        return ResponseEntity.ok(username.concat("===>").concat(password));
    }

}
