package com.bjsxt.frontend.portal.service.impl;

import com.bjsxt.frontend.portal.feign.CommonContentFeignClient;
import com.bjsxt.frontend.portal.feign.CommonRedisFeignClient;
import com.bjsxt.frontend.portal.service.ContentService;
import com.bjsxt.utils.Result;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 首页内容管理Service
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private CommonContentFeignClient commonContentFeignClient;

    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;

    /**
     * 查询首页大广告位
     * @return
     */
    @Override
    public Result selectFrontendContentByAD() {
        //查询缓存
        try{
            List<Map> list = this.commonRedisFeignClient.selectContentAD();
            if(list != null && list.size() > 0){
                return Result.ok(list);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        //查询数据库
        List<Map> list = this.commonContentFeignClient.selectFrontendContentByAD();
        if(list != null && list.size() > 0){
            //将查询到数据添加到缓存中
            this.commonRedisFeignClient.insertContentAD(list);
        }

        if(list != null && list.size() > 0){
            return Result.ok(list);
        }
        return Result.error("查无结果");
    }
}
