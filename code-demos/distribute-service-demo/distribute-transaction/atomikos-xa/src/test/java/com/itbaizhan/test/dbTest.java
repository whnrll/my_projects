package com.itbaizhan.test;

import com.itbaizhan.entity.UserAccount;
import com.itbaizhan.mapper1.UserAccountMapper1;
import com.itbaizhan.mapper2.UserAccountMapper2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class dbTest {

    @Autowired
    private UserAccountMapper1 userAccountMapper1;

    @Autowired
    private UserAccountMapper2 userAccountMapper2;


    @Test
    public void test(){
        UserAccount userAccount1 = userAccountMapper1.selectById("1001");
        System.out.println(userAccount1.toString());
        UserAccount userAccount2 = userAccountMapper2.selectById("1002");
        System.out.println(userAccount2.toString());
    }


}
