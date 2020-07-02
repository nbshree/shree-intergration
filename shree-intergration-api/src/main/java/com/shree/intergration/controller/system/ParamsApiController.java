package com.shree.intergration.controller.system;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.util.StringUtils;
import com.shree.intergration.common.base.id.generator.LiteSnowflakeIdGenerator;
import com.shree.intergration.common.util.MybatisPlusUtils;
import com.shree.intergration.common.web.rest.RestResult;
import com.shree.intergration.common.web.rest.layui.LayPageResult;
import com.shree.intergration.common.web.rest.layui.LayPagination;
import com.shree.intergration.model.major.entity.IdpParamsInfo;
import com.shree.intergration.model.major.entity.IdpParamsInfoSearch;
import com.shree.intergration.model.major.service.IdpParamsInfoService;
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

@RestController
@RequestMapping(value = "/system/params", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//@RequiresPermissions("system:params")
public class ParamsApiController {

    @Resource
    IdpParamsInfoService idpParamsInfoService;

    /**
     * 获取参数信息列表（分页）
     *
     * @return
     */
    @RequestMapping(value = "/list/page", method = {RequestMethod.GET, RequestMethod.POST})
    public LayPageResult queryListPage(IdpParamsInfoSearch search, HttpServletRequest request, HttpServletResponse response) {
        LayPagination layPagination = new LayPagination(request);
        search.setStatus("1");
        Wrapper<IdpParamsInfo> queryWrapper = MybatisPlusUtils.getQueryWrapper(search);
        Page<IdpParamsInfo> pageParams = layPagination.generatePageParams(IdpParamsInfo.class);
        IPage<IdpParamsInfo> list = this.idpParamsInfoService.page(pageParams, queryWrapper);
        LayPageResult layPageResult = layPagination.getLayPageResult(list);
        return layPageResult;
    }

    /**
     * 获取参数信息
     *
     * @return
     */
    @RequestMapping(value = "/info/load", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResult doLoadInfo(String id) {
        RestResult result = RestResult.createSuccessResult("查询成功！");
        if (!StringUtils.isNullOrEmpty(id)) {
            try {
                IdpParamsInfo record = this.idpParamsInfoService.getById(id);
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
     * 保存参数信息
     *
     * @return
     */
    @RequestMapping(value = "/info/save", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResult doSaveInfo(IdpParamsInfo record) {
        RestResult restResult = RestResult.createSuccessResult("保存成功！");
        try {
            LocalDateTime date = LocalDateTime.now();
            if (null==record.getId()) {
                record.setId(LiteSnowflakeIdGenerator.nextId());
                record.setCreateTime(LocalDateTime.now());
            }
            record.setModifyTime(date);
            this.idpParamsInfoService.saveOrUpdate(record);
        } catch (Exception ex) {
            restResult.setErrorResult("保存失败!", null, ex.getMessage());
        }
        return restResult;
    }

    /**
     * 删除参数信息
     *
     * @param id id
     * @return
     */
    @RequestMapping(value = "/info/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResult doDeleteInfo(String id) {
        RestResult result = RestResult.createSuccessResult("删除成功！");
        if (!StringUtils.isNullOrEmpty(id)) {
            try {
                IdpParamsInfo info = new IdpParamsInfo();
                info.setId(Long.parseLong(id));
                info.setStatus(ConstUtils.STATUS_DELETE);
                if (!this.idpParamsInfoService.updateById(info)) {
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
}
