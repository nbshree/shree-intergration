package com.shree.intergration.model.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shree.intergration.model.entity.IdpSsoUserInfo;
import com.shree.intergration.model.service.IdpSsoUserInfoService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 统一平台角色结构信息 前端控制器
 * </p>
 *
 * @author riusky
 * @since 2020-05-15
 */
@RestController
@RequestMapping("/idp-sso-role-info")
public class IdpSsoRoleInfoController {
    @Resource
    IdpSsoUserInfoService idpSsoUserInfoService;
    @RequestMapping(value = "/test", method = {RequestMethod.GET, RequestMethod.POST})
    public List<IdpSsoUserInfo> doOnStorageStatistics() {
        QueryWrapper<IdpSsoUserInfo> queryWrapper = new QueryWrapper<>();
//        queryWrapper.isNotNull("ID");
        List<IdpSsoUserInfo> result = this.idpSsoUserInfoService.list(queryWrapper);
        return result;
    }



}

