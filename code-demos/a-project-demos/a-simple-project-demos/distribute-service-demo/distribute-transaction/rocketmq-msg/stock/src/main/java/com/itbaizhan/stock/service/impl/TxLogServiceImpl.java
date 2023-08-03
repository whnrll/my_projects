package com.itbaizhan.stock.service.impl;

import com.itbaizhan.stock.entity.TxLog;
import com.itbaizhan.stock.mapper.TxLogMapper;
import com.itbaizhan.stock.service.ITxLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author itbaizhan
 * @since 05-21
 */
@Service
public class TxLogServiceImpl extends ServiceImpl<TxLogMapper, TxLog> implements ITxLogService {

}
