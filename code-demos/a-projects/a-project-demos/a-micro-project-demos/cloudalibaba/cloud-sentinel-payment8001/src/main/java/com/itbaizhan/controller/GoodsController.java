package com.itbaizhan.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 热点参数限流
 */
@RestController
@RequestMapping("goods")
public class GoodsController {


    /**
     * 根据商品id查询商品信息
     * 注意事项：
     *   默认SprinfMVC资源无效，需要利用SentinelResource注解标记资源
     * @param id
     * @return
     */
    @SentinelResource(value = "goods")
    @GetMapping("findById")
    public String findById(String id){
        return "商品ID;" + id;
    }
}
