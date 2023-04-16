package com.example.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/manager")
@RestController
public class OrderManagerController {

    @GetMapping("/ok")
    public String ok() {
        return "ok";
    }

}
