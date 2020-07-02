package com.shree.intergration.controller.system;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.util.StringUtils;
import com.shree.intergration.common.base.id.generator.LiteSnowflakeIdGenerator;
import com.shree.intergration.common.security.util.CommonSecurityUtils;
import com.shree.intergration.common.util.MybatisPlusUtils;
import com.shree.intergration.common.web.rest.RestResult;
import com.shree.intergration.common.web.rest.layui.LayPageResult;
import com.shree.intergration.common.web.rest.layui.LayPagination;
import com.shree.intergration.model.custom.entity.UserInfoVo;
import com.shree.intergration.model.custom.serivce.UserService;
import com.shree.intergration.model.major.entity.IdpUserInfo;
import com.shree.intergration.model.major.entity.IdpUserInfoSearch;
import com.shree.intergration.model.major.entity.IdpUserRoleMap;
import com.shree.intergration.model.major.entity.IdpUserRoleMapSearch;
import com.shree.intergration.model.major.service.IdpUserInfoService;
import com.shree.intergration.model.major.service.IdpUserRoleMapService;
import com.shree.intergration.util.ConstUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/system/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequiresAuthentication
//@RequiresPermissions("system:user")
public class UserApiController {

    @Resource
    UserService userService;
    @Resource
    IdpUserInfoService idpUserInfoService;
    @Resource
    IdpUserRoleMapService idpUserRoleMapService;

    /**
     * 获取用户信息列表（分页）
     *
     * @return
     */
    @RequestMapping(value = "/list/page", method = {RequestMethod.GET, RequestMethod.POST})
    public LayPageResult queryListPage(IdpUserInfoSearch search, HttpServletRequest request, HttpServletResponse response) {
        LayPageResult layPageResult = new LayPageResult();
        try {
            LayPagination layPagination = new LayPagination(request);
            Page<UserInfoVo> pageParams = this.userService.getPage(layPagination, search);
            layPageResult = layPagination.getLayPageResult(pageParams);
        } catch (Exception ex) {
            layPageResult.setErrorResult("查询错误！", null, ex.getMessage());
        }
        return layPageResult;
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @RequestMapping(value = "/info/load", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResult doLoadInfo(String id) {
        RestResult result = RestResult.createSuccessResult("查询成功！");
        if (!StringUtils.isNullOrEmpty(id)) {
            try {
                IdpUserInfo record = this.idpUserInfoService.getById(id);
                result.setData(record);
            } catch (Exception ex) {
                result.setErrorResult("查询错误！", null, ex.getMessage());
            }
        } else {
            result.setFailedResult("查询失败，ID不能为空！");
        }
        return result;
    }

    /**
     * 保存用户信息
     *
     * @return
     */
    @RequestMapping(value = "/info/save", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResult doSaveInfo(IdpUserInfo record) {
        RestResult restResult = RestResult.createSuccessResult("保存成功！");
        try {
            LocalDateTime date = LocalDateTime.now();
            if (null == record.getId()) {
                record.setId(LiteSnowflakeIdGenerator.nextId());
                record.setCreateTime(date);
            }
            if (!StringUtils.isNullOrEmpty(record.getUserName()) && !StringUtils.isNullOrEmpty(record.getUserPwd())) {
                record.setUserPwd(CommonSecurityUtils.getPassword(record.getUserName(), record.getUserPwd()));
            }
            record.setModifyTime(date);
            this.idpUserInfoService.saveOrUpdate(record);
        } catch (Exception ex) {
            restResult.setErrorResult("保存失败!", null, ex.getMessage());
        }
        return restResult;
    }

    /**
     * 删除用户信息
     *
     * @param id id
     * @return
     */
    @RequestMapping(value = "/info/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResult doDeleteInfo(String id) {
        RestResult result = RestResult.createSuccessResult("删除成功！");
        if (!StringUtils.isNullOrEmpty(id)) {
            try {
                IdpUserInfo info = new IdpUserInfo();
                info.setId(Long.parseLong(id));
                info.setStatus(ConstUtils.STATUS_DELETE);
                if (!this.idpUserInfoService.updateById(info)) {
                    result.setFailedResult("删除失败！");
                }
            } catch (Exception ex) {
                result.setErrorResult("删除错误！", null, ex.getMessage());
            }
        } else {
            result.setFailedResult("删除失败，ID不能为空！");
        }

        return result;
    }

    /**
     * 查询用户和角色关联信息
     *
     * @return
     */
    @RequestMapping(value = "/role/list/query", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResult queryRoleList(IdpUserRoleMapSearch search) {
        RestResult result = RestResult.createSuccessResult("查询成功！");
        search.setStatus("1");
        try {
            QueryWrapper<IdpUserRoleMap> queryWrapper = MybatisPlusUtils.getQueryWrapper(search);
            List<IdpUserRoleMap> list = this.idpUserRoleMapService.list(queryWrapper);
            result.setData(list);
        } catch (Exception ex) {
            result.setErrorResult("查询错误！", null, ex.getMessage());
        }
        return result;
    }

    /**
     * 保存用户和角色关联信息
     *
     * @return
     */
    @RequestMapping(value = "/role/list/save", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResult doSaveRoleInfo(String roleCode, String roleName, String userName) {
        RestResult result = RestResult.createSuccessResult("查询成功！");
        try {
            IdpUserRoleMapSearch search = new IdpUserRoleMapSearch();
            search.setStatus("1");
            search.setUserName(userName);
            QueryWrapper<IdpUserRoleMap> queryWrapper = MybatisPlusUtils.getQueryWrapper(search);
            this.idpUserRoleMapService.remove(queryWrapper);
            IdpUserRoleMap record = new IdpUserRoleMap();
            record.setStatus("1");
            record.setId(LiteSnowflakeIdGenerator.nextId());
            record.setUserName(userName);
            record.setRoleCode(roleCode);
            record.setRoleName(roleName);
            record.setCreateTime(LocalDateTime.now());
            record.setModifyTime(LocalDateTime.now());
            this.idpUserRoleMapService.save(record);
        } catch (Exception ex) {
            result.setErrorResult("保存错误！", null, ex.getMessage());
        }
        return result;
    }

    /**
     * 检查用户是否已存在
     *
     * @return
     */
    @RequestMapping(value = "/list/verification/query", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResult queryVerification(String userName, String id) {
        RestResult result = RestResult.createSuccessResult("查询成功！");
        try {
            IdpUserInfoSearch search = new IdpUserInfoSearch();
            search.setStatus("1");
            if (!StringUtils.isNullOrEmpty(userName)) {
                search.setUserName(userName);
            }
            QueryWrapper<IdpUserInfo> queryWrapper = MybatisPlusUtils.getQueryWrapper(search);
            if (!StringUtils.isNullOrEmpty(id)) {
                queryWrapper.ne("ID", id);
            }
            List<IdpUserInfo> list = this.idpUserInfoService.list(queryWrapper);
            result.setData(list.size());
        } catch (Exception ex) {
            result.setErrorResult("查询错误！", null, ex.getMessage());
        }
        return result;
    }
}
