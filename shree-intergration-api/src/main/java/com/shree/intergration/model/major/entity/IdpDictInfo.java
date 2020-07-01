package com.shree.intergration.model.major.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 字典信息表
 * </p>
 *
 * @author shree
 * @since 2020-07-01
 */
public class IdpDictInfo extends Model<IdpDictInfo> {

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
        * 字典类型代码
        */
        public static final String dictTypeCode = "dict_type_code";
        /**
        * 字典类型名称
        */
        public static final String dictTypeName = "dict_type_name";
        /**
        * 字典代码
        */
        public static final String dictCode = "dict_code";
        /**
        * 字典名称
        */
        public static final String dictName = "dict_name";
        /**
        * 字典键值
        */
        public static final String dictValue = "dict_value";
        /**
        * 是否启用字典@(0:否,1:是)
        */
        public static final String dictEnabled = "dict_enabled";
        /**
        * 备注
        */
        public static final String remark = "remark";
        /**
        * 排序
        */
        public static final String sort = "sort";
        /**
        * 是否为开放字典@(0:否,1:是)
        */
        public static final String publicFlag = "public_flag";
        /**
        * 是否为系统级字典@(0:否,1:是)
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
     * 字典类型代码
     */
    @TableField("dict_type_code")
    private String dictTypeCode;

    /**
     * 字典类型名称
     */
    @TableField("dict_type_name")
    private String dictTypeName;

    /**
     * 字典代码
     */
    @TableField("dict_code")
    private String dictCode;

    /**
     * 字典名称
     */
    @TableField("dict_name")
    private String dictName;

    /**
     * 字典键值
     */
    @TableField("dict_value")
    private String dictValue;

    /**
     * 是否启用字典@(0:否,1:是)
     */
    @TableField("dict_enabled")
    private String dictEnabled;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序
     */
    private String sort;

    /**
     * 是否为开放字典@(0:否,1:是)
     */
    @TableField("public_flag")
    private String publicFlag;

    /**
     * 是否为系统级字典@(0:否,1:是)
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

    public String getDictTypeCode() {
        return dictTypeCode;
    }

    public void setDictTypeCode(String dictTypeCode) {
        this.dictTypeCode = dictTypeCode;
    }

    public String getDictTypeName() {
        return dictTypeName;
    }

    public void setDictTypeName(String dictTypeName) {
        this.dictTypeName = dictTypeName;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getDictEnabled() {
        return dictEnabled;
    }

    public void setDictEnabled(String dictEnabled) {
        this.dictEnabled = dictEnabled;
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
        return "IdpDictInfo{" +
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
        ", dictTypeCode=" + dictTypeCode +
        ", dictTypeName=" + dictTypeName +
        ", dictCode=" + dictCode +
        ", dictName=" + dictName +
        ", dictValue=" + dictValue +
        ", dictEnabled=" + dictEnabled +
        ", remark=" + remark +
        ", sort=" + sort +
        ", publicFlag=" + publicFlag +
        ", systemFlag=" + systemFlag +
        "}";
    }
}
