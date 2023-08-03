package com.itbaizhan.stock.entity;

import java.io.Serializable;

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
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId
      private Long id;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 商品总库存
     */
    private Integer totalCount;


}
