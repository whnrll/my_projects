package org.example.payment.controller;

import com.alibaba.fastjson.JSON;
import org.example.payment.config.WxPayConfig;
import org.example.payment.utils.HttpUtils;
import org.example.payment.utils.WxVerifierUtils;
import org.example.payment.vo.BaseResult;
import org.example.payment.service.IWxPaymentService;
import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.HashMap;

/**
 * 微信支付接口
 */

@Slf4j
@RestController
@RequestMapping("/api/wx-pay")
public class WxPayController {


    @Autowired
    private IWxPaymentService iWxPaymentService;

    @Autowired
    private WxPayConfig wxPayConfig;

    @Autowired
    private Verifier verifier;


    /**
     * Native下单
     * weixin://wxpay/bizpayurl?pr=e5ta1spzz
     *
     * @param orderNo
     * @return
     */
    @PostMapping("/native/{orderNo}")
    public BaseResult nativePay(@PathVariable String orderNo) throws Exception {
        BaseResult baseResult = iWxPaymentService.nativePay(orderNo);
        return baseResult;
    }

    //api/wx-pay/native/notify

    /**
     * 微信支付通知
     *
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/native/notify")
    public String notify(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, GeneralSecurityException {

        HashMap<String, String> responseMap = new HashMap<>();

        // 1. 获取报文body
        String body = HttpUtils.readData(request);
        // 2. 把json转换为map
        HashMap<String, Object> bodyMap = JSON.parseObject(body, HashMap.class);
        log.info("支付通知id======> {}", bodyMap.get("id"));
        log.info("支付通知完整数据=======> {}", body);


        //TODO： 签名验证 确保是微信给我们发的消息
        boolean verifier = WxVerifierUtils.verifier(request, this.verifier, body);
        if (!verifier) {
            response.setStatus(200);
            responseMap.put("code", "FAIL");
            responseMap.put("message", "失败");
            return JSON.toJSONString(responseMap);
        }

        //TODO： 处理订单  未支付  已支付
        iWxPaymentService.updateOrderStatus(bodyMap);
        /**
         *
         {
         "code": "FAIL",
         "message": "失败"
         }
         */
        // 成功应答： 成功就是200 否则就是失败的应答
        response.setStatus(200);
        responseMap.put("code", "SUCCESS");
        responseMap.put("message", "成功");
        return JSON.toJSONString(responseMap);
    }


    // /api/wx-pay/queryOrderStatus/{orderNo}

    /**
     * 查询订单
     *
     * @param orderNo
     * @return
     */
    @GetMapping("/queryOrder/{orderNo}")
    public BaseResult queryOrder(@PathVariable String orderNo) throws IOException {
        return BaseResult.ok(iWxPaymentService.queryOrder(orderNo));
    }



    /**
     * 根据订单编号取消订单
     * @param orderNo
     * @return
     */
    @PostMapping("/cancel/{orderNo}")
    public BaseResult cancel(@PathVariable String orderNo) throws IOException {
        iWxPaymentService.cancelOrder(orderNo);
        return BaseResult.ok("订单已取消");
    }


    /**
     * 申请退款
     *
     * @param orderNo 订单编号
     * @param reason  退款理由
     * @return
     */
    @PostMapping("/refund/{orderNo}/{reason}")
    public BaseResult refund(@PathVariable String orderNo, @PathVariable String reason) throws IOException {
        iWxPaymentService.refund(orderNo, reason);
        return BaseResult.ok();
    }


    /**
     * 退款通知
     *
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     * @throws GeneralSecurityException
     */
    @PostMapping("/refunds/notify")
    public String refunds(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, GeneralSecurityException {
        HashMap<String, String> responseMap = new HashMap<>();

        // 1. 获取报文body
        String body = HttpUtils.readData(request);
        // 2. 把json转换为map
        HashMap<String, Object> bodyMap = JSON.parseObject(body, HashMap.class);
        log.info("支付通知id======> {}", bodyMap.get("id"));
        log.info("支付通知完整数据=======> {}", body);


        //TODO： 签名验证 确保是微信给我们发的消息
        boolean verifier = WxVerifierUtils.verifier(request, this.verifier, body);
        if (!verifier) {
            response.setStatus(200);
            responseMap.put("code", "FAIL");
            responseMap.put("message", "失败");
            return JSON.toJSONString(responseMap);
        }
        // 处理退款单
        iWxPaymentService.processRefunds(bodyMap);

        // 成功应答： 成功就是200 否则就是失败的应答
        response.setStatus(200);
        responseMap.put("code", "SUCCESS");
        responseMap.put("message", "成功");
        return JSON.toJSONString(responseMap);
    }

}