package com.itbaizhan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itbaizhan.entity.AccountInfo;
import com.itbaizhan.fegin.Bank2ServiceFeign;
import com.itbaizhan.mapper.AccountMapper;
import com.itbaizhan.service.IAccountService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * 转账业务层
 */
@Slf4j
@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountMapper accountMapper;


    @Autowired
    private Bank2ServiceFeign bank2ServiceFeign;

    /**
     * 张三转账功能
     * @param accountNo 银行卡号
     * @param amount 转账金额
     */
    @GlobalTransactional
    @Transactional
    public void updateAccountBalance(String accountNo, Double amount) {
        log.info("bank1 张三 XID:{}", RootContext.getXID());
        // 1. 获取账户信息
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("account_no",accountNo);
        AccountInfo accountInfo = accountMapper.selectOne(queryWrapper);
        // 2. 判断账户是否存在
        if (accountInfo != null){
            accountInfo.setAccountBalance(accountInfo.getAccountBalance() - amount);
            // 3. 更新张三账户信息
            accountMapper.updateById(accountInfo);

            // TODO 远程调用李四的钱增加
            bank2ServiceFeign.transfer("2",amount);

        }



    }
}
