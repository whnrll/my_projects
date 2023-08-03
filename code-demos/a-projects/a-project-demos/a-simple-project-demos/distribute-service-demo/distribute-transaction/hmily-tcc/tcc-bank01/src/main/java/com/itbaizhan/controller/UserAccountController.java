package com.itbaizhan.controller;

import com.itbaizhan.dto.UserAccountDTO;
import com.itbaizhan.service.IUserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * <p>
 * 账户信息 前端控制器
 * </p>
 *
 * @author itbaizhan
 * @since 05-15
 */
@RestController
@RequestMapping("/userAccount")
public class UserAccountController {

    @Autowired
    private IUserAccountService iUserAccountService;


    /**
     * 转账
     * @return
     */
    @GetMapping("/transfer")
    public String transfer(UserAccountDTO userAccountDTO){
        userAccountDTO.setTxNo(UUID.randomUUID().toString().replace("-",""));
        iUserAccountService.transferAmountToBank02(userAccountDTO);
        return "转账成功";
    }



}
