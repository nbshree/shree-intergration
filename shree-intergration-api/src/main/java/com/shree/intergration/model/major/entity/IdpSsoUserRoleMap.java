package com.shree.intergration.model.major.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 统一平台用户角色映射信息
 * </p>
 *
 * @author klz
 * @since 2020-06-30
 */
public class IdpSsoUserRoleMap extends Model<IdpSsoUserRoleMap> {

    /**
    * 所有字段名
    */
    public static class Columns {
        /**
        * ID@(SnowflakeId)
        */
        public static final String id = "ID";
        /**
        * 记录状态@(0:删除,1:正常)
        */
        public static final String status = "STATUS";
        /**
        * 创建时间
        */
        public static final String createTime = "CREATE_TIME";
        /**
        * 创建者ID
        */
        public static final String creatorId = "CREATOR_ID";
        /**
        * 创建者名称@(用户名)
        */
        public static final String creatorName = "CREATOR_NAME";
        /**
        * 修改时间
        */
        public static final String modifyTime = "MODIFY_TIME";
        /**
        * 修改者ID
        */
        public static final String modifierId = "MODIFIER_ID";
        /**
        * 修改者名称@(用户名)
        */
        public static final String modifierName = "MODIFIER_NAME";
        /**
        * 用户ID
        */
        public static final String userId = "USER_ID";
        /**
        * 用户名
        */
        public static final String userName = "USER_NAME";
        /**
        * 角色ID
        */
        public static final String roleId = "ROLE_ID";
        /**
        * 角色标志@(0:菜单角色,1:数据角色)
        */
        public static final String roleFlag = "ROLE_FLAG";
        /**
        * 角色所属者ID@(企业ID或用户ID)
        */
        public static final String roleOwnerId = "ROLE_OWNER_ID";
        /**
        * 角色所属类型@(0:企业,1:用户)
        */
        public static final String roleOwnerType = "ROLE_OWNER_TYPE";
    }

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
    private LocalDateTime createTime;

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
    private LocalDateTime modifyTime;

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
    protected Serializable pkVal() {
        return this.id;
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
