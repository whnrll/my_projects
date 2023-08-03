package org.example.payment.zhifubao.service;

import com.itbaizhan.dto.UserAccountDTO;
import org.example.payment.zhifubao.entity.UserAccount;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 账户信息 服务类
 * </p>
 *
 * @author itbaizhan
 * @since 05-15
 */
public interface IUserAccountService extends IService<UserAccount> {


    /**
     * 跨库转账
     * @param userAccountDTO
     */
    void transferAmountToBank02(UserAccountDTO userAccountDTO);




}
