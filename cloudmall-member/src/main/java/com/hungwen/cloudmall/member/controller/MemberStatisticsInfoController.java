package com.hungwen.cloudmall.member.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hungwen.cloudmall.member.entity.MemberStatisticsInfoEntity;
import com.hungwen.cloudmall.member.service.MemberStatisticsInfoService;
import com.hungwen.common.utils.PageUtils;
import com.hungwen.common.utils.R;



/**
 * 會員統計資料
 *
 * @author Hungwen Tseng
 * @email hungwen.tseng@gmail.com
 * @date 2020-08-25 14:49:31
 */
@RestController
@RequestMapping("member/memberstatisticsinfo")
public class MemberStatisticsInfoController {
    @Autowired
    private MemberStatisticsInfoService memberStatisticsInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("member:memberstatisticsinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberStatisticsInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 資料
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("member:memberstatisticsinfo:info")
    public R info(@PathVariable("id") Long id){
		MemberStatisticsInfoEntity memberStatisticsInfo = memberStatisticsInfoService.getById(id);

        return R.ok().put("memberStatisticsInfo", memberStatisticsInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("member:memberstatisticsinfo:save")
    public R save(@RequestBody MemberStatisticsInfoEntity memberStatisticsInfo){
		memberStatisticsInfoService.save(memberStatisticsInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("member:memberstatisticsinfo:update")
    public R update(@RequestBody MemberStatisticsInfoEntity memberStatisticsInfo){
		memberStatisticsInfoService.updateById(memberStatisticsInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:memberstatisticsinfo:delete")
    public R delete(@RequestBody Long[] ids){
		memberStatisticsInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
