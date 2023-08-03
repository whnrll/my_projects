package com.itbaizhan.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 远程调用的接口
 */
@Service
@FeignClient("bank2-provider")
public interface Bank2ServiceFeign {


    @GetMapping("/bank2/transfer")
    String transfer(@RequestParam("accountNo") String accountNo,@RequestParam("amount") Double amount);


}
