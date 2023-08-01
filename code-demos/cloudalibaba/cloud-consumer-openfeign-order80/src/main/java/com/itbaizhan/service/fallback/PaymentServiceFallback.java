package com.itbaizhan.service.fallback;


import com.alibaba.csp.sentinel.slots.block.BlockException;

public class PaymentServiceFallback{

    // 接口异常降级方法
    public static String testFallBack(String id,Throwable e){
        return "接口异常降级~";
    }

    // Sentinel控制台配置异常降级方法
    public static String testBlockHeader(String id, BlockException e){
        return "sentinel控制配置异常降级~";
    }

}
