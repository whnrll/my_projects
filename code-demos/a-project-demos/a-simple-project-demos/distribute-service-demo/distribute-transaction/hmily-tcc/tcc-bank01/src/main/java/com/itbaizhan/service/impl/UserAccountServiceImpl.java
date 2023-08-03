package org.example.payment.zhifubao.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.itbaizhan.api.UserAccountBank02Service;
import com.itbaizhan.dto.UserAccountDTO;
import org.example.payment.zhifubao.entity.CancelLog;
import org.example.payment.zhifubao.entity.ConfirmLog;
import org.example.payment.zhifubao.entity.TryLog;
import org.example.payment.zhifubao.entity.UserAccount;
import org.example.payment.zhifubao.mapper.CancelLogMapper;
import org.example.payment.zhifubao.mapper.ConfirmLogMapper;
import org.example.payment.zhifubao.mapper.TryLogMapper;
import org.example.payment.zhifubao.mapper.UserAccountMapper;
import org.example.payment.zhifubao.service.IUserAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.swing.*;
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
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements IUserAccountService {

    @DubboReference(version = "1.0.0")
    private UserAccountBank02Service userAccountBank02Service;

    @Resource
    private TryLogMapper tryLogMapper;

    @Resource
    private ConfirmLogMapper confirmLogMapper;

    @Resource
    private CancelLogMapper cancelLogMapper;



    /**
     * 转账功能
     * @param userAccountDTO
     */
    @HmilyTCC(confirmMethod = "sayConfrim", cancelMethod = "sayCancel")
    @Override
    public void transferAmountToBank02(UserAccountDTO userAccountDTO) {

        String txNo = userAccountDTO.getTxNo();
        log.info("**********   执行bank01 的 Try方法 ，事务id={}",txNo);

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
        UserAccount userAccount = baseMapper.selectById(userAccountDTO.getSourceAccountNo());
        // 4. 判断账户是否存在
        if (userAccount != null){
            // 5. 账户金额更新
            LambdaUpdateWrapper<UserAccount> ulw = new LambdaUpdateWrapper<>();
            // 更新转账金额
            ulw.set(UserAccount::getTransferAmount,userAccount.getTransferAmount().add(userAccountDTO.getBigDecimal()));
            // 更新余额
            ulw.set(UserAccount::getAccountBalance,userAccount.getAccountBalance().subtract(userAccountDTO.getBigDecimal()));
            ulw.eq(UserAccount::getAccountNo,userAccountDTO.getSourceAccountNo());
            baseMapper.update(null,ulw);
        }

        // 7. 准备阶段记录
        TryLog tryLog1 = new TryLog();
        tryLog1.setTxNo(txNo);
        tryLog1.setCreateTime(LocalDateTime.now());
        tryLogMapper.insert(tryLog1);

         // 8. 远程调用 转入微服务 跨库转账的功能
        userAccountBank02Service.transferAmountToBank02(userAccountDTO);

    }


    /**
     * 确认阶段
     * @param userAccountDTO
     */
    public void sayConfrim(UserAccountDTO userAccountDTO) {

        String txNo = userAccountDTO.getTxNo();
        log.info("**********   执行bank01 的 Confrim方法 ，事务id={}",txNo);
        // 1、幂等处理
        ConfirmLog confirmLog = confirmLogMapper.selectById(txNo);
        if (confirmLog != null){
            return ;
        }
        // 2、根据账户id查询账户
        UserAccount userAccount = baseMapper.selectById(userAccountDTO.getSourceAccountNo());
        userAccount.setTransferAmount(userAccount.getTransferAmount().subtract(userAccountDTO.getBigDecimal()));
        baseMapper.updateById(userAccount);

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
        log.info("**********   执行bank01 的Cancel方法 ，事务id={}",txNo);
        // 1. 幂等处理
        CancelLog cancelLog = cancelLogMapper.selectById(txNo);
        if (cancelLog != null ){
            return;
        }
        // 2、根据账户id查询账户
        UserAccount userAccount = baseMapper.selectById(userAccountDTO.getSourceAccountNo());
        userAccount.setAccountBalance(userAccount.getAccountBalance().add(userAccountDTO.getBigDecimal()));
        userAccount.setTransferAmount(userAccount.getTransferAmount().subtract(userAccountDTO.getBigDecimal()));
        baseMapper.updateById(userAccount);

        // 3、记录回滚日志
        CancelLog cancelLog1 = new CancelLog();
        cancelLog1.setTxNo(txNo);
        cancelLog1.setCreateTime(LocalDateTime.now());
        cancelLogMapper.insert(cancelLog1);
    }

}
