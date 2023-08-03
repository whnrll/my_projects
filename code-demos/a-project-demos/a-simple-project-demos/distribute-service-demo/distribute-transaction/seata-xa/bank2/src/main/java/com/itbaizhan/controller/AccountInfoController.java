package org.example.payment.zhifubao.controller;

import org.example.payment.zhifubao.service.IAccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author itbaizhan
 * @since 05-12
 */
@RestController
@RequestMapping("/bank2")
public class AccountInfoController {


    @Autowired
    private IAccountInfoService iAccountInfoService;

    /**
     * 转账
     * @param accountNo
     * @param amount
     * @return
     */
    @GetMapping("/transfer")
    public String transfer(String accountNo, Double amount){
        iAccountInfoService.updateAccountBalance(accountNo,amount);
        return "转账成功";
    }



}
