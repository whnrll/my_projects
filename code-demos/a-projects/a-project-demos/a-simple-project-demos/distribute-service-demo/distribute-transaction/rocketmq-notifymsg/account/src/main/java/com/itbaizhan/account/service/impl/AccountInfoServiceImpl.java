package com.itbaizhan.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itbaizhan.account.entity.AccountInfo;
import com.itbaizhan.account.entity.PayInfo;
import com.itbaizhan.account.feign.PayFeginService;
import com.itbaizhan.account.mapper.AccountInfoMapper;
import com.itbaizhan.account.mapper.PayInfoMapper;
import com.itbaizhan.account.service.IAccountInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author itbaizhan
 * @since 05-23
 */
@Slf4j
@Service
public class AccountInfoServiceImpl extends ServiceImpl<AccountInfoMapper, AccountInfo> implements IAccountInfoService {


    @Resource
    private PayInfoMapper payInfoMapper;


    @Resource
    private PayFeginService payFeginService;

    /**
     * 更新账户余额
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateAccount(PayInfo payInfo) {

        // 1. 根据事务编号查询交易记录
        PayInfo payInfo1 = payInfoMapper.selectById(payInfo.getTxNo());
        if (payInfo1 != null){
            log.info("账户微服务已经处理过当前事务。。。。。。");
            return;
        }
        // 2. 根据账户编号查询账户信息
        LambdaQueryWrapper<AccountInfo> accountInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        accountInfoLambdaQueryWrapper.eq(AccountInfo::getAccountNo,payInfo.getAccountNo());
        List<AccountInfo> accountInfos = baseMapper.selectList(accountInfoLambdaQueryWrapper);
        if (accountInfos != null && !accountInfos.isEmpty()){
            AccountInfo accountInfo = accountInfos.get(0);
            accountInfo.setAccountBalance(accountInfo.getAccountBalance().add(payInfo.getPayAmount()));
            // 更新操作
            baseMapper.updateById(accountInfo);
        }

        // 3. 保存充值记录
        payInfoMapper.insert(payInfo);
    }

    @Override
    public PayInfo queryPayment(String txNo) {
        return payFeginService.queryPayment(txNo);
    }


}
