package com.bjsxt.studentmanager.feignclient;

import com.bjsxt.pojo.Classes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("lcn-class-manager")
public interface ClassManagerFeignClient {
    @RequestMapping("/createClass")
    public Classes createClass(@RequestParam("className") String className,
                               @RequestParam("id") Long id);
}
