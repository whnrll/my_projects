package org.example.payment.vo;

import lombok.Data;

/**
 * 支付返回结果vo
 */
@Data
public class PayInfoVO {

    // 二维码连接
    private String codeUrl;
    // 订单
    private String orderNo;

}
