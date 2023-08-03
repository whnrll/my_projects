package org.example.micro.driver.feign.fallback;


import feign.hystrix.FallbackFactory;
import org.example.micro.driver.feign.DriverFeign;
import org.example.micro.driver.model.Driver;
import org.springframework.stereotype.Component;

@Component
public class DriverFeignFallBackFactory implements FallbackFactory<DriverFeign> {
    @Override
    public DriverFeign create(Throwable cause) {
        return new DriverFeign() {
            @Override
            public Driver status(String id, Integer status) {
                System.out.println("走降级");
                return new Driver();
            }
        };
    }
}
