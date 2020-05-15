package com.shree.intergration.model.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 统一平台角色菜单映射信息
 * </p>
 *
 * @author sry123
 * @since 2020-05-15
 */
@TableName("idp_sso_role_resource_map")
public class IdpSsoRoleResourceMap implements Serializable {

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
     * 菜单角色ID
     */
    @TableField("ROLE_ID")
    private Long roleId;
    /**
     * 菜单ID
     */
    @TableField("RESOURCE_ID")
    private Long resourceId;


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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        return "IdpSsoRoleResourceMap{" +
        "id=" + id +
        ", status=" + status +
        ", createTime=" + createTime +
        ", creatorId=" + creatorId +
        ", creatorName=" + creatorName +
        ", modifyTime=" + modifyTime +
        ", modifierId=" + modifierId +
        ", modifierName=" + modifierName +
        ", roleId=" + roleId +
        ", resourceId=" + resourceId +
        "}";
    }
}
