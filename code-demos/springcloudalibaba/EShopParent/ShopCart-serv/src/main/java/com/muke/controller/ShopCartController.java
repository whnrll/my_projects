package com.muke.com.muke.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author ziya
 * @date 2021/2/22 12:54 上午
 */
@RestController
public class ShopCartController {

    @GetMapping("/shopcart/remove")
    public String remove(Integer productId,Integer userId){
        System.out.println("移出购物车成功");
        return "移出购物车成功";
    }
}
