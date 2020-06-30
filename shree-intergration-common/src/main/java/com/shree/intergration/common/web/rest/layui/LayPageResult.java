package com.shree.intergration.common.web.rest.layui;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shree.intergration.common.web.rest.RestResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel(value = "Layui分页请求结果")
@Data
@EqualsAndHashCode(callSuper = true)
public class LayPageResult<T> extends RestResult<T> {

    @ApiModelProperty(value = "分页查询数据总数", dataType = "int")
    private int count;

    @ApiModelProperty(value = "分页起始记录行号", dataType = "int")
    private int start;

    @ApiModelProperty(value = "分页起始页面号", dataType = "long")
    private long current;

    public void fillByMybatisPage(IPage pageData) {
        this.setData((T) pageData.getRecords());
        this.setCount((int) pageData.getTotal());
        this.setStart((int) ((pageData.getCurrent() - 1) * pageData.getSize()));
        this.setCurrent(pageData.getCurrent());
    }
}
