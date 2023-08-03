package com.itbaizhan.orders.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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
@TableName("tx_log")
public class TxLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分布式事务全局序列号
     */
    @TableId
      private String txNo;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
