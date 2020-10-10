package com.hungwen.cloudmall.product.dao;

import com.hungwen.cloudmall.product.entity.SpuInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * spu資料
 * 
 * @author Hungwen Tseng
 * @email hungwen.tseng@gmail.com
 * @date 2020-08-25 10:21:04
 */
@Mapper
public interface SpuInfoDao extends BaseMapper<SpuInfoEntity> {

    void updaSpuStatus(Long spuId, int code);
}
