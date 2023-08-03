package org.example.payment.zhifubao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.payment.zhifubao.entity.AccountInfo;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

@Mapper
public interface AccountMapper extends BaseMapper<AccountInfo> {
}
