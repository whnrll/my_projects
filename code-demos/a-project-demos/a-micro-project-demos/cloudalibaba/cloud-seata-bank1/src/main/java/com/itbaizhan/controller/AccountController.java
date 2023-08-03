package org.example.payment.zhifubao.controller;

import org.example.payment.zhifubao.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bank1")
public class AccountController {

    @Autowired
    private IAccountService iAccountService;

    /**
     * 转账
     * @param accountNo 银行卡号
     * @param amount 转账金额
     * @return
     */
    @GetMapping("transfer")
    public String transfer(String accountNo,Double amount){
        iAccountService.updateAccountBalance(accountNo,amount);
        return "转账成功";
    }
}
