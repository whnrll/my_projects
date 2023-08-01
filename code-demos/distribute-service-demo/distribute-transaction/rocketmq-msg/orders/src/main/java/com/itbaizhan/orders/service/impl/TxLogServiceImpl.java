package com.itbaizhan.orders.service.impl;

import com.itbaizhan.orders.entity.TxLog;
import com.itbaizhan.orders.mapper.TxLogMapper;
import com.itbaizhan.orders.service.ITxLogService;
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
