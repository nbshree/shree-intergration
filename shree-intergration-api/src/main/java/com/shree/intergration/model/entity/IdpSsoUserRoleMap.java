package com.shree.intergration.model.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 统一平台用户角色映射信息
 * </p>
 *
 * @author sry123
 * @since 2020-05-15
 */
@TableName("idp_sso_user_role_map")
public class IdpSsoUserRoleMap implements Serializable {

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
     * 用户ID
     */
    @TableField("USER_ID")
    private Long userId;
    /**
     * 用户名
     */
    @TableField("USER_NAME")
    private String userName;
    /**
     * 角色ID
     */
    @TableField("ROLE_ID")
    private Long roleId;
    /**
     * 角色标志@(0:菜单角色,1:数据角色)
     */
    @TableField("ROLE_FLAG")
    private String roleFlag;
    /**
     * 角色所属者ID@(企业ID或用户ID)
     */
    @TableField("ROLE_OWNER_ID")
    private Long roleOwnerId;
    /**
     * 角色所属类型@(0:企业,1:用户)
     */
    @TableField("ROLE_OWNER_TYPE")
    private String roleOwnerType;


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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleFlag() {
        return roleFlag;
    }

    public void setRoleFlag(String roleFlag) {
        this.roleFlag = roleFlag;
    }

    public Long getRoleOwnerId() {
        return roleOwnerId;
    }

    public void setRoleOwnerId(Long roleOwnerId) {
        this.roleOwnerId = roleOwnerId;
    }

    public String getRoleOwnerType() {
        return roleOwnerType;
    }

    public void setRoleOwnerType(String roleOwnerType) {
        this.roleOwnerType = roleOwnerType;
    }

    @Override
    public String toString() {
        return "IdpSsoUserRoleMap{" +
        "id=" + id +
        ", status=" + status +
        ", createTime=" + createTime +
        ", creatorId=" + creatorId +
        ", creatorName=" + creatorName +
        ", modifyTime=" + modifyTime +
        ", modifierId=" + modifierId +
        ", modifierName=" + modifierName +
        ", userId=" + userId +
        ", userName=" + userName +
        ", roleId=" + roleId +
        ", roleFlag=" + roleFlag +
        ", roleOwnerId=" + roleOwnerId +
        ", roleOwnerType=" + roleOwnerType +
        "}";
    }
}
