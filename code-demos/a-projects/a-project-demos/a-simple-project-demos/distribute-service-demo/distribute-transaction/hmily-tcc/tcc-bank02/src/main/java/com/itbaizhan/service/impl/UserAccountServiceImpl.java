package com.itbaizhan.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.itbaizhan.api.UserAccountBank02Service;
import com.itbaizhan.dto.UserAccountDTO;
import com.itbaizhan.entity.CancelLog;
import com.itbaizhan.entity.ConfirmLog;
import com.itbaizhan.entity.TryLog;
import com.itbaizhan.entity.UserAccount;
import com.itbaizhan.mapper.CancelLogMapper;
import com.itbaizhan.mapper.ConfirmLogMapper;
import com.itbaizhan.mapper.TryLogMapper;
import com.itbaizhan.mapper.UserAccountMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 账户信息 服务实现类
 * </p>
 *
 * @author itbaizhan
 * @since 05-15
 */

@Slf4j
@DubboService(version = "1.0.0")
public class UserAccountServiceImpl  implements UserAccountBank02Service {


    @Autowired
    private UserAccountMapper userAccountMapper;

    @Resource
    private TryLogMapper tryLogMapper;
    @Resource
    private ConfirmLogMapper confirmLogMapper;
    @Resource
    private CancelLogMapper cancelLogMapper;


    /**
     * 跨库转账
     * @param userAccountDTO
     */
    @HmilyTCC(confirmMethod = "sayConfrim", cancelMethod = "sayCancel")
    @Override
    public void transferAmountToBank02(UserAccountDTO userAccountDTO) {

        String txNo = userAccountDTO.getTxNo();
        log.info("**********   执行bank02 的 Try方法 ，事务id={}",txNo);



        // 1、 幂等处理
        TryLog tryLog = tryLogMapper.selectById(txNo);
        if (tryLog != null){
            return ;
        }
        // 2、 悬挂处理
        if (confirmLogMapper.selectById(txNo) != null || cancelLogMapper.selectById(txNo) != null){
            return ;
        }
        // 3. 根据账户编号查询账户信息
        UserAccount userAccount = userAccountMapper.selectById(userAccountDTO.getTargetAccountNo());
        // 4. 判断账户是否存在
        if (userAccount != null){
            // 5. 账户金额更新
            LambdaUpdateWrapper<UserAccount> ulw = new LambdaUpdateWrapper<>();
            // 更新转账金额
            ulw.set(UserAccount::getTransferAmount,userAccount.getTransferAmount().add(userAccountDTO.getBigDecimal()));
            ulw.eq(UserAccount::getAccountNo,userAccountDTO.getTargetAccountNo());
            userAccountMapper.update(null,ulw);
        }

        // 7. 准备阶段记录
        TryLog tryLog1 = new TryLog();
        tryLog1.setTxNo(txNo);
        tryLog1.setCreateTime(LocalDateTime.now());
        tryLogMapper.insert(tryLog1);


    }

    /**
     * 确认阶段
     * @param userAccountDTO
     */
    public void sayConfrim(UserAccountDTO userAccountDTO) {
        String txNo = userAccountDTO.getTxNo();
        log.info("**********   执行bank02 的 Confrim方法 ，事务id={}",txNo);
        // 1、幂等处理
        ConfirmLog confirmLog = confirmLogMapper.selectById(txNo);
        if (confirmLog != null) {
            return;
        }
        // 2、根据账户id查询账户
        UserAccount userAccount = userAccountMapper.selectById(userAccountDTO.getTargetAccountNo());
        userAccount.setAccountBalance(userAccount.getAccountBalance().add(userAccountDTO.getBigDecimal()));
        userAccount.setTransferAmount(userAccount.getTransferAmount().subtract(userAccountDTO.getBigDecimal()));
        userAccountMapper.updateById(userAccount);

        // 3、 确认日志记录

        ConfirmLog confirmLog1 = new ConfirmLog();
        confirmLog1.setTxNo(userAccountDTO.getTxNo());
        confirmLog1.setCreateTime(LocalDateTime.now());
        confirmLogMapper.insert(confirmLog1);


    }

    /**
     * 取消阶段
     * @param userAccountDTO
     */
    public void sayCancel(UserAccountDTO userAccountDTO) {
        String txNo = userAccountDTO.getTxNo();
        log.info("**********   执行bank02 的Cancel方法 ，事务id={}",txNo);
        // 1. 幂等处理
        CancelLog cancelLog = cancelLogMapper.selectById(txNo);
        if (cancelLog != null ){
            return;
        }
        // 2、根据账户id查询账户
        UserAccount userAccount = userAccountMapper.selectById(userAccountDTO.getTargetAccountNo());
        userAccount.setTransferAmount(userAccount.getTransferAmount().subtract(userAccountDTO.getBigDecimal()));
        userAccountMapper.updateById(userAccount);

        // 3、记录回滚日志
        CancelLog cancelLog1 = new CancelLog();
        cancelLog1.setTxNo(txNo);
        cancelLog1.setCreateTime(LocalDateTime.now());
        cancelLogMapper.insert(cancelLog1);
    }
}
