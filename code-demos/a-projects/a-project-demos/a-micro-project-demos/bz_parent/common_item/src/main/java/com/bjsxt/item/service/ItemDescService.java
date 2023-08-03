package com.bjsxt.item.service;

import com.bjsxt.pojo.TbItemDesc;

public interface ItemDescService {
    Integer insertTbitemDesc(TbItemDesc tbItemDesc);
    Integer updateItemDesc(TbItemDesc tbItemDesc);
    TbItemDesc selectItemDescByItemId(Long itemId);
}
