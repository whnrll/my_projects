package com.bjsxt.item.service;

import com.bjsxt.pojo.TbItemParamItem;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface ItemParamItemService {
    Integer insertTbItemParamItem(TbItemParamItem tbItemParamItem);
    Integer upateItemParamItem(TbItemParamItem tbItemParamItem);
    TbItemParamItem selectTbItemParamItemByItemId(Long itemId);
}
