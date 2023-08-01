package com.bjsxt.frontend.portal.controller;

import com.bjsxt.frontend.portal.service.ContentService;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页内容管理Controller
 */
@RestController
@RequestMapping("/frontend/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    /**
     * 查询首页大广告位
     */
    @RequestMapping("/selectFrontendContentByAD")
    public Result selectFrontendContentByAD(){
        try{
            Thread.sleep(10000);
            return this.contentService.selectFrontendContentByAD();
        }catch(Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }
}
