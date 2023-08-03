package org.example.payment.zhifubao.mapper;

import org.example.payment.zhifubao.entity.UserAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * <p>
 * 账户信息 Mapper 接口
 * </p>
 *
 * @author itbaizhan
 * @since 05-15
 */
public interface UserAccountMapper extends BaseMapper<UserAccount> {

    /**
     * 更新转出账户余额
     */
    int updateUserAccountBalanceBank01(@Param("amount") BigDecimal amount, @Param("accountNo") String accountNo);

    /**
     * 转出账户余额确认接口
     */
    int confirmUserAccountBalanceBank01(@Param("amount") BigDecimal amount, @Param("accountNo") String accountNo);

    /**
     * 转出账户余额取消接口
     */
    int cancelUserAccountBalanceBank01(@Param("amount") BigDecimal amount, @Param("accountNo") String accountNo);

}
