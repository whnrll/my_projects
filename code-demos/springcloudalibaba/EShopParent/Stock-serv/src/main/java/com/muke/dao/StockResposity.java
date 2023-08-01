package com.muke.dao;

import com.muke.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockResposity extends JpaRepository<Stock, Integer> {
    // 通过商品ID查询库存信息
    public Stock getFirstByProductId(Integer productId);
}
