package com.itbaizhan.enums.wx;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WxNotifyType {
    /**
     * 支付通知
     * http://kalista.natapp1.cc/api/wx-pay/native/notify
     */
    NATIVE_NOTIFY("/api/wx-pay/native/notify"),
    /**
     * 退款结果通知
     */
    REFUND_NOTIFY("/api/wx-pay/refunds/notify");
    /**
     * 类型
     */
    private final String type;
}
