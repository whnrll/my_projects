package com.itbaizhan.orders.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author itbaizhan
 * @since 05-21
 */
@Getter
@Setter
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 购买数量
     */
    private Integer payCount;


}
