package org.example.demo.controller;

import org.example.demo.aop.PrintLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AopController {

    @PrintLog
    @GetMapping("/test1")
    public String test1() {
        return "test1";
    }
}
