package com.shree.intergration.model.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 统一平台用户信息
 * </p>
 *
 * @author sry123
 * @since 2020-05-15
 */
@TableName("idp_sso_user_info")
public class IdpSsoUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id@(SnowflakeId)
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
     * 活动状态@(0:禁用,1:启用)
     */
    @TableField("ACTIVE_STATUS")
    private String activeStatus;
    /**
     * 审核状态@(0:未审核,1:已审核,2:不通过)
     */
    @TableField("APPROVE_STATUS")
    private String approveStatus;
    /**
     * 审批时间
     */
    @TableField("APPROVE_TIME")
    private Date approveTime;
    /**
     * 审批人id
     */
    @TableField("APPROVER_ID")
    private Long approverId;
    /**
     * 审批人名称
     */
    @TableField("APPROVER_NAME")
    private String approverName;
    /**
     * 用户名
     */
    @TableField("USER_NAME")
    private String userName;
    /**
     * 用户昵称
     */
    @TableField("USER_NICK_NAME")
    private String userNickName;
    /**
     * 用户实名
     */
    @TableField("USER_REAL_NAME")
    private String userRealName;
    /**
     * 用户密码
     */
    @TableField("USER_PWD")
    private String userPwd;
    /**
     * 用户类型@(0:平台管理员,1:企业管理员,2:部门管理员,3:普通用户)
     */
    @TableField("USER_TYPE")
    private String userType;
    /**
     * 性别
     */
    @TableField("GENDER")
    private String gender;
    /**
     * 电子邮件
     */
    @TableField("MAIL")
    private String mail;
    /**
     * 电话号码
     */
    @TableField("TEL")
    private String tel;
    /**
     * 手机号码
     */
    @TableField("MOBILE")
    private String mobile;
    /**
     * 证件类型@(1:身份证,2:护照,3:军官证)
     */
    @TableField("CERT_TYPE")
    private String certType;
    /**
     * 证件号码
     */
    @TableField("CERT_NO")
    private String certNo;
    /**
     * 所在组织id
     */
    @TableField("ORG_ID")
    private Long orgId;
    /**
     * 组织代码
     */
    @TableField("ORG_CODE")
    private String orgCode;
    /**
     * 所在组织名称
     */
    @TableField("ORG_NAME")
    private String orgName;
    /**
     * 所在部门id
     */
    @TableField("DEPT_ID")
    private Long deptId;
    /**
     * 所在部门名称
     */
    @TableField("DEPT_NAME")
    private String deptName;
    /**
     * 报检员号
     */
    @TableField("CIQ_CODE")
    private String ciqCode;
    /**
     * 报关员号
     */
    @TableField("CUSTOMS_CODE")
    private String customsCode;
    /**
     * 中国电子口岸卡证书号码
     */
    @TableField("CNCA_CODE")
    private String cncaCode;
    /**
     * 所属区县名称
     */
    @TableField("DISTRICT_NAME")
    private String districtName;


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

    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public Long getApproverId() {
        return approverId;
    }

    public void setApproverId(Long approverId) {
        this.approverId = approverId;
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getCiqCode() {
        return ciqCode;
    }

    public void setCiqCode(String ciqCode) {
        this.ciqCode = ciqCode;
    }

    public String getCustomsCode() {
        return customsCode;
    }

    public void setCustomsCode(String customsCode) {
        this.customsCode = customsCode;
    }

    public String getCncaCode() {
        return cncaCode;
    }

    public void setCncaCode(String cncaCode) {
        this.cncaCode = cncaCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    @Override
    public String toString() {
        return "IdpSsoUserInfo{" +
        "id=" + id +
        ", status=" + status +
        ", createTime=" + createTime +
        ", creatorId=" + creatorId +
        ", creatorName=" + creatorName +
        ", modifyTime=" + modifyTime +
        ", modifierId=" + modifierId +
        ", modifierName=" + modifierName +
        ", activeStatus=" + activeStatus +
        ", approveStatus=" + approveStatus +
        ", approveTime=" + approveTime +
        ", approverId=" + approverId +
        ", approverName=" + approverName +
        ", userName=" + userName +
        ", userNickName=" + userNickName +
        ", userRealName=" + userRealName +
        ", userPwd=" + userPwd +
        ", userType=" + userType +
        ", gender=" + gender +
        ", mail=" + mail +
        ", tel=" + tel +
        ", mobile=" + mobile +
        ", certType=" + certType +
        ", certNo=" + certNo +
        ", orgId=" + orgId +
        ", orgCode=" + orgCode +
        ", orgName=" + orgName +
        ", deptId=" + deptId +
        ", deptName=" + deptName +
        ", ciqCode=" + ciqCode +
        ", customsCode=" + customsCode +
        ", cncaCode=" + cncaCode +
        ", districtName=" + districtName +
        "}";
    }
}
