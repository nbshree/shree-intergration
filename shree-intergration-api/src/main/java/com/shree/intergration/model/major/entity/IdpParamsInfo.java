package com.shree.intergration.model.major.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 参数信息表
 * </p>
 *
 * @author shree
 * @since 2020-07-01
 */
public class IdpParamsInfo extends Model<IdpParamsInfo> {

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
        * 父级ID
        */
        public static final String parentId = "parent_id";
        /**
        * 参数类型代码
        */
        public static final String paramTypeCode = "param_type_code";
        /**
        * 参数类型名称
        */
        public static final String paramTypeName = "param_type_name";
        /**
        * 参数代码
        */
        public static final String paramCode = "param_code";
        /**
        * 参数名称
        */
        public static final String paramName = "param_name";
        /**
        * 参数键值
        */
        public static final String paramValue = "param_value";
        /**
        * 是否启用参数@(0:否,1:是)
        */
        public static final String paramEnabled = "param_enabled";
        /**
        * 备注
        */
        public static final String remark = "remark";
        /**
        * 排序
        */
        public static final String sort = "sort";
        /**
        * 是否为开放参数@(0:否,1:是)
        */
        public static final String publicFlag = "public_flag";
        /**
        * 是否为系统级参数@(0:否,1:是)
        */
        public static final String systemFlag = "system_flag";
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
     * 父级ID
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 参数类型代码
     */
    @TableField("param_type_code")
    private String paramTypeCode;

    /**
     * 参数类型名称
     */
    @TableField("param_type_name")
    private String paramTypeName;

    /**
     * 参数代码
     */
    @TableField("param_code")
    private String paramCode;

    /**
     * 参数名称
     */
    @TableField("param_name")
    private String paramName;

    /**
     * 参数键值
     */
    @TableField("param_value")
    private String paramValue;

    /**
     * 是否启用参数@(0:否,1:是)
     */
    @TableField("param_enabled")
    private String paramEnabled;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序
     */
    private String sort;

    /**
     * 是否为开放参数@(0:否,1:是)
     */
    @TableField("public_flag")
    private String publicFlag;

    /**
     * 是否为系统级参数@(0:否,1:是)
     */
    @TableField("system_flag")
    private String systemFlag;


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

    public String getParamTypeCode() {
        return paramTypeCode;
    }

    public void setParamTypeCode(String paramTypeCode) {
        this.paramTypeCode = paramTypeCode;
    }

    public String getParamTypeName() {
        return paramTypeName;
    }

    public void setParamTypeName(String paramTypeName) {
        this.paramTypeName = paramTypeName;
    }

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getParamEnabled() {
        return paramEnabled;
    }

    public void setParamEnabled(String paramEnabled) {
        this.paramEnabled = paramEnabled;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getPublicFlag() {
        return publicFlag;
    }

    public void setPublicFlag(String publicFlag) {
        this.publicFlag = publicFlag;
    }

    public String getSystemFlag() {
        return systemFlag;
    }

    public void setSystemFlag(String systemFlag) {
        this.systemFlag = systemFlag;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "IdpParamsInfo{" +
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
        ", paramTypeCode=" + paramTypeCode +
        ", paramTypeName=" + paramTypeName +
        ", paramCode=" + paramCode +
        ", paramName=" + paramName +
        ", paramValue=" + paramValue +
        ", paramEnabled=" + paramEnabled +
        ", remark=" + remark +
        ", sort=" + sort +
        ", publicFlag=" + publicFlag +
        ", systemFlag=" + systemFlag +
        "}";
    }
}
