package com.itbaizhan.payment.controller;

import com.itbaizhan.payment.entity.PayInfo;
import com.itbaizhan.payment.service.IPayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
 * @since 05-23
 */
@RestController
@RequestMapping("/payInfo")
public class PayInfoController {

    @Autowired
    private IPayInfoService iPayInfoService;


    /**
     * 充值
     * @param accountNo 账户编号
     * @param payAmount 金额
     * @return
     */
    @GetMapping("/save")
    public ResponseEntity savePayment(String accountNo, BigDecimal payAmount){
        return ResponseEntity.ok(iPayInfoService.savePayment(accountNo,payAmount));
    }


    /**
     * 查询交易流水记录
     * @param txNo 交易流水编号
     * @return
     */
    @GetMapping("/query/{txNo}")
    public PayInfo queryPayment(@PathVariable("txNo") String txNo){
        return iPayInfoService.getById(txNo);
    }

}
