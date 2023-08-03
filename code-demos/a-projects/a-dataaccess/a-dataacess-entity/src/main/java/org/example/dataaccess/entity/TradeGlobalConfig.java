package org.example.dataaccess.entity;

import org.example.dataaccess.entity.base.BaseEntity;

import lombok.Data;

/**
 * 描述：全局系统配置表
 *
 * @author xutao
 * @date 2023-08-03 20:48:07
 * @since 1.0.0
 */
@Data
public class TradeGlobalConfig extends BaseEntity {

    /**
     * 主键标识
     */
    private Long id;

    /**
     * 配置项编号
     */
    private String code;

    /**
     * 分类编号(BASIC:基础配置， BUSINESS： 业务配置, SYSTEM：系统项配置)
     */
    private String category;

    /**
     * 配置项的值
     */
    private String value;

    /**
     * 状态(0:启用， 1：禁用）
     */
    private int status;

    /**
     *
     * @mbg.generated Tue Jan 07 10:22:01 CST 2020
     */
    private static final long serialVersionUID = 1L;

}