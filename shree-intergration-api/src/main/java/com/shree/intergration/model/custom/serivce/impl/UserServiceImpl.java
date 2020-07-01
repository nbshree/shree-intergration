package com.shree.intergration.model.custom.serivce.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shree.intergration.common.common.ProjectConst;
import com.shree.intergration.common.security.util.CommonSecurityUtils;
import com.shree.intergration.common.util.MybatisPlusUtils;
import com.shree.intergration.common.web.rest.layui.LayPagination;
import com.shree.intergration.model.custom.entity.UserInfoVo;
import com.shree.intergration.model.custom.serivce.UserService;
import com.shree.intergration.model.major.entity.*;
import com.shree.intergration.model.major.service.IdpRoleResourceMapService;
import com.shree.intergration.model.major.service.IdpUserInfoService;
import com.shree.intergration.model.major.service.IdpUserRoleMapService;
import com.shree.intergration.util.AppResources;
import com.shree.intergration.util.ConstUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

    @Resource
    IdpUserInfoService idpUserInfoService;
    @Resource
    IdpUserRoleMapService idpUserRoleMapService;
    @Resource
    IdpRoleResourceMapService idpRoleResourceMapService;

    @Override
    public Page<UserInfoVo> getPage(LayPagination layPagination, IdpUserInfoSearch search) {
        search.setStatus("1");
        Wrapper<IdpUserInfo> queryWrapper = MybatisPlusUtils.getQueryWrapper(search);
        Page<IdpUserInfo> pageParamsOld = layPagination.generatePageParams(IdpUserInfo.class);
        Page<UserInfoVo> pageParams = new Page<>();
        IPage<IdpUserInfo> list = this.idpUserInfoService.page(pageParamsOld, queryWrapper);

        List<UserInfoVo> resList = new ArrayList<>();
        List<String> userNameList = new ArrayList<>();
        for (IdpUserInfo item :
                list.getRecords()) {
            userNameList.add(item.getUserName());
        }
        if(userNameList.size()>0){
            QueryWrapper<IdpUserRoleMap> roleQueryWrapper = new QueryWrapper<>();
            roleQueryWrapper.eq(IdpUserRoleMap.Columns.status, ProjectConst.RecordStatus.Active);
            roleQueryWrapper.in(IdpUserRoleMap.Columns.userName, userNameList);
            List<IdpUserRoleMap> roleList = idpUserRoleMapService.list(roleQueryWrapper);
            for (IdpUserInfo item :
                    list.getRecords()) {
                List<IdpUserRoleMap> filterList = roleList.stream().filter(a -> item.getUserName().equals(a.getUserName())).collect(Collectors.toList());
                if (filterList.size() > 0) {
                    UserInfoVo vo = new UserInfoVo();
                    BeanUtils.copyProperties(item, vo);
                    vo.setRoleName(filterList.get(0).getRoleName());
                    resList.add(vo);
                }
            }
        }
        pageParams.setTotal(pageParamsOld.getTotal());
        pageParams.setPages(pageParamsOld.getPages());
        pageParams.setSize(pageParamsOld.getSize());
        pageParams.setCurrent(pageParamsOld.getCurrent());
        pageParams.setOrders(pageParamsOld.getOrders());
        pageParams.setRecords(resList);
        return pageParams;
    }


    @Override
    public IdpUserInfo webUserLogin(String userName, String userPwd) {
        return this.userLogin(userName, userPwd, ConstUtils.STATUS_DELETE);
    }

    private IdpUserInfo userLogin(String userName, String userPwd, String userType) {
        QueryWrapper<IdpUserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(IdpUserInfo.Columns.status, ConstUtils.STATUS_ACTIVE);
        queryWrapper.eq(IdpUserInfo.Columns.userName, userName);
        queryWrapper.eq(IdpUserInfo.Columns.userPwd, CommonSecurityUtils.getPassword(userName, userPwd));
        queryWrapper.eq(IdpUserInfo.Columns.userType, userType);
        List<IdpUserInfo> userInfoList = this.idpUserInfoService.list(queryWrapper);
        if (userInfoList.size() > 0) {
            return userInfoList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<IdpAppResource> getPermissionResource(String userName) {
        List<IdpRoleResourceMap> resourceMapList = this.getRoleResourceMap(userName);
        if (null != resourceMapList && resourceMapList.size() > 0) {
            List<IdpAppResource> resourceList = new ArrayList<>();
            for (IdpRoleResourceMap resourceMap : resourceMapList) {
                IdpAppResource resource = AppResources.getResourceMap().get(Long.parseLong(resourceMap.getResourceCode()));
                if (null != resource && ConstUtils.STATUS_ACTIVE.equals(resource.getResourceEnabled())) {
                    resourceList.add(resource);
                }
            }
            // 加入菜单排序
            resourceList.sort(Comparator.comparing(IdpAppResource::getResourceSort));
            return resourceList;
        } else {
            return null;
        }
    }

    @Override
    public List<String> getPermissionCode(String userName) {
        List<IdpRoleResourceMap> resourceMapList = this.getRoleResourceMap(userName);
        if (null != resourceMapList && resourceMapList.size() > 0) {
            List<String> permissionList = new ArrayList<>();
            for (IdpRoleResourceMap resourceMap : resourceMapList) {
                permissionList.add(resourceMap.getResourcePermission());
            }
            return permissionList;
        } else {
            return null;
        }
    }

    private List<IdpRoleResourceMap> getRoleResourceMap(String userName) {
        QueryWrapper<IdpUserRoleMap> roleWrapper = new QueryWrapper<>();
        roleWrapper.eq(IdpUserRoleMap.Columns.status, ConstUtils.STATUS_ACTIVE);
        roleWrapper.eq(IdpUserRoleMap.Columns.userName, userName);
        List<IdpUserRoleMap> userRoleMapList = this.idpUserRoleMapService.list(roleWrapper);
        if (null != userRoleMapList && userRoleMapList.size() > 0) {
            List<String> roleList = new ArrayList<>();
            for (IdpUserRoleMap roleMap : userRoleMapList) {
                roleList.add(roleMap.getRoleCode());
            }
            QueryWrapper<IdpRoleResourceMap> permissionWrapper = new QueryWrapper<>();
            permissionWrapper.eq(IdpRoleResourceMap.Columns.status, ConstUtils.STATUS_ACTIVE);
            permissionWrapper.in(IdpRoleResourceMap.Columns.roleCode, roleList.toArray());
            List<IdpRoleResourceMap> resourceMapList = this.idpRoleResourceMapService.list(permissionWrapper);
            if (null != resourceMapList && resourceMapList.size() > 0) {
                return resourceMapList;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

}
