package com.bjsxt.frontend.portal.controller;

import com.bjsxt.frontend.portal.service.ItemCategoryService;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页商品分类
 */
@RestController
@RequestMapping("/frontend/itemCategory")
public class ItemCategoryController {


    @Autowired
    private ItemCategoryService itemCategoryService;

    /**
     * 查询首页商品分类
     */
    @RequestMapping("/selectItemCategoryAll")
    public Result selectItemCategoryAll(){
        try{
        return this.itemCategoryService.selectItemCategoryAll();
        }catch(Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }
}
