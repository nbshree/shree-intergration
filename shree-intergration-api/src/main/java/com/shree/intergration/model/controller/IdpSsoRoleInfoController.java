package com.shree.intergration.model.controller;


import com.baomidou.mybatisplus.mapper.Wrapper;
import com.shree.intergration.model.entity.IdpSsoRoleInfo;
import com.shree.intergration.model.service.IdpSsoRoleInfoService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 统一平台角色结构信息 前端控制器
 * </p>
 *
 * @author sry123
 * @since 2020-05-15
 */
@RestController
@RequestMapping("/idpSsoRoleInfo")
public class IdpSsoRoleInfoController {

    @Resource
    IdpSsoRoleInfoService idpSsoRoleInfoService;

    @RequestMapping(value = "monitor/list/mode/map", method = {RequestMethod.GET, RequestMethod.POST})
    public String doMonitorMapListQuery() {
        try {
            QueryWrapper<IdpSsoRoleInfo> queryWrapper =
            queryWrapper.eq(new IdpSsoRoleInfo(), ProjectConst.RecordStatus.Active);
            if (null != lastUpdateTime) {
                queryWrapper.ge(EsealMonitor.Columns.modifyTime, lastUpdateTime);
            }
            List<IdpSsoRoleInfo> list = this.idpSsoRoleInfoService.selectList();
        }
        return "!2";
    }
}

