package com.shree.intergration.common.base.lang.convert;

/**
 * 字段转换器接口
 */
public interface IFieldConverter {

    /**
     * SET 方法时调用的转换
     *
     * @param source 源类型
     * @return 转换类型
     */
    Object setterChange(Object source);

    /**
     * GET 方法时调用的转换
     *
     * @param source 源类型
     * @return 转换类型
     */
    Object getterChange(Object source);
}
