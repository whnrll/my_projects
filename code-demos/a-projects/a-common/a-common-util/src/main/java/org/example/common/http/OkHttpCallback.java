package org.example.common.http;

import okhttp3.Call;

/**
 * 描述：OkHttp 回调接口
 *
 * @author xutao
 * @date 2023-04-14 20:52:06
 * @since 1.0.0
 */
public interface OkHttpCallback<T> {

    /**
     * 描述：失败回调
     *
     * @param call call
     * @param e e
     */
    void failed(Call call, Exception e);

    /**
     * 描述：成功回调
     *
     * @param call
     * @param t
     */
    void success(Call call, T t);
}
