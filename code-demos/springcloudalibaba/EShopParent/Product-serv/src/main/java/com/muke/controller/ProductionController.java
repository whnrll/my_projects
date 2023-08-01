package com.muke.controller;

import com.muke.entity.SkillGood;
import com.muke.service.SkillGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author ziya
 * @date 2021/2/22 12:34 上午
 */
@RestController
public class ProductionController {

    @Autowired
    private SkillGoodService skillGoodService;

    @GetMapping("/product/{productId}")
    @ResponseBody
    public String getProduct(@PathVariable Long productId) {
        System.out.println("调用商品服务");
        return "我是新版本";
    }

    @PostMapping("/product")
    public String update(@RequestBody SkillGood skillGood){
        skillGoodService.update(skillGood);
        return "更新库存成功";
    }
}
