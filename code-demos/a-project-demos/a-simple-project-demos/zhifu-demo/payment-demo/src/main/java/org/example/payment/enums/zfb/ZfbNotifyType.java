package org.example.payment.enums.zfb;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ZfbNotifyType {
    /**
     * 支付通知
     */
    PC_NOTIFY("/api/zfb-pay/pcPay/notify"),
    /**
     * 退款结果通知
     */
    REFUND_NOTIFY("/api/zfb-pay/refunds/notify");
    /**
     * 类型
     */
    private final String type;
}
