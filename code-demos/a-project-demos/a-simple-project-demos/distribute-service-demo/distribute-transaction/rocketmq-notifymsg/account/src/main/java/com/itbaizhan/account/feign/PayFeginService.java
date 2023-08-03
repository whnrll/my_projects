package com.itbaizhan.account.feign;

import com.itbaizhan.account.entity.PayInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("payment")
public interface PayFeginService {

    @GetMapping("/payInfo/query/{txNo}")
    PayInfo queryPayment(@PathVariable("txNo") String txNo);
}
