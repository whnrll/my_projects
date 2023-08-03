package com.bjsxt.backend.item.service.impl;
import com.bjsxt.backend.item.feign.CommonItemFeignClient;
import com.bjsxt.backend.item.service.ItemService;
import com.bjsxt.pojo.TbItem;
import com.bjsxt.pojo.TbItemDesc;
import com.bjsxt.pojo.TbItemParamItem;
import com.bjsxt.utils.IDUtils;
import com.bjsxt.utils.PageResult;
import com.bjsxt.utils.Result;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;


/**
 * 商品管理
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private CommonItemFeignClient commonItemFeignClient;

    @Override
    public Result selectTbItemAllByPage(Integer page, Integer rows) {
        PageResult pageResult = this.commonItemFeignClient.selectTbItemAllByPage(page,rows);
        if(pageResult != null && pageResult.getResult() != null && pageResult.getResult().size() > 0){
            return Result.ok(pageResult);
        }
        return Result.error("查无结果");
    }

    /**
     * 添加TbItem，添加TbitemDesc，添加TbItemParamItem
     * @param tbItem
     * @param desc
     * @param itemParams
     * @return
     */
    @Override
    @LcnTransaction
    public Result insertTbItem(TbItem tbItem, String desc, String itemParams){
        //补齐Tbitem数据
        Long itemId = IDUtils.genItemId();
        Date date = new Date();
        tbItem.setId(itemId);
        tbItem.setStatus((byte)1);
        tbItem.setUpdated(date);
        tbItem.setCreated(date);
       Integer tbItemNum = this.commonItemFeignClient.insertTbItem(tbItem);

       //补齐商品描述对象
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);
        Integer tbitemDescNum = this.commonItemFeignClient.insertItemDesc(tbItemDesc);

        //补齐商品规格参数
        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setItemId(itemId);
        tbItemParamItem.setParamData(itemParams);
        tbItemParamItem.setUpdated(date);
        tbItemParamItem.setCreated(date);
        Integer itemParamItmeNum = this.commonItemFeignClient.insertTbItemParamItem(tbItemParamItem);

        if(tbItemNum == null || tbitemDescNum== null ||itemParamItmeNum ==null ){
                throw new RuntimeException();
        }
        return Result.ok();
    }

    /**
     * 删除商品
     * @param itemId
     * @return
     */
    @Override
    @LcnTransaction
    public Result deleteItemById(Long itemId){
        TbItem item = new TbItem();
        item.setId(itemId);
        item.setStatus((byte)3);
        Integer itemNum = this.commonItemFeignClient.deleteItemById(item);
        if(itemNum ==  null){
            throw new RuntimeException();
        }
        return Result.ok();
    }

    /**
     * 预更新商品查询
     * @param itemId
     * @return
     */
    @Override
    public Result preUpdateItem(Long itemId) {
        Map<String,Object> map = this.commonItemFeignClient.preUpdateItem(itemId);
        if(map != null){
            return Result.ok(map);
        }
        return Result.error("查无结果");
    }

    /**
     * 更新商品：更新TbItem表，更新TbitemDesc表，更新TbItempParamItem表
     * @param tbItem
     * @param desc
     * @param itemParams
     * @return
     */
    @Override
    @LcnTransaction
    public Result updateTbItem(TbItem tbItem, String desc, String itemParams) {
        //更新商品
        Integer itemNum = this.commonItemFeignClient.updateTbitem(tbItem);

        //更新商品描述
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(tbItem.getId());
        tbItemDesc.setItemDesc(desc);
        Integer itemDescNum = this.commonItemFeignClient.updateItemDesc(tbItemDesc);

        //更新商品规格参数
        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setParamData(itemParams);
        tbItemParamItem.setItemId(tbItem.getId());
        Integer itemParamItemNum = this.commonItemFeignClient.upateItemParamItem(tbItemParamItem);

        if(itemNum ==null || itemDescNum == null || itemParamItemNum ==null){
            throw  new RuntimeException();
        }
        return Result.ok();
    }

}
