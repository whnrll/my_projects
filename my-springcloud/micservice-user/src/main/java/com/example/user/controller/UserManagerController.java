package com.example.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/manager")
@RestController
public class UserManagerController {

    @GetMapping("/ok")
    public String ok() {
        return "ok";
    }

}
