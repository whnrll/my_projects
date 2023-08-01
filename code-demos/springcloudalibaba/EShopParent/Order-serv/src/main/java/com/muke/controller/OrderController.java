package com.muke.controller;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.naming.NacosNamingService;
import com.muke.dao.OrderResposity;
import com.muke.entity.Order;
import io.seata.spring.annotation.GlobalTransactional;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;


/**
 * @author ziya
 * @date 2021/2/20 3:58 下午
 */
@RestController
public class OrderController {
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrderResposity orderResposity;

    @GlobalTransactional(timeoutMills = 300000, name = "order-service")
    @GetMapping("/order/create")
    public String createOrder(Integer productId, Integer userId) {

        Order order = new Order();
        order.setProductId(productId);
        order.setUserId(userId);
        orderResposity.save(order);
        String result = restTemplate.getForObject("http://stock-serv/stock/reduce/" + productId, String.class);
        if (!result.equals("success")) {
            throw new RuntimeException();
        }
        return result;
    }

    @GetMapping("/order")
    public String getStock() {
        List<ServiceInstance> list = discoveryClient.getInstances("stock-serv");
        String a="";
        for(ServiceInstance serviceInstance:list){
            a=a+" "+serviceInstance.getHost();
        }
        return a;
    }

}
