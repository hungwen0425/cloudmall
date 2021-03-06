/**
 * Copyright (c) 2016-2019 人人開源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版權所有，侵權必究！
 */

package io.renren.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.sys.entity.SysUserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统使用者Token
 *
 * @author hungwen.tseng@gmail.com
 */
@Mapper
public interface SysUserTokenDao extends BaseMapper<SysUserTokenEntity> {

    SysUserTokenEntity queryByToken(String token);
	
}
