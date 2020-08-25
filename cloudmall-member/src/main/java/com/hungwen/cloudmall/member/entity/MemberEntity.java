package com.hungwen.cloudmall.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 會員
 * 
 * @author Hungwen Tseng
 * @email hungwen.tseng@gmail.com
 * @date 2020-08-25 14:49:31
 */
@Data
@TableName("ums_member")
public class MemberEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */
	@TableId
	private Long id;
	/**
	 * $column.comments
	 */
	private Long levelId;
	/**
	 * $column.comments
	 */
	private String username;
	/**
	 * $column.comments
	 */
	private String password;
	/**
	 * $column.comments
	 */
	private String nickname;
	/**
	 * $column.comments
	 */
	private String mobile;
	/**
	 * $column.comments
	 */
	private String email;
	/**
	 * $column.comments
	 */
	private String header;
	/**
	 * $column.comments
	 */
	private Integer gender;
	/**
	 * $column.comments
	 */
	private Date birth;
	/**
	 * $column.comments
	 */
	private String city;
	/**
	 * $column.comments
	 */
	private String job;
	/**
	 * $column.comments
	 */
	private String sign;
	/**
	 * $column.comments
	 */
	private Integer sourceType;
	/**
	 * $column.comments
	 */
	private Integer integration;
	/**
	 * $column.comments
	 */
	private Integer growth;
	/**
	 * $column.comments
	 */
	private Integer status;
	/**
	 * $column.comments
	 */
	private Date createTime;
	/**
	 * $column.comments
	 */
	private String socialuid;
	/**
	 * $column.comments
	 */
	private String accesstoken;
	/**
	 * $column.comments
	 */
	private Long expiresin;

}