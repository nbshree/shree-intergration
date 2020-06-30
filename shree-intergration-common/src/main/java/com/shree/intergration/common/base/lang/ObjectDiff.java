package com.shree.intergration.common.base.lang;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 对象比较描述
 *
 * @author Victor
 */
public class ObjectDiff {

    /**
     * 对象类型名称
     */
    @Getter
    @Setter
    protected String className;

    /**
     * 对象类型
     */
    @Getter
    @Setter
    protected Class<?> classType;

    /**
     * 主键
     */
    @Getter
    @Setter
    protected Object primaryKey;

    /**
     * 值不同的字段列表
     */
    @Getter
    @Setter
    protected List<FieldDiff> fields;

    public ObjectDiff() {
        this.fields = new ArrayList<>();
    }

}
