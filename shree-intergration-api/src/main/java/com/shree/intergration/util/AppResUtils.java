package com.shree.intergration.util;

import com.shree.intergration.base.WebMenu;
import com.shree.intergration.common.base.ClassScaner;
import com.shree.intergration.common.base.SpringContext;
import com.shree.intergration.common.security.util.HashUtils;
import com.shree.intergration.model.major.entity.IdpAppResource;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppResUtils {

    private static final String ROOT_PACKAGE = "com.smartit.isport.controller";

    public static List<IdpAppResource> startScan() {
        Environment env = SpringContext.getBean(Environment.class);
        String appName = env.getProperty("spring.application.name");
        List<IdpAppResource> resourceList = new ArrayList<>();
        List<Class<?>> classes = ClassScaner.scanPackage(ROOT_PACKAGE);
        for (Class<?> clazz : classes) {
            Controller controller = clazz.getAnnotation(Controller.class);
            if (null != controller) {
                RequestMapping baseMapping = clazz.getAnnotation(RequestMapping.class);
                String baseUrl = baseMapping == null ? "" : baseMapping.value()[0];
                for (Method method : clazz.getDeclaredMethods()) {
                    WebMenu webMenu = method.getAnnotation(WebMenu.class);
                    if (null != webMenu) {
                        GetMapping getMapping = method.getAnnotation(GetMapping.class);
                        if (null != getMapping) {
                            IdpAppResource resource = new IdpAppResource();
                            resource.setResourceName(webMenu.name());
                            resource.setResourceType(ConstUtils.STATUS_ACTIVE);
                            resource.setResourcePath(webMenu.path());
                            resource.setResourceUrl(baseUrl + getMapping.value()[0]);
                            RequiresPermissions permissions = method.getAnnotation(RequiresPermissions.class);
                            if (null != permissions) {
                                resource.setResourcePermission(permissions.value()[0]);
                            }
                            resource.setResourceCode(HashUtils.getMD5(appName + resource.getResourceUrl(), StandardCharsets.UTF_8, true));
                            resource.setCreateTime(LocalDateTime.now());
                            resource.setModifyTime(LocalDateTime.now());
                            resourceList.add(resource);
                        }
                    }
                }
            }
        }
        return resourceList;
    }
}
