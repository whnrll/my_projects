package org.example.micro.driver.feign;

import org.example.micro.driver.model.Driver;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(value = "hailtaxi-driver")
public interface DriverFeign {

    /****
     * 更新司机信息，该方法和hailtaxi-driver服务中的方法保持一致
     */
    @PutMapping(value = "/driver/status/{id}/{status}")
    Driver status(@PathVariable(value = "id") String id, @PathVariable(value = "status") Integer status);
}