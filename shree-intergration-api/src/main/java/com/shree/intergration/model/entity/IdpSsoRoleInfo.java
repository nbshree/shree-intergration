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
 * 统一平台角色结构信息
 * </p>
 *
 * @author riusky
 * @since 2020-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("idp_sso_role_info")
public class IdpSsoRoleInfo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * ID@(SnowflakeId)
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


    public static final String ID = "ID";

    public static final String STATUS = "STATUS";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATOR_ID = "CREATOR_ID";

    public static final String CREATOR_NAME = "CREATOR_NAME";

    public static final String MODIFY_TIME = "MODIFY_TIME";

    public static final String MODIFIER_ID = "MODIFIER_ID";

    public static final String MODIFIER_NAME = "MODIFIER_NAME";

    public static final String PARENT_ID = "PARENT_ID";

    public static final String ROLE_NAME = "ROLE_NAME";

    public static final String ROLE_CODE = "ROLE_CODE";

    public static final String ROLE_TYPE = "ROLE_TYPE";

    public static final String ROLE_FLAG = "ROLE_FLAG";

    public static final String ROLE_PUBLIC = "ROLE_PUBLIC";

    public static final String ROLE_REMARK = "ROLE_REMARK";

    public static final String ROLE_SORT = "ROLE_SORT";

    public static final String ORG_ID = "ORG_ID";

    public static final String ORG_NAME = "ORG_NAME";

    public static final String ORG_CODE = "ORG_CODE";

    public static final String APP_KEY = "APP_KEY";

    public static final String APP_NAME = "APP_NAME";

}
