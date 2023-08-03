package org.example.payment.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CodeEnum {

    // 成功
    SUCCESS(200,"SUCCESS"),

    // 系统异常
    SYSTEM_ERROR(500,"系统异常"),
    PARAMETER_ERROR(500,"参数缺失"),

    // 支付异常
    ORDER_ERROR(600,"订单不存在"),
    PAYMENT_ERROR(601,"支付异常");


    // 状态码
    private final Integer code;

    //响应信息
    private final String message;





}
