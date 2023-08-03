package com.bjsxt.content.service;

import com.bjsxt.pojo.TbContentCategory;
import com.bjsxt.utils.PageResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ContentCategoryService {

    List<TbContentCategory> selectContentCategoryByParentId(Long parentId );
    Integer insertContentCategory(TbContentCategory tbContentCategory);
    Integer deleteContentCategoryById(Long categoryId);
    Integer updateContentCategory(TbContentCategory tbContentCategory);
}
