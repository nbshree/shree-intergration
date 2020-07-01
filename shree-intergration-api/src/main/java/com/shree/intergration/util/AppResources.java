package com.shree.intergration.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shree.intergration.common.base.SpringContext;
import com.shree.intergration.common.base.singleton.AbstractSingleton;
import com.shree.intergration.model.major.entity.IdpAppResource;
import com.shree.intergration.model.major.service.IdpAppResourceService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class AppResources {

    @Getter
    @Setter
    private ConcurrentHashMap<Long, IdpAppResource> appResourcesMap;

    public static final AbstractSingleton<AppResources> HOLDER = new AbstractSingleton<AppResources>() {
        protected AppResources newInstance() {
            return new AppResources();
        }
    };

    public static ConcurrentHashMap<Long, IdpAppResource> getResourceMap() {
        return HOLDER.getInstance().appResourcesMap;
    }

    public static AppResources getInstance() {
        return HOLDER.getInstance();
    }

    private AppResources() {
        this.refreshAppResources();
    }

    public int refreshAppResources() {
        IdpAppResourceService appResourceService = SpringContext.getBean(IdpAppResourceService.class);
        QueryWrapper<IdpAppResource> wrapper = new QueryWrapper<>();
        wrapper.eq(IdpAppResource.Columns.status, ConstUtils.STATUS_ACTIVE);
        wrapper.in(IdpAppResource.Columns.resourceType, ConstUtils.STATUS_DELETE, ConstUtils.STATUS_ACTIVE);
        List<IdpAppResource> list = appResourceService.list(wrapper);
        this.appResourcesMap = new ConcurrentHashMap<>();
        for (IdpAppResource resource : list) {
            this.appResourcesMap.put(resource.getId(), resource);
        }
        return this.appResourcesMap.size();
    }
}
