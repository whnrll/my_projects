package com.itbaizhan.account.controller;

import com.itbaizhan.account.service.IAccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author itbaizhan
 * @since 05-23
 */
@Controller
@RequestMapping("/payInfo")
public class PayInfoController {

    @Autowired
    private IAccountInfoService iAccountInfoService;


    /**
     * 查询交易流水记录
     * @param txNo 交易流水编号
     * @return
     */
    @GetMapping("/query/{txNo}")
    public ResponseEntity queryPayment(@PathVariable("txNo") String txNo){
        return ResponseEntity.ok(iAccountInfoService.queryPayment(txNo));
    }



}
