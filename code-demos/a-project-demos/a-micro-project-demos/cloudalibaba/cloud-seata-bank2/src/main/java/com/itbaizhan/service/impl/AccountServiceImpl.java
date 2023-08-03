package org.example.payment.zhifubao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.payment.zhifubao.entity.AccountInfo;
import org.example.payment.zhifubao.mapper.AccountMapper;
import org.example.payment.zhifubao.service.IAccountService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 转账业务层
 */
@Slf4j
@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountMapper accountMapper;


    /**
     * 转账
     * @param accountNo 银行卡号
     * @param amount 转账金额
     */
    @Transactional
    public void updateAccountBalance(String accountNo, Double amount) {
        log.info("bank1 张三 XID:{}", RootContext.getXID());
        // 1. 获取银行卡信息
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("account_no",accountNo);
        AccountInfo accountInfo = accountMapper.selectOne(queryWrapper);
        // 2. 判断账户信息是否空
        if (accountInfo != null){
            accountInfo.setAccountBalance(accountInfo.getAccountBalance() + amount);
            // 3. 更新账户
            accountMapper.updateById(accountInfo);
        }


    }
}
