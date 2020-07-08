package com.shree.intergration.model.major.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 企业信息表, 查询对象
 * </p>
 *
 * @author shree
 * @since 2020-07-01
 */
public class IdpEntInfoSearch implements Serializable {

    /**
     * id@(SnowflakeId)
     */
    @TableField("id")
    private Long id;

    /**
     * id@(SnowflakeId) - 开始
     */
    @TableField("id")
    private Long idStart;

    /**
     * id@(SnowflakeId) - 结束
     */
    @TableField("id")
    private Long idEnd;
    /**
     * 记录状态@(0:删除,1:正常)
     */
    @TableField("status")
    private String status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 创建时间 - 开始
     */
    @TableField("create_time")
    private LocalDateTime createTimeStart;

    /**
     * 创建时间 - 结束
     */
    @TableField("create_time")
    private LocalDateTime createTimeEnd;
    /**
     * 创建者ID
     */
    @TableField("creator_id")
    private Long creatorId;

    /**
     * 创建者ID - 开始
     */
    @TableField("creator_id")
    private Long creatorIdStart;

    /**
     * 创建者ID - 结束
     */
    @TableField("creator_id")
    private Long creatorIdEnd;
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
     * 修改时间 - 开始
     */
    @TableField("modify_time")
    private LocalDateTime modifyTimeStart;

    /**
     * 修改时间 - 结束
     */
    @TableField("modify_time")
    private LocalDateTime modifyTimeEnd;
    /**
     * 修改者ID
     */
    @TableField("modifier_id")
    private Long modifierId;

    /**
     * 修改者ID - 开始
     */
    @TableField("modifier_id")
    private Long modifierIdStart;

    /**
     * 修改者ID - 结束
     */
    @TableField("modifier_id")
    private Long modifierIdEnd;
    /**
     * 修改者名称@(用户名)
     */
    @TableField("modifier_name")
    private String modifierName;

    /**
     * 企业类型(0-企业，1-第三方，2-园区)
     */
    @TableField("ent_type")
    private String entType;

    /**
     * 企业名称
     */
    @TableField("ent_name")
    private String entName;

    /**
     * 所属上级ID
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 所属上级ID - 开始
     */
    @TableField("parent_id")
    private Long parentIdStart;

    /**
     * 所属上级ID - 结束
     */
    @TableField("parent_id")
    private Long parentIdEnd;
    /**
     * 上级名称
     */
    @TableField("parent_name")
    private String parentName;

    /**
     * 联系人
     */
    @TableField("contact")
    private String contact;

    /**
     * 手机号码
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 企业状态@(0:未激活,1:正常,2:禁用)
     */
    @TableField("ent_status")
    private String entStatus;


    /**
     * 获取: id@(SnowflakeId)
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置: id@(SnowflakeId)
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取: id@(SnowflakeId) - 开始
     */
    public Long getIdStart() {
        return idStart;
    }

    /**
     * 设置: id@(SnowflakeId) - 开始
     */
    public void setIdStart(Long id) {
        this.idStart = id;
    }

    /**
     * 获取: id@(SnowflakeId) - 结束
     */
    public Long getIdEnd() {
        return idEnd;
    }

    /**
     * 设置: id@(SnowflakeId) - 结束
     */
    public void setIdEnd(Long id) {
        this.idEnd = id;
        }
    /**
     * 获取: 记录状态@(0:删除,1:正常)
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置: 记录状态@(0:删除,1:正常)
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取: 创建时间
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * 设置: 创建时间
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取: 创建时间 - 开始
     */
    public LocalDateTime getCreateTimeStart() {
        return createTimeStart;
    }

    /**
     * 设置: 创建时间 - 开始
     */
    public void setCreateTimeStart(LocalDateTime createTime) {
        this.createTimeStart = createTime;
    }

    /**
     * 获取: 创建时间 - 结束
     */
    public LocalDateTime getCreateTimeEnd() {
        return createTimeEnd;
    }

    /**
     * 设置: 创建时间 - 结束
     */
    public void setCreateTimeEnd(LocalDateTime createTime) {
        this.createTimeEnd = createTime;
        }
    /**
     * 获取: 创建者ID
     */
    public Long getCreatorId() {
        return creatorId;
    }

    /**
     * 设置: 创建者ID
     */
    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * 获取: 创建者ID - 开始
     */
    public Long getCreatorIdStart() {
        return creatorIdStart;
    }

    /**
     * 设置: 创建者ID - 开始
     */
    public void setCreatorIdStart(Long creatorId) {
        this.creatorIdStart = creatorId;
    }

    /**
     * 获取: 创建者ID - 结束
     */
    public Long getCreatorIdEnd() {
        return creatorIdEnd;
    }

    /**
     * 设置: 创建者ID - 结束
     */
    public void setCreatorIdEnd(Long creatorId) {
        this.creatorIdEnd = creatorId;
        }
    /**
     * 获取: 创建者名称@(用户名)
     */
    public String getCreatorName() {
        return creatorName;
    }

    /**
     * 设置: 创建者名称@(用户名)
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    /**
     * 获取: 修改时间
     */
    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置: 修改时间
     */
    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取: 修改时间 - 开始
     */
    public LocalDateTime getModifyTimeStart() {
        return modifyTimeStart;
    }

    /**
     * 设置: 修改时间 - 开始
     */
    public void setModifyTimeStart(LocalDateTime modifyTime) {
        this.modifyTimeStart = modifyTime;
    }

    /**
     * 获取: 修改时间 - 结束
     */
    public LocalDateTime getModifyTimeEnd() {
        return modifyTimeEnd;
    }

    /**
     * 设置: 修改时间 - 结束
     */
    public void setModifyTimeEnd(LocalDateTime modifyTime) {
        this.modifyTimeEnd = modifyTime;
        }
    /**
     * 获取: 修改者ID
     */
    public Long getModifierId() {
        return modifierId;
    }

    /**
     * 设置: 修改者ID
     */
    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * 获取: 修改者ID - 开始
     */
    public Long getModifierIdStart() {
        return modifierIdStart;
    }

    /**
     * 设置: 修改者ID - 开始
     */
    public void setModifierIdStart(Long modifierId) {
        this.modifierIdStart = modifierId;
    }

    /**
     * 获取: 修改者ID - 结束
     */
    public Long getModifierIdEnd() {
        return modifierIdEnd;
    }

    /**
     * 设置: 修改者ID - 结束
     */
    public void setModifierIdEnd(Long modifierId) {
        this.modifierIdEnd = modifierId;
        }
    /**
     * 获取: 修改者名称@(用户名)
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * 设置: 修改者名称@(用户名)
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName;
    }

    /**
     * 获取: 企业类型(0-企业，1-第三方，2-园区)
     */
    public String getEntType() {
        return entType;
    }

    /**
     * 设置: 企业类型(0-企业，1-第三方，2-园区)
     */
    public void setEntType(String entType) {
        this.entType = entType;
    }

    /**
     * 获取: 企业名称
     */
    public String getEntName() {
        return entName;
    }

    /**
     * 设置: 企业名称
     */
    public void setEntName(String entName) {
        this.entName = entName;
    }

    /**
     * 获取: 所属上级ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置: 所属上级ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取: 所属上级ID - 开始
     */
    public Long getParentIdStart() {
        return parentIdStart;
    }

    /**
     * 设置: 所属上级ID - 开始
     */
    public void setParentIdStart(Long parentId) {
        this.parentIdStart = parentId;
    }

    /**
     * 获取: 所属上级ID - 结束
     */
    public Long getParentIdEnd() {
        return parentIdEnd;
    }

    /**
     * 设置: 所属上级ID - 结束
     */
    public void setParentIdEnd(Long parentId) {
        this.parentIdEnd = parentId;
        }
    /**
     * 获取: 上级名称
     */
    public String getParentName() {
        return parentName;
    }

    /**
     * 设置: 上级名称
     */
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    /**
     * 获取: 联系人
     */
    public String getContact() {
        return contact;
    }

    /**
     * 设置: 联系人
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * 获取: 手机号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置: 手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取: 企业状态@(0:未激活,1:正常,2:禁用)
     */
    public String getEntStatus() {
        return entStatus;
    }

    /**
     * 设置: 企业状态@(0:未激活,1:正常,2:禁用)
     */
    public void setEntStatus(String entStatus) {
        this.entStatus = entStatus;
    }

}