package handler;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import util.JwtAuthTokenFactory;

public class FeignClientRequestHandler implements RequestInterceptor {
    private final JwtAuthTokenFactory jwtTokenFactory;

    public FeignClientRequestHandler(JwtAuthTokenFactory jwtAuthTokenFactory) {
        this.jwtTokenFactory = jwtAuthTokenFactory;
    }

    @Override
    public void apply(RequestTemplate template) {
        String token = jwtTokenFactory.createToken().getToken();
        template.header("token", token);
    }
}
