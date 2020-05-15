package com.shree.intergration.vo.result.layui;

import com.shree.intergration.vo.result.RestResult;
import lombok.Data;

@Data
public class LayPageResult<T> extends RestResult<T> {

    private int count;

    private int start;
}
