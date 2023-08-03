package org.example.micro.driver.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.example.micro.driver.model.Driver;
import org.example.micro.driver.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/driver")
@Slf4j
public class DriverController {


    @Autowired
    private DriverService driverService;


    /****
     * 司机信息
     */
    //@GetMapping(value = "/info/{id}")
    @RequestMapping(value = "/info/{id}")
    public Driver info(@PathVariable(value = "id") String id, HttpServletRequest request) {
        int i = 1/ 0;
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ":" + value);
            System.out.println("--------------------------");
        }
        return driverService.findById(id);
    }


    @Value("${server.port}")
    private int port;

    /****
     * 更新司机信息
     */
    @PutMapping(value = "/status/{id}/{status}")
    public Driver status(@PathVariable(value = "id") String id, @PathVariable(value = "status") Integer status) throws InterruptedException {
        log.info("当前服务占用的端口为:{}", port);
        // 超时设置
//        Thread.sleep(3000);
        //修改状态
        driverService.update(id, status);
        log.info("update:id={}, status={}", id, status);
        //修改状态后的司机信息
        return driverService.findById(id);
    }
}
