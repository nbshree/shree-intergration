package com.shree.intergration.model.major.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * APP资源表(菜单、链接)
 * </p>
 *
 * @author shree
 * @since 2020-07-01
 */
public class IdpAppResource extends Model<IdpAppResource> {

    /**
    * 所有字段名
    */
    public static class Columns {
        /**
        * id@(SnowflakeId)
        */
        public static final String id = "id";
        /**
        * 记录状态@(0:删除,1:正常)
        */
        public static final String status = "status";
        /**
        * 创建时间
        */
        public static final String createTime = "create_time";
        /**
        * 创建者ID
        */
        public static final String creatorId = "creator_id";
        /**
        * 创建者名称@(用户名)
        */
        public static final String creatorName = "creator_name";
        /**
        * 修改时间
        */
        public static final String modifyTime = "modify_time";
        /**
        * 修改者ID
        */
        public static final String modifierId = "modifier_id";
        /**
        * 修改者名称@(用户名)
        */
        public static final String modifierName = "modifier_name";
        /**
        * 所属企业ID@(同时用于数据权限过滤)
        */
        public static final String entId = "ent_id";
        /**
        * 所属企业名称
        */
        public static final String entName = "ent_name";
        /**
        * 父级资源ID
        */
        public static final String parentId = "parent_id";
        /**
        * 资源代码@(固定值Hash不会变动)
        */
        public static final String resourceCode = "resource_code";
        /**
        * 资源名称
        */
        public static final String resourceName = "resource_name";
        /**
        * 资源类型@(0:目录,1:菜单/页面,2:功能,-1:隐藏)
        */
        public static final String resourceType = "resource_type";
        /**
        * 资源模式@(0:主界面,1:弹出窗)
        */
        public static final String resourceMode = "resource_mode";
        /**
        * 资源路径@(中文显示)
        */
        public static final String resourcePath = "resource_path";
        /**
        * 资源访问URL
        */
        public static final String resourceUrl = "resource_url";
        /**
        * 资源图标
        */
        public static final String resourceIcon = "resource_icon";
        /**
        * 资源排序
        */
        public static final String resourceSort = "resource_sort";
        /**
        * 资源启用状态(0:不启用,1:启用)
        */
        public static final String resourceEnabled = "resource_enabled";
        /**
        * 资源权限
        */
        public static final String resourcePermission = "resource_permission";
    }

    /**
     * id@(SnowflakeId)
     */
    private Long id;

    /**
     * 记录状态@(0:删除,1:正常)
     */
    private String status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 创建者ID
     */
    @TableField("creator_id")
    private Long creatorId;

    /**
     * 创建者名称@(用户名)
     */
    @TableField("creator_name")
    private String creatorName;

    /**
     * 修改时间
     */
    @TableField("modify_time")
    private LocalDateTime modifyTime;

    /**
     * 修改者ID
     */
    @TableField("modifier_id")
    private Long modifierId;

    /**
     * 修改者名称@(用户名)
     */
    @TableField("modifier_name")
    private String modifierName;

    /**
     * 所属企业ID@(同时用于数据权限过滤)
     */
    @TableField("ent_id")
    private Long entId;

    /**
     * 所属企业名称
     */
    @TableField("ent_name")
    private String entName;

    /**
     * 父级资源ID
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 资源代码@(固定值Hash不会变动)
     */
    @TableField("resource_code")
    private String resourceCode;

    /**
     * 资源名称
     */
    @TableField("resource_name")
    private String resourceName;

    /**
     * 资源类型@(0:目录,1:菜单/页面,2:功能,-1:隐藏)
     */
    @TableField("resource_type")
    private String resourceType;

    /**
     * 资源模式@(0:主界面,1:弹出窗)
     */
    @TableField("resource_mode")
    private String resourceMode;

    /**
     * 资源路径@(中文显示)
     */
    @TableField("resource_path")
    private String resourcePath;

    /**
     * 资源访问URL
     */
    @TableField("resource_url")
    private String resourceUrl;

    /**
     * 资源图标
     */
    @TableField("resource_icon")
    private String resourceIcon;

    /**
     * 资源排序
     */
    @TableField("resource_sort")
    private String resourceSort;

    /**
     * 资源启用状态(0:不启用,1:启用)
     */
    @TableField("resource_enabled")
    private String resourceEnabled;

    /**
     * 资源权限
     */
    @TableField("resource_permission")
    private String resourcePermission;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getModifierId() {
        return modifierId;
    }

    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    public String getModifierName() {
        return modifierName;
    }

    public void setModifierName(String modifierName) {
        this.modifierName = modifierName;
    }

    public Long getEntId() {
        return entId;
    }

    public void setEntId(Long entId) {
        this.entId = entId;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceMode() {
        return resourceMode;
    }

    public void setResourceMode(String resourceMode) {
        this.resourceMode = resourceMode;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getResourceIcon() {
        return resourceIcon;
    }

    public void setResourceIcon(String resourceIcon) {
        this.resourceIcon = resourceIcon;
    }

    public String getResourceSort() {
        return resourceSort;
    }

    public void setResourceSort(String resourceSort) {
        this.resourceSort = resourceSort;
    }

    public String getResourceEnabled() {
        return resourceEnabled;
    }

    public void setResourceEnabled(String resourceEnabled) {
        this.resourceEnabled = resourceEnabled;
    }

    public String getResourcePermission() {
        return resourcePermission;
    }

    public void setResourcePermission(String resourcePermission) {
        this.resourcePermission = resourcePermission;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "IdpAppResource{" +
        "id=" + id +
        ", status=" + status +
        ", createTime=" + createTime +
        ", creatorId=" + creatorId +
        ", creatorName=" + creatorName +
        ", modifyTime=" + modifyTime +
        ", modifierId=" + modifierId +
        ", modifierName=" + modifierName +
        ", entId=" + entId +
        ", entName=" + entName +
        ", parentId=" + parentId +
        ", resourceCode=" + resourceCode +
        ", resourceName=" + resourceName +
        ", resourceType=" + resourceType +
        ", resourceMode=" + resourceMode +
        ", resourcePath=" + resourcePath +
        ", resourceUrl=" + resourceUrl +
        ", resourceIcon=" + resourceIcon +
        ", resourceSort=" + resourceSort +
        ", resourceEnabled=" + resourceEnabled +
        ", resourcePermission=" + resourcePermission +
        "}";
    }
}
