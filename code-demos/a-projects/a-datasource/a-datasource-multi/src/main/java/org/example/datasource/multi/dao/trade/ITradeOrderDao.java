package org.example.datasource.multi.dao.trade;

import org.example.dataaccess.entity.trade.TradeOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface ITradeOrderDao {

    /**
     * 新增记录
     * @param record
     * @return
     */
    int insert(TradeOrder record);

    /**
     * 根据ID获取订单
     * @param id
     * @return
     */
    TradeOrder getById(Long id);
}
