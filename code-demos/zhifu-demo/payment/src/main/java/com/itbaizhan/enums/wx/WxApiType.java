package com.itbaizhan.enums.wx;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WxApiType {
    /**
     * Native下单
     * https://api.mch.weixin.qq.com/v3/pay/transactions/native
     */
    NATIVE_PAY("/v3/pay/transactions/native"),
    /**
     * 查询订单
     * https://api.mch.weixin.qq.com/v3/pay/transactions/id/{transaction_id}
     * https://api.mch.weixin.qq.com/v3/pay/transactions/out-trade-no/{out_trade_no}
     */
    ORDER_QUERY_BY_NO("/v3/pay/transactions/out-trade-no/%s"),

    /**
     * 关闭订单
     */
    CLOSE_ORDER_BY_NO("/v3/pay/transactions/out-trade-no/%s/close"),

    /**
     * 申请退款
     */
    DOMESTIC_REFUNDS("/v3/refund/domestic/refunds"),

    /**
     * 查询单笔退款
     */
    DOMESTIC_REFUNDS_QUERY("/v3/refund/domestic/refunds/%s");


    /**
     * 类型
     */
    private final String type;
}
