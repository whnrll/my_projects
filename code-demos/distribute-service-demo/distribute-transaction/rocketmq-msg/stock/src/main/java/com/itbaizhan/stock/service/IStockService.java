package com.itbaizhan.stock.service;

import com.itbaizhan.stock.entity.Stock;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itbaizhan.stock.tx.TxMessage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author itbaizhan
 * @since 05-21
 */
public interface IStockService extends IService<Stock> {


    /**
     * 减库存
     * @param txMessage
     */
    void updateStock(TxMessage txMessage);


}
