package com.hungwen.cloudmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hungwen.common.utils.PageUtils;
import com.hungwen.cloudmall.product.entity.CategoryBrandRelationEntity;

import java.util.Map;

/**
 * 品牌分類關聯
 *
 * @author Hungwen Tseng
 * @email hungwen.tseng@gmail.com
 * @date 2020-08-25 10:21:04
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
