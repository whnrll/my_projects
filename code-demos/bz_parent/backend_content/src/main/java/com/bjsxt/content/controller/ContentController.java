package com.bjsxt.content.controller;

import com.bjsxt.content.service.ContentService;
import com.bjsxt.pojo.TbContent;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.MalformedParameterizedTypeException;

@RestController
@RequestMapping("/content")

public class ContentController {


    @Autowired
    private ContentService contentService;

    /**
     * 根据分类ID查询内容
     */
    @RequestMapping("/selectTbContentAllByCategoryId")
    public Result selectTbContentAllByCategoryId(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "30") Integer rows,Long categoryId){
        try{
            return this.contentService.selectTbContentAllByCategoryId(page,rows,categoryId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    /**
     * 根据分类添加内容
     */
    @RequestMapping("/insertTbContent")
    public Result insertTbContent(TbContent tbContent){
        try{
            return this.contentService.insertTbContent(tbContent);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    /**
     * 删除分类的内容
     */
    @RequestMapping("/deleteContentByIds")
    public Result deleteContentByIds(Long ids){
        try{
            return this.contentService.deleteContentByIds(ids);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

}
