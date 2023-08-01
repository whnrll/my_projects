package com.itbaizhan.api;

import com.itbaizhan.dto.UserAccountDTO;
import org.dromara.hmily.annotation.Hmily;

public interface UserAccountBank02Service {


    /**
     * 跨库转账
     * @param userAccountDTO
     */
    @Hmily
    void transferAmountToBank02(UserAccountDTO userAccountDTO);


}
