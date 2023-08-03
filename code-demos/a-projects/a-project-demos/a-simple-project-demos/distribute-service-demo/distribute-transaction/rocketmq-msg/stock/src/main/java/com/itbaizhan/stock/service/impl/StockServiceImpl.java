package com.itbaizhan.stock.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itbaizhan.stock.entity.Stock;
import com.itbaizhan.stock.entity.TxLog;
import com.itbaizhan.stock.mapper.StockMapper;
import com.itbaizhan.stock.mapper.TxLogMapper;
import com.itbaizhan.stock.service.IStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itbaizhan.stock.tx.TxMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author itbaizhan
 * @since 05-21
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements IStockService {

    @Resource
    private TxLogMapper txLogMapper;



    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateStock(TxMessage txMessage) {

        //1 . 根据事务编号获取事务
        TxLog txLog = txLogMapper.selectById(txMessage.getTxNo());
        //2. 判断事务记录是否存在
        if(txLog != null){
            return ;
        }

        // 3. 根据商品id查询库存
        LambdaQueryWrapper<Stock> stockLambdaQueryWrapper = new LambdaQueryWrapper<>();
        stockLambdaQueryWrapper.eq(Stock::getProductId,txMessage.getProductId());
        List<Stock> stocks = baseMapper.selectList(stockLambdaQueryWrapper);
        // 4. 判断库存是否存在
        if (stocks != null && !stocks.isEmpty()){
            Stock stock = stocks.get(0);
            // 5. 判断库存足不足
            if (stock.getTotalCount() < txMessage.getPayCount()){
                throw  new RuntimeException("存库不足");
            }
            stock.setTotalCount(stock.getTotalCount() - txMessage.getPayCount());
            baseMapper.updateById(stock);
        }

        // 5.添加事务日志记录
        TxLog txLog1 = new TxLog();
        txLog1.setTxNo(txMessage.getTxNo());
        txLog1.setCreateTime(LocalDateTime.now());
        txLogMapper.insert(txLog1);

    }
}
