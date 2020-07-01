package com.shree.intergration.controller.system;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.util.StringUtils;
import com.shree.intergration.common.base.id.generator.LiteSnowflakeIdGenerator;
import com.shree.intergration.common.util.MybatisPlusUtils;
import com.shree.intergration.common.web.rest.RestResult;
import com.shree.intergration.common.web.rest.layui.LayPageResult;
import com.shree.intergration.common.web.rest.layui.LayPagination;
import com.shree.intergration.model.major.entity.*;
import com.shree.intergration.model.major.service.IdpRoleInfoService;
import com.shree.intergration.model.major.service.IdpRoleResourceMapService;
import com.shree.intergration.util.AppResources;
import com.shree.intergration.util.ConstUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/system/role", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequiresPermissions("system:role")
public class RoleApiController {

    @Resource
    IdpRoleInfoService idpRoleInfoService;

    @Resource
    IdpRoleResourceMapService idpRoleResourceMapService;

    /**
     * 获取角色信息列表
     *
     * @return
     */
    @RequestMapping(value = "/list/query", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResult queryList(IdpRoleInfoSearch search) {
        RestResult result = RestResult.createSuccessResult("查询成功！");
        search.setStatus("1");
        try {
            QueryWrapper<IdpRoleInfo> queryWrapper = MybatisPlusUtils.getQueryWrapper(search);
            List<IdpRoleInfo> list = this.idpRoleInfoService.list(queryWrapper);
            result.setData(list);
        } catch (Exception ex) {
            result.setErrorResult("查询错误！", null, ex.getMessage());
        }
        return result;
    }

    /**
     * 获取角色信息列表（分页）
     *
     * @return
     */
    @RequestMapping(value = "/list/page", method = {RequestMethod.GET, RequestMethod.POST})
    public LayPageResult queryListPage(IdpRoleInfoSearch search, HttpServletRequest request, HttpServletResponse response) {
        LayPagination layPagination = new LayPagination(request);
        search.setStatus("1");
        Wrapper<IdpRoleInfo> queryWrapper = MybatisPlusUtils.getQueryWrapper(search);
        Page<IdpRoleInfo> pageParams = layPagination.generatePageParams(IdpRoleInfo.class);
        IPage<IdpRoleInfo> list = this.idpRoleInfoService.page(pageParams, queryWrapper);
        LayPageResult layPageResult = layPagination.getLayPageResult(list);
        return layPageResult;
    }

    /**
     * 保存角色信息
     *
     * @return
     */
    @RequestMapping(value = "/info/save", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResult doSaveInfo(IdpRoleInfo record) {
        RestResult restResult = RestResult.createSuccessResult("保存成功！");
        try {
            LocalDateTime date = LocalDateTime.now();
            if (null==record.getId()) {
                record.setId(LiteSnowflakeIdGenerator.nextId());
                record.setCreateTime(LocalDateTime.now());
            }
            record.setModifyTime(date);
            this.idpRoleInfoService.saveOrUpdate(record);
        } catch (Exception ex) {
            restResult.setErrorResult("保存失败!", null, ex.getMessage());
        }
        return restResult;
    }

    /**
     * 删除角色信息
     *
     * @return
     */
    @RequestMapping(value = "/info/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResult doDeleteInfo(String id) {
        RestResult result = RestResult.createSuccessResult("删除成功！");
        if (!StringUtils.isNullOrEmpty(id)) {
            try {
                IdpRoleInfo info = new IdpRoleInfo();
                info.setId(Long.parseLong(id));
                info.setStatus(ConstUtils.STATUS_DELETE);
                if (!this.idpRoleInfoService.updateById(info)) {
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
     * 获取角色与系统资源关联信息列表
     *
     * @return
     */
    @RequestMapping(value = "/resource/list/query", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResult queryResourceList(IdpRoleResourceMapSearch search) {
        RestResult result = RestResult.createSuccessResult("查询成功！");
        search.setStatus("1");
        try {
            QueryWrapper<IdpRoleResourceMap> queryWrapper = MybatisPlusUtils.getQueryWrapper(search);
            List<IdpRoleResourceMap> list = this.idpRoleResourceMapService.list(queryWrapper);
            result.setData(list);
        } catch (Exception ex) {
            result.setErrorResult("查询错误！", null, ex.getMessage());
        }
        return result;
    }

    /**
     * 批量保存角色与系统资源关联信息
     *
     * @return
     */
    @RequestMapping(value = "/resource/info/bacth/save", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResult doSaveResourceInfo(String roleCode, String resourceIds) {
        RestResult restResult = RestResult.createSuccessResult("保存成功！");
        try {
            IdpRoleResourceMapSearch search = new IdpRoleResourceMapSearch();
            search.setStatus("1");
            search.setRoleCode(roleCode);
            QueryWrapper<IdpRoleResourceMap> queryWrapper = MybatisPlusUtils.getQueryWrapper(search);
            this.idpRoleResourceMapService.remove(queryWrapper);
            if (!StringUtils.isNullOrEmpty(resourceIds)) {
                String[] idArr = resourceIds.split(",");
                List<IdpRoleResourceMap> list = new ArrayList<>();
                for (String resourceId : idArr) {
                    IdpAppResource resource = AppResources.getResourceMap().get(Long.parseLong(resourceId));
                    System.out.println(AppResources.getResourceMap());
                    if (null != resource) {
                        IdpRoleResourceMap record = new IdpRoleResourceMap();
                        record.setRoleCode(roleCode);
                        record.setResourceCode(resourceId);
                        if (null != resource.getResourcePermission()) {
                            record.setResourcePermission(resource.getResourcePermission());
                        } else {
                            record.setResourcePermission(ConstUtils.DEFAULT_PERMISSION);
                        }
                        record.setStatus(ConstUtils.STATUS_ACTIVE);
                        record.setId(LiteSnowflakeIdGenerator.nextId());
                        record.setCreateTime(LocalDateTime.now());
                        record.setModifyTime(LocalDateTime.now());
                        list.add(record);
                    }
                }
                this.idpRoleResourceMapService.saveBatch(list);
            }
        } catch (Exception ex) {
            restResult.setErrorResult("保存失败!", null, ex.getMessage());
        }
        return restResult;
    }

    /**
     * 检查角色是否已存在
     *
     * @return
     */
    @RequestMapping(value = "/list/verification/query", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResult queryVerification(String roleCode, String id) {
        RestResult result = RestResult.createSuccessResult("查询成功！");
        try {
            IdpRoleInfoSearch search = new IdpRoleInfoSearch();
            search.setStatus("1");
            if (!StringUtils.isNullOrEmpty(roleCode)) {
                search.setRoleCode(roleCode);
            }
            QueryWrapper<IdpRoleInfo> queryWrapper = MybatisPlusUtils.getQueryWrapper(search);
            if (!StringUtils.isNullOrEmpty(id)) {
                queryWrapper.ne("ID", id);
            }
            List<IdpRoleInfo> list = this.idpRoleInfoService.list(queryWrapper);
            result.setData(list.size());
        } catch (Exception ex) {
            result.setErrorResult("查询错误！", null, ex.getMessage());
        }
        return result;
    }
}
