package org.example.payment.utils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ZfbVerifierUtils {


    /**
     * 验证支付宝异步通知签名合法性
     * @param request 支付宝发送的请求
     * @param alipayPublicKey 支付宝公钥
     * @return
     */
    public static boolean isVaild(HttpServletRequest request,String alipayPublicKey){

        // 1. 获取支付宝post发送过来的信息
        Map<String,String> resutlMap = new HashMap<>();
        Map<String, String[]> requestParameterMap = request.getParameterMap();
        for (Object v: requestParameterMap.entrySet()){
            Map.Entry<String,String[]> item =  (Map.Entry<String,String[]>)v;
            resutlMap.put(item.getKey(),item.getValue()[0]);
        }
        try {
            return AlipaySignature.rsaCheckV1(resutlMap,alipayPublicKey,"UTF-8","RSA2");
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return  false;
    }



}
