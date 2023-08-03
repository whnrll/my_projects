package com.bjsxt.frontend.portal.controller;

import com.bjsxt.frontend.portal.service.ItemService;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 查询商品Controller
 */
@RestController
@RequestMapping("/frontend/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 查询商品基本信息
     */
    @RequestMapping("/selectItemInfo")
    public Result selectItemInfo(Long itemId){
        try{
        return this.itemService.selectItemInfo(itemId);
        }catch(Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");

    }

    /**
     * 查询商品介绍
     */
    @RequestMapping("/selectItemDescByItemId")
    public Result selectItemDescByItemId(Long itemId){
        try{
            return this.itemService.selectItemDescByItemId(itemId);
        }catch(Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    /**
     * 根据商品ID查询商品规格参数
     */
    @RequestMapping("/selectTbItemParamItemByItemId")
    public Result selectTbItemParamItemByItemId(Long itemId){
        try{
            return this.itemService.selectTbItemParamItemByItemId(itemId);
        }catch(Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }
}
