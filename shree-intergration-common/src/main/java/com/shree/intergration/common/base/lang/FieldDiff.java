package com.shree.intergration.common.base.lang;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;

/**
 * 字段比较描述
 *
 * @author Victor
 */
public class FieldDiff {

    /**
     * 字段名称
     */
    @Getter
    @Setter
    protected String fieldName;

    /**
     * 字段标题
     */
    @Getter
    @Setter
    protected String fieldCaption;

    /**
     * 字段类型
     */
    @Getter
    @Setter
    protected Class<?> fieldType;

    /**
     * 来源对象值
     */
    @Getter
    @Setter
    protected Object sourceValue;

    /**
     * 目标对象值
     */
    @Getter
    @Setter
    protected Object targetValue;

    public FieldDiff() {
    }

    /**
     * 值不同的字段描述
     *
     * @param field       字段
     * @param sourceValue 来源对象值
     * @param targetValue 目标对象值
     */
    public FieldDiff(Field field, Object sourceValue, Object targetValue) {
        this.fieldName = field.getName();
        this.fieldType = field.getType();
        this.sourceValue = sourceValue;
        this.targetValue = targetValue;
    }

    /**
     * 值不同的字段描述
     *
     * @param field        字段
     * @param fieldCaption 字段中文名
     * @param sourceValue  来源对象值
     * @param targetValue  目标对象值
     */
    public FieldDiff(Field field, String fieldCaption, Object sourceValue, Object targetValue) {
        this(field, sourceValue, targetValue);
        this.fieldCaption = fieldCaption;
    }

    /**
     * 值不同的字段描述
     *
     * @param fieldName   字段名称
     * @param fieldType   字段类型
     * @param sourceValue 来源对象值
     * @param targetValue 目标对象值
     */
    public FieldDiff(String fieldName, Class<?> fieldType, Object sourceValue, Object targetValue) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.sourceValue = sourceValue;
        this.targetValue = targetValue;
    }

    /**
     * 值不同的字段描述
     *
     * @param fieldName    字段名称
     * @param fieldCaption 字段中文名
     * @param fieldType    字段类型
     * @param sourceValue  来源对象值
     * @param targetValue  目标对象值
     */
    public FieldDiff(String fieldName, String fieldCaption, Class<?> fieldType, Object sourceValue, Object targetValue) {
        this(fieldName, fieldType, sourceValue, targetValue);
        this.fieldCaption = fieldCaption;
    }
}
