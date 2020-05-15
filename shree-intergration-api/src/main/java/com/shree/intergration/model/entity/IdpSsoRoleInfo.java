package com.shree.intergration.model.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 统一平台角色结构信息
 * </p>
 *
 * @author sry123
 * @since 2020-05-15
 */
@TableName("idp_sso_role_info")
public class IdpSsoRoleInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID@(SnowflakeId)
     */
    @TableId("ID")
    private Long id;
    /**
     * 记录状态@(0:删除,1:正常)
     */
    @TableField("STATUS")
    private String status;
    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;
    /**
     * 创建者ID
     */
    @TableField("CREATOR_ID")
    private Long creatorId;
    /**
     * 创建者名称@(用户名)
     */
    @TableField("CREATOR_NAME")
    private String creatorName;
    /**
     * 修改时间
     */
    @TableField("MODIFY_TIME")
    private Date modifyTime;
    /**
     * 修改者ID
     */
    @TableField("MODIFIER_ID")
    private Long modifierId;
    /**
     * 修改者名称@(用户名)
     */
    @TableField("MODIFIER_NAME")
    private String modifierName;
    /**
     * 父级角色ID
     */
    @TableField("PARENT_ID")
    private String parentId;
    /**
     * 角色名称（中文）
     */
    @TableField("ROLE_NAME")
    private String roleName;
    /**
     * 角色代码
     */
    @TableField("ROLE_CODE")
    private String roleCode;
    /**
     * 角色类型@(0:企业默认角色,1:自定义角色)
     */
    @TableField("ROLE_TYPE")
    private String roleType;
    /**
     * 角色标志@(0:菜单角色,1:数据角色)
     */
    @TableField("ROLE_FLAG")
    private String roleFlag;
    /**
     * 是否公开@(0:否,1:是)
     */
    @TableField("ROLE_PUBLIC")
    private String rolePublic;
    /**
     * 备注
     */
    @TableField("ROLE_REMARK")
    private String roleRemark;
    /**
     * 排序
     */
    @TableField("ROLE_SORT")
    private String roleSort;
    /**
     * 所属组织ID
     */
    @TableField("ORG_ID")
    private String orgId;
    /**
     * 所属组织名称
     */
    @TableField("ORG_NAME")
    private String orgName;
    /**
     * 所属组织名称代码
     */
    @TableField("ORG_CODE")
    private String orgCode;
    /**
     * 角色所属的APP KEY
     */
    @TableField("APP_KEY")
    private String appKey;
    /**
     * 角色所属的APP NAME
     */
    @TableField("APP_NAME")
    private String appName;


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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
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

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleFlag() {
        return roleFlag;
    }

    public void setRoleFlag(String roleFlag) {
        this.roleFlag = roleFlag;
    }

    public String getRolePublic() {
        return rolePublic;
    }

    public void setRolePublic(String rolePublic) {
        this.rolePublic = rolePublic;
    }

    public String getRoleRemark() {
        return roleRemark;
    }

    public void setRoleRemark(String roleRemark) {
        this.roleRemark = roleRemark;
    }

    public String getRoleSort() {
        return roleSort;
    }

    public void setRoleSort(String roleSort) {
        this.roleSort = roleSort;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    @Override
    public String toString() {
        return "IdpSsoRoleInfo{" +
        "id=" + id +
        ", status=" + status +
        ", createTime=" + createTime +
        ", creatorId=" + creatorId +
        ", creatorName=" + creatorName +
        ", modifyTime=" + modifyTime +
        ", modifierId=" + modifierId +
        ", modifierName=" + modifierName +
        ", parentId=" + parentId +
        ", roleName=" + roleName +
        ", roleCode=" + roleCode +
        ", roleType=" + roleType +
        ", roleFlag=" + roleFlag +
        ", rolePublic=" + rolePublic +
        ", roleRemark=" + roleRemark +
        ", roleSort=" + roleSort +
        ", orgId=" + orgId +
        ", orgName=" + orgName +
        ", orgCode=" + orgCode +
        ", appKey=" + appKey +
        ", appName=" + appName +
        "}";
    }
}
