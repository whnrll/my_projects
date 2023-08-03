package org.example.datasource.multi.dao.biz;


import org.example.common.utils.GlobalConstants;
import org.example.dataaccess.entity.product.TradeStock;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;


@Repository
public interface ITradeStockDao {

    @Cacheable(value= GlobalConstants.STOCK_PRODUCT_KEY)
    public TradeStock getById(Long id);

}