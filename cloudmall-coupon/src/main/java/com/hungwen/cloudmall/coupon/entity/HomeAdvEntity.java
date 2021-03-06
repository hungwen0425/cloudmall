package com.hungwen.cloudmall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 首頁輪播廣告
 * 
 * @author Hungwen Tseng
 * @email hungwen.tseng@gmail.com
 * @date 2020-08-25 14:33:48
 */
@Data
@TableName("sms_home_adv")
public class HomeAdvEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 名字
	 */
	private String name;
	/**
	 * 圖片地址
	 */
	private String pic;
	/**
	 * 開始時間
	 */
	private Date startTime;
	/**
	 * 結束時間
	 */
	private Date endTime;
	/**
	 * 狀態
	 */
	private Integer status;
	/**
	 * 點擊數
	 */
	private Integer clickCount;
	/**
	 * 廣告詳情連接地址
	 */
	private String url;
	/**
	 * 備註
	 */
	private String note;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 發布者
	 */
	private Long publisherId;
	/**
	 * 審核者
	 */
	private Long authId;

}
