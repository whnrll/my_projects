package com.itbaizhan.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("seata-bank2")
public interface Bank2ServiceFeign {


    /**
     * 远程调用bank2 增加金额
     * @param accountNo
     * @param amount
     * @return
     */
    @GetMapping("/bank2/transfer")
    String transfer(@RequestParam("accountNo") String accountNo,@RequestParam("amount") Double amount);


}
