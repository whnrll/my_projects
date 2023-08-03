package com.itbaizhan.orders.tx;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TxMessage implements Serializable {

    private Long productId;//商品id
    private Integer payCount;//购买数量
    private String txNo;// 全局事务编号

}
