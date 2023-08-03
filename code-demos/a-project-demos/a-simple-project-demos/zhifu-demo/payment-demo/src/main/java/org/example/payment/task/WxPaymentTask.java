package org.example.payment.task;

import org.example.payment.entity.OrderInfo;
import org.example.payment.service.IOrderInfoService;
import org.example.payment.service.IWxPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * 微信任务调度
 */
@Component
@Slf4j
public class WxPaymentTask {

    @Autowired
    IOrderInfoService iOrderInfoService; // 订单接口

    @Autowired
    IWxPaymentService iWxPaymentService;

    // 秒 分 时 天 月 周
//    @Scheduled(cron = "0/3 * * * * *")
    public void test(){
        log.info("**********  test 执行了 ******");
    }

    /**
     * 从0秒开始每隔30秒执行一次，查询创建超过5分钟，并且没有支付的订单
     */
//    @Scheduled(cron = "0/3 * * * * *")
    public void  timeoutOrder() throws IOException {
        log.info("**********  开始查询超过订单 ********");
        List<OrderInfo> timeOutOrder = iOrderInfoService.getTimeOutOrder(5);
        for (OrderInfo orderInfo : timeOutOrder) {
            iWxPaymentService.checkOrderStatus(orderInfo.getOrderNo());
        }
    }





}
