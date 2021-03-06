package com.hungwen.cloudmall.seckill.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Created: with IntelliJ IDEA.
 * @author: Hungwen Tseng
 * @createTime: 2020-11-29 21:12
 **/

@Data
public class SeckillSessionWithSkusVo {

    private Long id;
    /**
     * 場次名稱
     */
    private String name;
    /**
     * 每日開始時間
     */
    private Date startTime;
    /**
     * 每日結束時間
     */
    private Date endTime;
    /**
     * 啟用狀態
     */
    private Integer status;
    /**
     * 創建時間
     */
    private Date createTime;

    private List<SeckillSkuVo> relationSkus;

}
