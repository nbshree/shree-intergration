package com.shree.intergration.model.custom.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "用户信息【接口版】")
@Data
public class TrunkUserInfo {

    @ApiModelProperty(value = "数据记录ID", dataType = "String")
    private String id;

    @ApiModelProperty(value = "数据所属企业ID", dataType = "String")
    private String entId;

    @ApiModelProperty(value = "数据所属企业名称", dataType = "String")
    private String entName;

    @ApiModelProperty(value = "用户名", dataType = "String")
    private String userName;

    @ApiModelProperty(value = "用户昵称", dataType = "String")
    private String nickName;

    @ApiModelProperty(value = "管理员类型", dataType = "String")
    private Boolean adminType;

    @ApiModelProperty(value = "进出口数据权限@(I:进口,E:出口,A:全部,N:无权限)", dataType = "String")
    private String permitIe;

    @ApiModelProperty(value = "务类型数据权限@(CB:跨境,EA:快件A类,EB:快件B类,EC:快件C类,A:全部,N:无权限)", dataType = "String")
    private String permitBiz;

    @ApiModelProperty(value = "授权菜单列表", dataType = "List<IdpAppResource>")
    private List<TrunkMenu> menuList;

    @ApiModelProperty(value = "本次登录SessionID", dataType = "String")
    private String sessionId;

    @ApiModelProperty(value = "角色名称", dataType = "String")
    private String roleName;

}
