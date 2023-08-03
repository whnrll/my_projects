package org.example.dataaccess.mybatis.mapper;


import org.springframework.stereotype.Repository;

import com.itcast.bulls.stock.entity.system.TradeGlobalConfig;
import com.itcast.bulls.stock.entity.system.TradeSeq;

/**
 * 全局系统配置数据层接口
 */
@Repository
public interface ITradeGlobalConfigMapper {

    /**
     * 新增全局系统配置
     * @param record
     * @return
     */
    int insert(TradeGlobalConfig record);

    /**
     * 根据编号查询对象
     * @param code
     * @return
     */
    TradeGlobalConfig getByCode(String code);

    /**
     * 根据编号获取序列ID
     * @param code
     * @return
     */
    int getNextId(TradeSeq record);

}