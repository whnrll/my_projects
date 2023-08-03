package org.example.payment.controller;

import org.example.payment.config.ZfbPayConfig;
import org.example.payment.utils.ZfbVerifierUtils;
import org.example.payment.vo.BaseResult;
import org.example.payment.service.IZfbPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 支付宝接口
 */

@Slf4j
@RestController
@RequestMapping("/api/zfb-pay")
public class ZfbPayController {

    @Autowired
    private IZfbPayService iZfbPayService;

    @Autowired
    private ZfbPayConfig zfbPayConfig;


    /**
     * 支付宝支付
     * @param orderNo
     * @return
     * @throws Exception
     */
    @PostMapping("/pcPay/{orderNo}")
    public BaseResult nativePay(@PathVariable String orderNo) throws Exception {
        BaseResult baseResult = iZfbPayService.pcPay(orderNo);
        return baseResult;
    }


    /**
     * 支付成功后回调
     * @return
     * @throws Exception
     */
    @PostMapping("/pcPay/notify")
    public String  pcPaynotify(HttpServletRequest request) throws Exception {

        // 1. 验签
        boolean vaild = ZfbVerifierUtils.isVaild(request, zfbPayConfig.getPublicKey());
        if (vaild){
            iZfbPayService.updateOrderStatus(request);
        }else {
            log.info("支付宝验签失败");
           return "fail";
        }
        return "success";
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
        iZfbPayService.refund(orderNo,reason);
        return BaseResult.ok();
    }


    /**
     * 查询订单
     *
     * @param orderNo
     * @return
     */
    @GetMapping("/queryOrder/{orderNo}")
    public BaseResult queryOrder(@PathVariable String orderNo) throws Exception {
        return BaseResult.ok(iZfbPayService.queryPayStauts(orderNo));
    }

}
