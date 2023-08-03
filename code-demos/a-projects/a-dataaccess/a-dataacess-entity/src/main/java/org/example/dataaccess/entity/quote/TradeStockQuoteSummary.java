package org.example.dataaccess.entity.quote;

import javax.persistence.Entity;
import javax.persistence.Table;


import lombok.Data;
import org.example.dataaccess.entity.base.BaseEntity;

/**
 * 
 *  股票报价概要表
 *
 */
@Entity
@Table(name="t_trade_stock_quote_summary")
@Data
public class TradeStockQuoteSummary extends BaseEntity {

    /**
     */
    private static final long serialVersionUID = -1L;
    /**
     * 产品ID
     */
    private Long stockId;

    /**
     * 产品编号
     */
    private String stockCode;

    /**
     * 产品名称
     */
    private String stockName;

    /**
     * 开盘价
     */
    private Long openPrice;

    /**
     * 收盘价
     */
    private Long closePrice;

    /**
     * 开盘时间
     */
    private Long openPriceTime;

    /**
     * 收盘时间
     */
    private Long closePriceTime;

}