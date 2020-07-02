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
import com.shree.intergration.model.major.entity.IdpDictInfo;
import com.shree.intergration.model.major.entity.IdpDictInfoSearch;
import com.shree.intergration.model.major.service.IdpDictInfoService;
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
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/system/dict", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//@RequiresPermissions("system:dict")
public class DictApiController {

    @Resource
    IdpDictInfoService idpDictInfoService;

    /**
     * 根据字典值数组查询字典信息
     *
     * @return
     */
    @RequestMapping(value = "/list/MultiTypes/query", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResult queryListByMultiTypes(String dictTypeCodeListString) {
        RestResult result = RestResult.createSuccessResult("查询成功！");
        HashMap<String, List<IdpDictInfo>> dictMap = new HashMap<>();
        try {
            if (!StringUtils.isNullOrEmpty(dictTypeCodeListString)) {
                String[] dictTypeCodeList = dictTypeCodeListString.split(",");
                for (String dictTypeCodeItem : dictTypeCodeList) {
                    IdpDictInfoSearch search = new IdpDictInfoSearch();
                    search.setStatus("1");
                    search.setDictEnabled("1");
                    search.setDictTypeCode(dictTypeCodeItem);
                    QueryWrapper<IdpDictInfo> queryWrapper = MybatisPlusUtils.getQueryWrapper(search);
                    queryWrapper.orderByAsc(IdpDictInfo.Columns.sort);
                    List<IdpDictInfo> list = this.idpDictInfoService.list(queryWrapper);
                    dictMap.put(dictTypeCodeItem, list);
                }
            }
            result.setData(dictMap);
        } catch (Exception ex) {
            result.setErrorResult("查询错误！", null, ex.getMessage());
        }
        return result;
    }

    /**
     * 获取字典信息列表
     *
     * @return
     */
    @RequestMapping(value = "/list/query", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResult queryList(IdpDictInfoSearch search) {
        RestResult result = RestResult.createSuccessResult("查询成功！");
        search.setStatus("1");
        search.setDictEnabled("1");
        try {
            Wrapper<IdpDictInfo> queryWrapper = MybatisPlusUtils.getQueryWrapper(search);
            List<IdpDictInfo> list = this.idpDictInfoService.list(queryWrapper);
            result.setData(list);
        } catch (Exception ex) {
            result.setErrorResult("查询错误！", null, ex.getMessage());
        }
        return result;
    }

    /**
     * 获取字典信息列表(分页)
     *
     * @return
     */
    @RequestMapping(value = "/list/page", method = {RequestMethod.GET, RequestMethod.POST})
    public LayPageResult queryListPage(IdpDictInfoSearch search, HttpServletRequest request, HttpServletResponse response) {
        LayPagination layPagination = new LayPagination(request);
        search.setStatus("1");
        Wrapper<IdpDictInfo> queryWrapper = MybatisPlusUtils.getQueryWrapper(search);
        Page<IdpDictInfo> pageParams = layPagination.generatePageParams(IdpDictInfo.class);
        IPage<IdpDictInfo> list = this.idpDictInfoService.page(pageParams, queryWrapper);
        LayPageResult layPageResult = layPagination.getLayPageResult(list);
        return layPageResult;
    }

    /**
     * 获取字典信息
     *
     * @return
     */
    @RequestMapping(value = "/info/load", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResult doLoadInfo(String id) {
        RestResult result = RestResult.createSuccessResult("查询成功！");
        if (!StringUtils.isNullOrEmpty(id)) {
            try {
                IdpDictInfo record = this.idpDictInfoService.getById(id);
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
     * 保存字典信息
     *
     * @return
     */
    @RequestMapping(value = "/info/save", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResult doSaveInfo(IdpDictInfo record) {
        RestResult restResult = RestResult.createSuccessResult("保存成功！");
        try {
            LocalDateTime date = LocalDateTime.now();
            if (null == record.getId()) {
                record.setId(LiteSnowflakeIdGenerator.nextId());
                record.setCreateTime(LocalDateTime.now());
            }
            record.setModifyTime(date);
            this.idpDictInfoService.saveOrUpdate(record);
        } catch (Exception ex) {
            restResult.setErrorResult("保存失败!", null, ex.getMessage());
        }
        return restResult;
    }

    /**
     * 删除字典信息
     *
     * @param id id
     * @return
     */
    @RequestMapping(value = "/info/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResult doDeleteInfo(String id) {
        RestResult result = RestResult.createSuccessResult("删除成功！");
        if (!StringUtils.isNullOrEmpty(id)) {
            try {
                IdpDictInfo info = new IdpDictInfo();
                info.setId(Long.parseLong(id));
                info.setStatus(ConstUtils.STATUS_DELETE);
                if (!this.idpDictInfoService.updateById(info)) {
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
