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
 * 统一平台用户角色映射信息
 * </p>
 *
 * @author riusky
 * @since 2020-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("idp_sso_user_role_map")
public class IdpSsoUserRoleMap implements Serializable {

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


    public static final String ID = "ID";

    public static final String STATUS = "STATUS";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATOR_ID = "CREATOR_ID";

    public static final String CREATOR_NAME = "CREATOR_NAME";

    public static final String MODIFY_TIME = "MODIFY_TIME";

    public static final String MODIFIER_ID = "MODIFIER_ID";

    public static final String MODIFIER_NAME = "MODIFIER_NAME";

    public static final String USER_ID = "USER_ID";

    public static final String USER_NAME = "USER_NAME";

    public static final String ROLE_ID = "ROLE_ID";

    public static final String ROLE_FLAG = "ROLE_FLAG";

    public static final String ROLE_OWNER_ID = "ROLE_OWNER_ID";

    public static final String ROLE_OWNER_TYPE = "ROLE_OWNER_TYPE";

}
