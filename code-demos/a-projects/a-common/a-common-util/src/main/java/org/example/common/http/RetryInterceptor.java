package org.example.common.http;

import java.io.IOException;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Slf4j
public class RetryInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        return retry(chain);
    }

    public Response retry(Chain chain) throws IOException {
        Request request = chain.request();
        ParentRetryRequestParam retryTag = request.tag(ParentRetryRequestParam.class);
        Response response = null;
        try {
            response = chain.proceed(request);
            if (!response.isSuccessful()
                    && Objects.nonNull(retryTag)
                    && retryTag.getCurrentCount() < retryTag.getRetryCount()) {
                retryTag.setCurrentCount(retryTag.getCurrentCount() + 1);
                response = retry(chain);
            }
        } catch (Exception e) {
            assert retryTag != null;
            log.info("RetryInterceptor: request {} is not successful-> request count {} because {}",
                    request.url(), retryTag.getCurrentCount(), e.getMessage());
            if (retryTag.getCurrentCount() < retryTag.getRetryCount()) {
                retryTag.setCurrentCount(retryTag.getCurrentCount() + 1);
                response = retry(chain);
            }
        }
        //同步请求直接抛出异常，外层调用函数处理，异步请求则进入回调接口处理。
        if (Objects.isNull(response)) {
            throw new IOException("重复请求" + retryTag.getCurrentCount() + "次后,请求失败");
        }
        return response;
    }
}
