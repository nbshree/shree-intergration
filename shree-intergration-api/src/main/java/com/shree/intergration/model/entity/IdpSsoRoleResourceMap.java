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
 * 统一平台角色菜单映射信息
 * </p>
 *
 * @author shree
 * @since 2020-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("idp_sso_role_resource_map")
public class IdpSsoRoleResourceMap implements Serializable {

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
     * 菜单角色ID
     */
    @TableField("ROLE_ID")
    private Long roleId;

    /**
     * 菜单ID
     */
    @TableField("RESOURCE_ID")
    private Long resourceId;


    public static final String ID = "ID";

    public static final String STATUS = "STATUS";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATOR_ID = "CREATOR_ID";

    public static final String CREATOR_NAME = "CREATOR_NAME";

    public static final String MODIFY_TIME = "MODIFY_TIME";

    public static final String MODIFIER_ID = "MODIFIER_ID";

    public static final String MODIFIER_NAME = "MODIFIER_NAME";

    public static final String ROLE_ID = "ROLE_ID";

    public static final String RESOURCE_ID = "RESOURCE_ID";

}
