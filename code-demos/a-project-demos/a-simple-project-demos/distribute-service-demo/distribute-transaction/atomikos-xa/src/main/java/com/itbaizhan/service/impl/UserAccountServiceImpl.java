package org.example.payment.zhifubao.service.impl;

import org.example.payment.zhifubao.entity.UserAccount;
import com.itbaizhan.mapper1.UserAccountMapper1;
import com.itbaizhan.mapper2.UserAccountMapper2;
import org.example.payment.zhifubao.service.IUserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author itbaizhan
 * @since 05-13
 */
@Service
public class UserAccountServiceImpl  implements IUserAccountService {


    @Autowired
    private UserAccountMapper1 userAccountMapper1;

    @Autowired
    private UserAccountMapper2 userAccountMapper2;


    /**
     * 跨库转账
     * @param sourceAccountNo 源账户
     * @param targetSourceNo 目标账户
     * @param bigDecimal 金额
     */
    @Transactional
    @Override
    public void transofer(String sourceAccountNo, String targetSourceNo, BigDecimal bigDecimal) {

        // 1. 查询原账户
        UserAccount sourceUserAccount = userAccountMapper1.selectById(sourceAccountNo);
        // 2. 查询目标账户
        UserAccount targetUserAccount = userAccountMapper2.selectById(targetSourceNo);
        // 3. 判断转入账户和转出账户是否为空
        if (sourceAccountNo != null && targetUserAccount != null){
            // 4. 判断转出账户是否余额不足
            if (sourceUserAccount.getAccountBalance().compareTo(bigDecimal) < 0){
                throw  new RuntimeException("余额不足");
            }
            // 5.更新金额
            sourceUserAccount.setAccountBalance(sourceUserAccount.getAccountBalance().subtract(bigDecimal));
            // 6.张三账户减金额
            userAccountMapper1.updateById(sourceUserAccount);

            System.out.println(10/0);

            // 7.更新金额
            targetUserAccount.setAccountBalance(targetUserAccount.getAccountBalance().add(bigDecimal));
            // 8.张三账户减金额
            userAccountMapper2.updateById(targetUserAccount);

        }






    }
}
