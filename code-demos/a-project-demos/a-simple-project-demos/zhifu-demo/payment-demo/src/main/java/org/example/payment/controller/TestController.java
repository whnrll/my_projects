package org.example.payment.controller;

import org.example.payment.config.WxPayConfig;
import org.example.payment.config.ZfbPayConfig;
import org.example.payment.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 */
@RestController
public class TestController {

    @Autowired
    private WxPayConfig wxPayConfig;

    @Autowired
    private ZfbPayConfig zfbPayConfig;


    @GetMapping("/test")
    public BaseResult test(){
        return BaseResult.ok("hello  payment");
    }

    /**
     * 测试微信配置文件
     * @return
     */
    @GetMapping("/getWxConfig")
    public BaseResult getWxConfig(){
        // 获取商户ID
        return BaseResult.ok(wxPayConfig.getMchId());
    }
    /**
     * 测试支付宝配置文件
     * @return
     */
    @GetMapping("/getZfbConfig")
    public BaseResult getZfbConfig(){
        // 获取商户ID
        return BaseResult.ok(zfbPayConfig.getAppId());
    }


}
