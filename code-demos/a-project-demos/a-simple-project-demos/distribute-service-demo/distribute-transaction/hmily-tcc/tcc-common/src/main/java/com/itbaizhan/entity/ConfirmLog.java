package org.example.payment.zhifubao.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * Confirm阶段执行的日志记录
 * </p>
 *
 * @author itbaizhan
 * @since 05-15
 */
@Getter
@Setter
@TableName("confirm_log")
public class ConfirmLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 全局事务编号
     */
    @TableId
      private String txNo;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
