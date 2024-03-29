package com.hungwen.cloudmall.product.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.alibaba.nacos.client.utils.StringUtils;
import com.hungwen.cloudmall.product.entity.SkuImagesEntity;
import com.hungwen.cloudmall.product.entity.SpuInfoDescEntity;
import com.hungwen.cloudmall.product.feign.SecKillFeignService;
import com.hungwen.cloudmall.product.service.*;
import com.hungwen.cloudmall.product.vo.*;
import com.hungwen.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hungwen.common.utils.PageUtils;
import com.hungwen.common.utils.Query;

import com.hungwen.cloudmall.product.dao.SkuInfoDao;
import com.hungwen.cloudmall.product.entity.SkuInfoEntity;

@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {

    @Autowired
    private ThreadPoolExecutor executor;
    @Autowired
    private SpuInfoDescService spuInfoDescService;
    @Autowired
    private AttrGroupService attrGroupService;
    @Autowired
    private SkuImagesService skuImagesService;
    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;
    @Autowired
    private SecKillFeignService secKillFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                new QueryWrapper<SkuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSkuInfo(SkuInfoEntity skuInfoEntity) {
        this.baseMapper.insert(skuInfoEntity);
    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {

        QueryWrapper<SkuInfoEntity> queryWrapper = new QueryWrapper<>();

        String key=(String)params.get("key");
        if(StringUtils.isNotEmpty(key)){
            queryWrapper.and(item ->{
                item.eq("sku_id",key).or().like("sku_name",key);
            });
        }

        String catelogId=(String)params.get("catelogId");
        if(StringUtils.isNotEmpty(catelogId) && ! "0".equalsIgnoreCase(catelogId)){
            queryWrapper.eq("catalog_id",catelogId);
        }

        String brandId=(String)params.get("brandId");
        if(StringUtils.isNotEmpty(brandId) && ! "0".equalsIgnoreCase(brandId)){
            queryWrapper.eq("brand_id",brandId);
        }

        String min=(String)params.get("min");
        if(StringUtils.isNotEmpty(min)){
            queryWrapper.ge("price",min);
        }

        String max=(String)params.get("max");
        if(StringUtils.isNotEmpty(max)){
            try {
                BigDecimal bigDecimal = new BigDecimal(max);
                if( bigDecimal.compareTo(new BigDecimal("0")) == 1){
                    queryWrapper.le("price",max);
                }
            } catch (Exception e) {
            }
        }

        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),queryWrapper
        );
        return new PageUtils(page);
    }

    @Override
    public List<SkuInfoEntity> getSkusBySpuId(Long spuId) {
        List<SkuInfoEntity> skuInfoEntities = this.list(new QueryWrapper<SkuInfoEntity>().eq("spu_id", spuId));
        return skuInfoEntities;
    }

//    @Override // 異步
//    public SkuItemVo item(Long skuId) throws ExecutionException, InterruptedException {
//        SkuItemVo skuItemVo = new SkuItemVo();
//
//        CompletableFuture<SkuInfoEntity> infoFuture = CompletableFuture.supplyAsync(() -> {
//            // 1. 查詢 sku 基本資料  pms_sku_info
//            SkuInfoEntity info = this.getById(skuId);
//            skuItemVo.setInfo(info);
//            return info;
//        }, executor);
//
//        CompletableFuture<Void> saleAttrFuture = infoFuture.thenAcceptAsync((res) -> {
//            // 3. 查詢 spu 的銷售屬性組合
//            List<SkuItemSaleAttrVo> saleAttrVos = skuSaleAttrValueService.getSaleAttrBySpuId(res.getSpuId());
//            skuItemVo.setSaleAttrs(saleAttrVos);
//        }, executor);
//
//        CompletableFuture<Void> descFuture = infoFuture.thenAcceptAsync((res) -> {
//            // 4. 查詢 spu 的介紹    pms_spu_info_desc
//            SpuInfoDescEntity spuInfoDescEntity = spuInfoDescService.getById(res.getSpuId());
//            skuItemVo.setDesc(spuInfoDescEntity);
//        }, executor);
//
//        CompletableFuture<Void> baseAttrFuture = infoFuture.thenAcceptAsync((res) -> {
//            // 5. 查詢 spu 的規格參數資料
//            List<SpuItemAttrGroupVo> attrGroupVos = attrGroupService.getAttrGroupWithAttrsBySpuId(res.getSpuId(), res.getCatalogId());
//            skuItemVo.setAttrGroups(attrGroupVos);
//        }, executor);
//
//        CompletableFuture<Void> imageFuture = CompletableFuture.runAsync(() -> {
//            // 2. sku 的圖片資料    pms_sku_images
//            List<SkuImagesEntity> imagesEntities = skuImagesService.getImagesBySkuId(skuId);
//            skuItemVo.setImages(imagesEntities);
//        }, executor);
//
//        // Long spuId = info.getSpuId();
//        // Long catalogId = info.getCatalogId();
//        CompletableFuture<Void> seckillFuture = CompletableFuture.runAsync(() -> {
//            //6、遠程調用查詢當前 sku 是否參與限時搶購優惠活動
//            R secKillSkuVo = secKillFeignService.getSkuSeckilInfo(skuId);
//            if (secKillSkuVo.getCode() == 0) {
//                SecKillSkuVo data = secKillSkuVo.getData(new TypeReference<SecKillSkuVo>() {
//                });
//                skuItemVo.setSecKillSkuVo(data);
//            }
//        }, executor);
//        //等到所有任務都完成
//        CompletableFuture.allOf(saleAttrFuture, descFuture, baseAttrFuture, imageFuture, seckillFuture).get();
//        return skuItemVo;
//    }


    @Override // 同步
    public SkuItemVo item(Long skuId) throws ExecutionException, InterruptedException {
        SkuItemVo skuItemVo = new SkuItemVo();
        // 1. 查詢 sku 基本資料  pms_sku_info
        SkuInfoEntity info = this.getById(skuId);
        skuItemVo.setInfo(info);
        // 2. sku 的圖片資料    pms_sku_images
        List<SkuImagesEntity> imagesEntities = skuImagesService.getImagesBySkuId(skuId);
        skuItemVo.setImages(imagesEntities);
        // 3. 查詢 spu 的銷售屬性組合
        List<SkuItemSaleAttrVo> saleAttrVos = skuSaleAttrValueService.getSaleAttrBySpuId(info.getSpuId());
        skuItemVo.setSaleAttrs(saleAttrVos);
        // 4. 查詢 spu 的介紹    pms_spu_info_desc
        SpuInfoDescEntity spuInfoDescEntity = spuInfoDescService.getById(info.getSpuId());
        skuItemVo.setDesc(spuInfoDescEntity);
        // 5. 查詢 spu 的規格參數資料
        List<SpuItemAttrGroupVo> attrGroupVos = attrGroupService.getAttrGroupWithAttrsBySpuId(info.getSpuId(), info.getCatalogId());
        skuItemVo.setAttrGroups(attrGroupVos);
        //6、遠程調用查詢當前 sku 是否參與限時搶購優惠活動
        R secKillSkuVo = secKillFeignService.getSkuSeckilInfo(skuId);
        if (secKillSkuVo.getCode() == 0) {
            SecKillSkuVo data = secKillSkuVo.getData(new TypeReference<SecKillSkuVo>() {
            });
            skuItemVo.setSecKillSkuVo(data);
        }
        return skuItemVo;
    }
}