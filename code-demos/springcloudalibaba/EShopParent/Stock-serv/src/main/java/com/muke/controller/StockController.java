package com.muke.controller;

import com.muke.dao.StockResposity;
import com.muke.entity.Stock;
import io.seata.core.context.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;


/**
 * @author ziya
 * @date 2021/2/22 12:50 上午
 */
@RestController
public class StockController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private StockResposity resposity;
    @Autowired
    private Random random;

    @GetMapping("/stock/reduce/{productId}")
    public String reduce(@PathVariable Integer productId) {
        LOGGER.info("Storage Service Begin ... xid: " + RootContext.getXID());
        if (random.nextBoolean()) {
            throw new RuntimeException("this is a mock Exception");
        }
        Stock stock = resposity.getFirstByProductId(productId);
        if (stock != null) {
            stock.setCount(stock.getCount() - 1);
            resposity.save(stock);
            return "success";
        }
        return "fail";
    }
}