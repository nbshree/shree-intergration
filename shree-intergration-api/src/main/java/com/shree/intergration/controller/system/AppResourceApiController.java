package com.shree.intergration.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mysql.cj.util.StringUtils;
import com.shree.intergration.common.base.id.generator.LiteSnowflakeIdGenerator;
import com.shree.intergration.common.web.api.ApiStatus;
import com.shree.intergration.common.web.api.ApiStatusResult;
import com.shree.intergration.common.web.rest.RestResult;
import com.shree.intergration.model.custom.entity.AppResource;
import com.shree.intergration.model.custom.mapper.AppResourceMapper;
import com.shree.intergration.model.major.entity.IdpAppResource;
import com.shree.intergration.model.major.service.IdpAppResourceService;
import com.shree.intergration.util.AppResUtils;
import com.shree.intergration.util.AppResources;
import com.shree.intergration.util.ConstUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@Log4j2
@RestController
@RequestMapping(value = "/system/resource", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//@RequiresPermissions("system:menu")
public class AppResourceApiController {

    @Resource
    IdpAppResourceService idpAppResourceService;

    @Resource
    AppResourceMapper appResourceMapper;

    /**
     * 获取系统资源列表
     *
     * @return
     */
    @RequestMapping(value = "/list/query", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResult queryList(String resourceName, String resourcePath, String resourceUrl, String resourceType) {
        RestResult result = RestResult.createSuccessResult("查询成功！");
        try {
            List<AppResource> list = appResourceMapper.listQuery(resourceName, resourcePath, resourceUrl, resourceType);
            result.setData(list);
        } catch (Exception ex) {
            result.setErrorResult("查询错误！", null, ex.getMessage());
        }
        return result;
    }

    /**
     * 保存系统资源
     *
     * @return
     */
    @RequestMapping(value = "/info/save", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResult doSaveInfo(IdpAppResource record) {
        RestResult restResult = RestResult.createSuccessResult("保存成功！");
        try {
            LocalDateTime date = LocalDateTime.now();
            if (null == record.getId()) {
                record.setId(LiteSnowflakeIdGenerator.nextId());
                record.setCreateTime(LocalDateTime.now());
            }
            record.setModifyTime(date);
            this.idpAppResourceService.saveOrUpdate(record);
            // 保存后异步刷新服务器菜单缓存
            Executors.newCachedThreadPool().submit(() -> AppResources.getInstance().refreshAppResources());
        } catch (Exception ex) {
            restResult.setErrorResult("保存失败!", null, ex.getMessage());
        }
        return restResult;
    }

    /**
     * 删除系统资源
     *
     * @return
     */
    @RequestMapping(value = "/info/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResult doDeleteInfo(String id) {
        RestResult result = RestResult.createSuccessResult("删除成功！");
        if (!StringUtils.isNullOrEmpty(id)) {
            try {
                IdpAppResource info = new IdpAppResource();
                info.setId(Long.parseLong(id));
                info.setStatus(ConstUtils.STATUS_DELETE);
                if (!this.idpAppResourceService.updateById(info)) {
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
     * 自动更新生成系统资源
     *
     * @return
     */
    @RequestMapping(value = "/info/generate", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResult doScanAndGenerateResource() {
        try {
            List<IdpAppResource> list = AppResUtils.startScan();
            List<IdpAppResource> saveList = new ArrayList<>();
            for (IdpAppResource item : list) {
                QueryWrapper<IdpAppResource> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq(IdpAppResource.Columns.status,"1");
                queryWrapper.eq(IdpAppResource.Columns.resourceCode,item.getResourceCode());
                List<IdpAppResource> IdpAppResourceList = this.idpAppResourceService.list(queryWrapper);
                if (IdpAppResourceList.size() > 0) {
                    item.setId(IdpAppResourceList.get(0).getId());
                }
                saveList.add(item);
            }
            if (this.idpAppResourceService.saveOrUpdateBatch(saveList)) {
                return RestResult.createSuccessResult(ApiStatus.OK.getCode(), null, "菜单资源更新成功！");
            } else {
                return RestResult.createFailedResult(ApiStatus.FAILED.getCode(), null, "菜单资源更新失败！");
            }
        } catch (Exception ex) {
            log.error("资源生成接口错误！", ex);
            return ApiStatusResult.ERROR;
        }
    }
}
