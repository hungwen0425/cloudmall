package com.hungwen.cloudmall.search.vo;

import com.hungwen.common.to.es.SkuEsModel;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Created: with IntelliJ IDEA.
 * @author: Hungwen Tseng
 * @createTime: 2020-10-13 14:41
 **/
@Data
public class SearchResult {
    /**
     * 查詢到的所有商品資料
     */
    private List<SkuEsModel> product;
    /**
     * 當前頁碼
     */
    private Integer pageNum;
    /**
     * 總記錄數
     */
    private Long total;
    /**
     * 總頁碼
     */
    private Integer totalPages;
    private List<Integer> pageNavs;
    /**
     * 當前查詢到的結果，所有涉及到的品牌
     */
    private List<BrandVo> brands;
    /**
     * 當前查詢到的結果，所有涉及到的所有屬性
     */
    private List<AttrVo> attrs;
    /**
     * 當前查詢到的結果，所有涉及到的所有分類
     */
    private List<CatalogVo> catalogs;
    //===========================以上是返回給頁面的所有資料============================//
    /* 麵包屑導航資料 */
    private List<NavVo> navs;

    @Data
    public static class NavVo {
        private String navName;
        private String navValue;
        private String link;
    }

    @Data
    public static class BrandVo {
        private Long brandId;
        private String brandName;
        private String brandImg;
    }

    @Data
    public static class AttrVo {
        private Long attrId;
        private String attrName;
        private List<String> attrValue;
    }

    @Data
    public static class CatalogVo {
        private Long catalogId;
        private String catalogName;
    }
}
