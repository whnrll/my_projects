package org.example.dataaccess.entity;

import lombok.Data;

/**
 * 描述：序列号
 *
 * @author xutao
 * @date 2023-08-03 20:48:14
 * @since 1.0.0
 */
@Data
public class TradeSeq {

    /**
     * 业务编号
     */
    private String code;

    /**
     * 序列值
     */
    private Long nextId;
}
