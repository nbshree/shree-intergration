package com.shree.intergration.common.base.lang.convert;

/**
 * 字段转换器
 */
public class FieldConverter implements IFieldConverter {

    @Override
    public Object setterChange(Object source) {
        return source;
    }

    @Override
    public Object getterChange(Object source) {
        return source;
    }
}
