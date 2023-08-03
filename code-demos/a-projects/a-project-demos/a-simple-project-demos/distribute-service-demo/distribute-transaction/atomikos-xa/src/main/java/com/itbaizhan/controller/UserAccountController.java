package com.itbaizhan.controller;

import com.itbaizhan.service.IUserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author itbaizhan
 * @since 05-13
 */
@RestController
@RequestMapping("/userAccount")
public class UserAccountController {


    @Autowired
    private IUserAccountService iUserAccountService;


    /**
     * 跨库转账
     * @param souceAccountNo 转出账户
     * @param targetAccountNo 转入账户
     * @param amount
     * @return
     */
    @GetMapping("/transfer")
    public String transfer(String souceAccountNo,String targetAccountNo, BigDecimal amount){
        iUserAccountService.transofer(souceAccountNo,targetAccountNo,amount);
        return "转账成功";
    }


}
