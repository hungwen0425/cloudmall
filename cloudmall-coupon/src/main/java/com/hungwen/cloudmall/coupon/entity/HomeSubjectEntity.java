package com.hungwen.cloudmall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 首頁專題表【jd首頁下面很多專題，每個專題鏈接新的頁面，展示專題商品資料】
 * 
 * @author Hungwen Tseng
 * @email hungwen.tseng@gmail.com
 * @date 2020-08-25 14:33:48
 */
@Data
@TableName("sms_home_subject")
public class HomeSubjectEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 專題名稱
	 */
	private String name;
	/**
	 * 專題標題
	 */
	private String title;
	/**
	 * 專題副標題
	 */
	private String subTitle;
	/**
	 * 顯示狀態
	 */
	private Integer status;
	/**
	 * 詳情連接
	 */
	private String url;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 專題圖片地址
	 */
	private String img;

}
