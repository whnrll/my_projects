package com.itbaizhan.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 统一结果封装类
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResult<T>  {

    // 状态码
    private  Integer code;

    //响应信息
    private  String message;

    // 数据
    private T data;

    // 构建请求成功结果
    public static <T> BaseResult<T> ok(){
        return new BaseResult<>(CodeEnum.SUCCESS.getCode(),CodeEnum.SUCCESS.getMessage(),null);
    }

    // 构建请求成功结果
    public static <T> BaseResult<T> ok(T data){
        return new BaseResult<>(CodeEnum.SUCCESS.getCode(),CodeEnum.SUCCESS.getMessage(),data);
    }

    // 构建请求成功结果
    public static <T> BaseResult<T> error(CodeEnum codeEnum){
        return new BaseResult<>(codeEnum.getCode(),codeEnum.getMessage(),null);
    }








}
