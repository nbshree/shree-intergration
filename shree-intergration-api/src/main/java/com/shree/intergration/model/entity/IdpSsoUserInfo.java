package com.shree.intergration.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 统一平台用户信息
 * </p>
 *
 * @author riusky
 * @since 2020-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("idp_sso_user_info")
public class IdpSsoUserInfo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id@(SnowflakeId)
     */
    @TableId(value = "ID", type = IdType.ID_WORKER)
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
    private LocalDateTime approveTime;

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


    public static final String ID = "ID";

    public static final String STATUS = "STATUS";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATOR_ID = "CREATOR_ID";

    public static final String CREATOR_NAME = "CREATOR_NAME";

    public static final String MODIFY_TIME = "MODIFY_TIME";

    public static final String MODIFIER_ID = "MODIFIER_ID";

    public static final String MODIFIER_NAME = "MODIFIER_NAME";

    public static final String ACTIVE_STATUS = "ACTIVE_STATUS";

    public static final String APPROVE_STATUS = "APPROVE_STATUS";

    public static final String APPROVE_TIME = "APPROVE_TIME";

    public static final String APPROVER_ID = "APPROVER_ID";

    public static final String APPROVER_NAME = "APPROVER_NAME";

    public static final String USER_NAME = "USER_NAME";

    public static final String USER_NICK_NAME = "USER_NICK_NAME";

    public static final String USER_REAL_NAME = "USER_REAL_NAME";

    public static final String USER_PWD = "USER_PWD";

    public static final String USER_TYPE = "USER_TYPE";

    public static final String GENDER = "GENDER";

    public static final String MAIL = "MAIL";

    public static final String TEL = "TEL";

    public static final String MOBILE = "MOBILE";

    public static final String CERT_TYPE = "CERT_TYPE";

    public static final String CERT_NO = "CERT_NO";

    public static final String ORG_ID = "ORG_ID";

    public static final String ORG_CODE = "ORG_CODE";

    public static final String ORG_NAME = "ORG_NAME";

    public static final String DEPT_ID = "DEPT_ID";

    public static final String DEPT_NAME = "DEPT_NAME";

    public static final String CIQ_CODE = "CIQ_CODE";

    public static final String CUSTOMS_CODE = "CUSTOMS_CODE";

    public static final String CNCA_CODE = "CNCA_CODE";

    public static final String DISTRICT_NAME = "DISTRICT_NAME";

}
