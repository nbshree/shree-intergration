package com.shree.intergration.model.custom.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "系统菜单【接口版】")
@Data
public class TrunkMenu {

    @ApiModelProperty(value = "数据记录ID", dataType = "String")
    private String id;

    @ApiModelProperty(value = "父级资源ID", dataType = "String")
    private String parentId;

    @ApiModelProperty(value = "资源名称", dataType = "String")
    private String resourceName;

    @ApiModelProperty(value = "资源类型@(0:目录,1:菜单/页面,2:功能,-1:隐藏)", dataType = "String")
    private String resourceType;

    @ApiModelProperty(value = "资源路径@(中文显示)", dataType = "String")
    private String resourcePath;

    @ApiModelProperty(value = "资源访问URL", dataType = "String")
    private String resourceUrl;

    @ApiModelProperty(value = "资源排序", dataType = "String")
    private String resourceSort;

    @ApiModelProperty(value = "资源模式@(1:主界面,2:弹出窗)", dataType = "String")
    private String resourceMode;

    @ApiModelProperty(value = "资源(主要是菜单)图标", dataType = "String")
    private String resourceIcon;
}
