package com.itbaizhan.itbaizhanlock.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author itbaizhan
 * @since 05-26
 */
@Getter
@Setter
@TableName("t_order")
public class TOrder implements Serializable {

    private static final long serialVersionUID = 1L;

     @TableId(type = IdType.ASSIGN_ID)//雪花算法
      private String id;

    /**
     * 订单状态 1 待支付 2已支付
     */
    private Integer orderStatus;

    /**
     * 收货人名字
     */
    private String receiverName;

    /**
     * 收货人手机
     */
    private String receiverMobile;

    /**
     * 订单价格
     */
    private BigDecimal orderAmount;


}
