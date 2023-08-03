package org.example.payment.controller.dto;

import lombok.Data;

@Data
public class OrderInfoDTO {

    /**
     * 订单标题
     */
    private String title;
    /**
     * 订单金额(分)
     */
    private Integer totalFee;

}
