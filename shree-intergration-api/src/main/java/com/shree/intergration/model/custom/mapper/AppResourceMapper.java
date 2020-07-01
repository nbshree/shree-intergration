package com.shree.intergration.model.custom.mapper;

import com.shree.intergration.model.custom.entity.AppResource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AppResourceMapper {

    @Select(" <script>" +
            " SELECT baseResource.*,parentResource.resource_name parentName" +
            " FROM idp_app_resource baseResource LEFT JOIN idp_app_resource parentResource ON baseResource.parent_id = parentResource.ID" +
            " WHERE baseResource.status = 1 " +
            " <when test='resourceName != null and resourceName != &quot;&quot;'>" +
            "   AND baseResource.resource_name LIKE #{resourceName}" +
            " </when>" +
            " <when test='resourcePath != null and resourcePath != &quot;&quot;'>" +
            "   AND baseResource.resource_path LIKE #{resourcePath}" +
            " </when>" +
            " <when test='resourceUrl != null and resourceUrl != &quot;&quot;'>" +
            "   AND baseResource.resource_url LIKE #{resourceUrl}" +
            " </when>" +
            " <when test='resourceType != null and resourceType != &quot;&quot;'>" +
            "   AND baseResource.resource_type LIKE #{resourceType}" +
            " </when>" +
            "ORDER BY baseResource.resource_sort ASC" +
            " </script>")
    List<AppResource> listQuery(@Param("resourceName") String resourceName, @Param("resourcePath") String resourcePath, @Param("resourceUrl") String resourceUrl, @Param("resourceType") String resourceType);
}
