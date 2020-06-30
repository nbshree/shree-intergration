package com.shree.intergration.common.web.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "Rest基础返回结果")
@Data
public abstract class BaseResult {

    public static final int FAILED = 0;
    public static final int SUCCESS = 1;
    public static final int ERROR = -1;

    @ApiModelProperty(value = "REST请求状态结果【成功 1 | 失败 0 | 错误 -1】", dataType = "int", allowableValues = "1,0,-1")
    protected int status;

    @ApiModelProperty(value = "REST请求返回消息", dataType = "String")
    protected String message;
}
