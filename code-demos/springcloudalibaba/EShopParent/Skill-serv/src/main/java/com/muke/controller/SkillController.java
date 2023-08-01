package com.muke.controller;

import com.muke.service.SkillGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SkillController {

    @Autowired
    private SkillGoodService skillGoodService;

    @GetMapping("/skill")
    public String add(Long productId,String userId) {
        try{
            skillGoodService.add(productId,userId);
            return "抢单成功";
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
