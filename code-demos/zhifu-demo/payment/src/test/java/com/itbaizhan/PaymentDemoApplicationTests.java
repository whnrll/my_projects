package com.itbaizhan;

import com.itbaizhan.config.WxPayConfig;
import com.itbaizhan.entity.OrderInfo;
import com.itbaizhan.mapper.OrderInfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.security.PrivateKey;
import java.util.List;

@SpringBootTest
class PaymentDemoApplicationTests {

    @Resource
    OrderInfoMapper orderInfoMapper;

    @Test
    void contextLoads() {
        List<OrderInfo> orderInfos = orderInfoMapper.selectList(null);
        for (OrderInfo orderInfo : orderInfos) {
            System.out.println(orderInfo.toString());
        }

    }


    @Autowired
    WxPayConfig wxPayConfig;

    @Test
    void testGetPrivateKey(){

        // 获取私钥名字
//        String privateKeyPath = wxPayConfig.getPrivateKeyPath();
//        // 获取商户私钥
//        PrivateKey privateKey = wxPayConfig.getPrivateKey(privateKeyPath);
//        System.out.println(privateKey.toString());


    }

}
