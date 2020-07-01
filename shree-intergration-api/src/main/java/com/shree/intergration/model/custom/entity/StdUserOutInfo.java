package com.shree.intergration.model.custom.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "用户信息【接口版】")
@Data
public class StdUserOutInfo {

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

    @ApiModelProperty(value = "本次登录SessionID", dataType = "String")
    private String sessionId;

}
