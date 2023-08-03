package org.example.datasource.multi.dao.biz;

import org.example.common.utils.GlobalConstants;
import org.example.dataaccess.entity.user.TradeAccount;
import org.example.dataaccess.entity.user.TradeGroup;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 * <p>Description: </p>
 * @date 2019/7/24
 * @author 贺锟 
 * @version 1.0
 * @name  Mirson
 * <p>Copyright:Copyright(c)2019</p>
 */
@Repository
public interface ITradeAccountDao {

    /**
     * 新增用户账号
     * @param record
     * @return
     */
    int insert(TradeAccount record);


    /**
     * 根据用户ID获取账号(多个只取第一个)
     * @param userId
     * @return
     */
    TradeAccount getByUserId(Long userId);


    /**
     * 根据账户ID获取对象
     * @param accountId
     * @return
     */
    @Cacheable(value= GlobalConstants.STOCK_TRADE_ACCOUNT_KEY)
    TradeAccount getById(Long accountId);


    /**
     * 根据ID获取交易账户组
     * @param accountId
     * @return
     */
    @Cacheable(value= GlobalConstants.STOCK_TRADE_GROUP_ACCOUNT_KEY)
    TradeGroup getTradeGroupByAccountId(Long accountId);


}
