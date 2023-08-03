package org.example.common.web.dto.resp;

import org.example.common.exception.constants.ApplicationErrorCodeEnum;
import org.example.common.exception.constants.IErrorCodeEnum;

import java.io.Serializable;

/**
 * 描述：统一API接口数据返回对象
 *
 * @author xutao
 * @date 2023-08-03 20:39:22
 * @since 1.0.0
 */
public class ApiRespResult<T> implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 结果码
     */
    private String code = ApplicationErrorCodeEnum.SUCCESS.getCode();

    /**
     * 结果信息
     */
    private String msg = ApplicationErrorCodeEnum.SUCCESS.getMessage();

    /**
     * 扩展对象(放置分页信息、其他信息等)
     */
    private Object extendData;

    /**
     * 返回结果的数据对象
     */
    private T data;

    public ApiRespResult() {
    }

    public ApiRespResult(String code) {
        this.code = code;
    }

    public ApiRespResult(String code, String message){
        this.code = code;
        this.msg = message;
    }

    public ApiRespResult(IErrorCodeEnum errorCodeEnum){
        this.code = errorCodeEnum.getCode();
        this.msg = errorCodeEnum.getMessage();
    }


    //  *** 错误信息封装方法 *＊＊
    public static <T> ApiRespResult<T> error(IErrorCodeEnum errorCodeEnum){
        return new ApiRespResult<T>(errorCodeEnum);
    }

    public static <T> ApiRespResult<T> sysError(String exceptionMsg){
        ApiRespResult error =  new ApiRespResult<T>(ApplicationErrorCodeEnum.FAILURE);
        error.setMsg(error.getMsg() + ":" + exceptionMsg);
        return error;
    }

    public static <T> ApiRespResult<T> error(String code, String msg){
        return new ApiRespResult<T>(code,msg);
    }

    public static <T> ApiRespResult<T> error(String code, String msg,T data){
        return new ApiRespResult<T>(code,msg).setData(data);
    }

    /**
     * 用于参数校验提示信息
     * @param exceptionMsg
     * @param <T>
     * @return
     */
    public static <T> ApiRespResult<T> validError(String exceptionMsg) {
        ApiRespResult error = new ApiRespResult<T>(ApplicationErrorCodeEnum.PARAMS_NOT_VALID);
        error.setMsg(error.getMsg() + ":" + exceptionMsg);
        return error;
    }

    //  *** 错误信息封装方法 end *＊＊

    //  *** 成功信息封装方法 *＊＊
    public static  ApiRespResult<Void> success(){
        return success(null);
    }

    public static <T> ApiRespResult<T> success(T data){
        return success(data, null);
    }

    public static <T> ApiRespResult<T> success(T data, Object extendData ){
        return new ApiRespResult<T>().setData(data).setExtendData(extendData);
    }

    public Boolean isSuccess(){
        return ApplicationErrorCodeEnum.SUCCESS.getCode().equals(getCode());
    }
    //  *** 成功信息封装方法 end *＊＊


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getExtendData() {
        return extendData;
    }

    public ApiRespResult<T> setExtendData(Object extendData) {
        this.extendData = extendData;
        return this;
    }

    public T getData() {
        return data;
    }

    public ApiRespResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "[code=" + code + ", msg=" + msg + ", extendData=" + extendData + ", data=" + data + "]";
    }
}
