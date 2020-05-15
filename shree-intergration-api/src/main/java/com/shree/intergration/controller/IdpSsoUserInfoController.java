package com.shree.intergration.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shree.intergration.model.entity.IdpSsoUserInfo;
import com.shree.intergration.model.service.IdpSsoUserInfoService;
import com.shree.intergration.vo.result.RestResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 统一平台用户信息 前端控制器
 * </p>
 *
 * @author shree
 * @since 2020-05-15
 */
@CrossOrigin
@RestController
@RequestMapping("/api/userInfo/")
public class IdpSsoUserInfoController {
    @Resource
    IdpSsoUserInfoService idpSsoUserInfoService;

    @RequestMapping(value = "/getUserInfo", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResult<List<IdpSsoUserInfo>> doGetUserInfo() {
        RestResult<List<IdpSsoUserInfo>> result = RestResult.createFailedResult("查询失败！");
        try {
            QueryWrapper<IdpSsoUserInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("STATUS", 1);
            List<IdpSsoUserInfo> list = this.idpSsoUserInfoService.list(queryWrapper);
            result.setSuccessResult("查询成功", list);
        } catch (Exception ex) {
            result.setErrorResult("查询出错");
        }
        return result;
    }
}

