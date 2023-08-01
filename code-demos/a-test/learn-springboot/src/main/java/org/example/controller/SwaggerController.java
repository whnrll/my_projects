package org.example.controller;

import io.swagger.annotations.ApiImplicitParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api("SwaggerController")
@RestController
@RequestMapping("/swagger")
public class SwaggerController {

    @ApiOperation("打印请求信息")
    @ApiImplicitParams(@ApiImplicitParam(dataType = "String", name = "message", value = "请求参数"))
    @PostMapping("/print")
    public String print(@RequestParam("message") String message) {
        return "Hello, " + message;
    }
}
