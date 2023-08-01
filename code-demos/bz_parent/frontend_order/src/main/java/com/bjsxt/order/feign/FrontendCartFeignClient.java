package com.bjsxt.order.feign;

import com.bjsxt.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "frontend-cart")
public interface FrontendCartFeignClient {

    @PostMapping("/cart/deleteItemFromCart")
    Result deleteItemFromCart(@RequestParam("itemId") Long itemId, @RequestParam("userId")  String userId);
}
