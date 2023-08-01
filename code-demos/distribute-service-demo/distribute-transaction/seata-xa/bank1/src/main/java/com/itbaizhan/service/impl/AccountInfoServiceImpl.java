package com.itbaizhan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itbaizhan.entity.AccountInfo;
import com.itbaizhan.fegin.Bank2ServiceFeign;
import com.itbaizhan.mapper1.AccountInfoMapper;
import com.itbaizhan.service.IAccountInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author itbaizhan
 * @since 05-12
 */
@Slf4j
@Service
public class AccountInfoServiceImpl extends ServiceImpl<AccountInfoMapper, AccountInfo> implements IAccountInfoService {


    @Autowired
    Bank2ServiceFeign bank2ServiceFeign;

    /**
     * 张三减少金额
     * @param accountNo 银行卡号
     * @param amount 金额
     */
    @GlobalTransactional
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateAccountBalance(String accountNo, Double amount) {
        log.info("**************** bank1 张三 XID:{}", RootContext.getXID());
        // 1. 根据银行卡号查询账户信息
        LambdaQueryWrapper<AccountInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AccountInfo::getAccountNo,accountNo);
        AccountInfo accountInfo = baseMapper.selectOne(queryWrapper);
        // 2. 判断账户是否为空
        if (accountInfo  != null){
            accountInfo.setAccountBalance(accountInfo.getAccountBalance() - amount);
            // 3. 更新张三账户
            baseMapper.updateById(accountInfo);

            // 4. 远程调用李四账户增加
            bank2ServiceFeign.transfer("2",amount);
            System.out.println(10/0);

        }

    }

}
