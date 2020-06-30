package com.shree.intergration.common.web.rest.layui;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "Layui下拉选项基础结果")
@Data
public class LaySelectItem {

    @ApiModelProperty(value = "选项名", dataType = "String")
    private String name;

    @ApiModelProperty(value = "选项值", dataType = "String")
    private String value;

    @ApiModelProperty(value = "是否选中", dataType = "String")
    private String selected;

    @ApiModelProperty(value = "是否禁用", dataType = "String")
    private String disabled;
}
