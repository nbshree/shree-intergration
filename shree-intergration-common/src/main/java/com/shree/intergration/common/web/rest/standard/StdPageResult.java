package com.shree.intergration.common.web.rest.standard;

import com.shree.intergration.common.web.rest.RestResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel(value = "标准分页请求结果")
@Data
@EqualsAndHashCode(callSuper = true)
public class StdPageResult<T> extends RestResult<T> {
    @ApiModelProperty(value = "分页查询数据总数", dataType = "int")
    private int count;

    @ApiModelProperty(value = "分页起始记录行号", dataType = "int")
    private int start;

    @ApiModelProperty(value = "分页起始页面号", dataType = "long")
    private long current;
}
