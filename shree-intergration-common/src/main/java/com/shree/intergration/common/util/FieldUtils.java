package com.shree.intergration.common.util;



import com.shree.intergration.common.base.lang.convert.FieldConverter;
import com.shree.intergration.common.base.lang.convert.FieldMap;
import com.shree.intergration.common.base.lang.convert.IFieldConverter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FieldUtils {

    private static final List<FieldMap.Access> READ_RIGHTS = Arrays.asList(FieldMap.Access.AUTO, FieldMap.Access.READ_WRITE, FieldMap.Access.READ_ONLY);

    /**
     * 查找当前类里所有的字段
     *
     * @param classType            被查找的类
     * @param searchFromSuperClass 是否查找超类的字段
     * @return 字段集合
     */
    public static Field[] getFieldArray(Class<?> classType, boolean searchFromSuperClass) {
        List<Field> fieldList = getFieldList(classType, searchFromSuperClass);
        return fieldList.toArray(new Field[fieldList.size()]);
    }

    /**
     * 获取所有字段, 默认查询所有子类
     *
     * @param classType 被查找的类
     */
    public static List<Field> getFieldList(Class<?> classType) {
        return getFieldList(classType, true);
    }

    /**
     * 获取所有字段
     *
     * @param classType            被查找的类
     * @param searchFromSuperClass 是否查找父类
     */
    public static List<Field> getFieldList(Class<?> classType, boolean searchFromSuperClass) {
        List<Field> fieldList = new ArrayList<>();
        Field[] fields = classType.getDeclaredFields();
        fieldList.addAll(Arrays.asList(fields));
        Class<?> superClass = classType.getSuperclass();
        if (searchFromSuperClass && null != superClass) {
            fieldList.addAll(getFieldList(superClass, true));
        }
        return fieldList;
    }

    /**
     * 获取字段
     *
     * @param classType 被查找的类
     * @param fieldName 字段名
     * @return 字段
     */
    public static Field getField(Class<?> classType, String fieldName) {
        Field field = null;
        try {
            field = classType.getDeclaredField(fieldName);
        } catch (NoSuchFieldException ex) {
            Class<?> superClass = classType.getSuperclass();
            if (null != superClass) {
                field = getField(superClass, fieldName);
            }
        }
        return field;
    }

    /**
     * 获取指定字段值
     *
     * @param sourceObject 来源对象
     * @param fieldName    字段名
     * @return 指定字段值
     */
    public static Object getFieldValue(Object sourceObject, String fieldName) throws InstantiationException, IllegalAccessException {
        return getFieldValue(sourceObject.getClass(), sourceObject, fieldName);
    }

    /**
     * 获取指定字段值
     *
     * @param classType    当前类型, 一般为当前类型, 但也有可能是父类型
     * @param sourceObject 来源对象
     * @param fieldName    字段名
     * @return 指定字段值
     */
    private static Object getFieldValue(Class<?> classType, Object sourceObject, String fieldName) throws InstantiationException, IllegalAccessException {
        Object value = null;
        Field field = null;
        try {
            field = classType.getDeclaredField(fieldName);
            field.setAccessible(true);
            try {
                value = field.get(sourceObject);
                FieldMap fieldMap = field.getAnnotation(FieldMap.class);
                if (null != fieldMap
                        && READ_RIGHTS.contains(fieldMap.access())
                        && FieldConverter.class != fieldMap.converter()) {
                    try {
                        IFieldConverter converter = fieldMap.converter().newInstance();
                        value = converter.getterChange(value);
                    } catch (InstantiationException ex) {
                        throw ex;
                    }
                }
            } catch (IllegalAccessException ex) {
                throw ex;
            }
        } catch (NoSuchFieldException ex) {
            if (null != classType.getSuperclass()) {
                value = getFieldValue(classType.getSuperclass(), sourceObject, fieldName);
            }
        }
        return value;
    }

    /**
     * 设置指定字段值
     *
     * @param data      目标对象
     * @param fieldName 字段名
     * @param value     字段值
     */
    public static void setFieldValue(Object data, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        setFieldValue(data.getClass(), data, fieldName, value);
    }

    /**
     * 设置指定字段值
     *
     * @param classType 当前类型, 一般为当前类型, 但也有可能是父类型
     * @param data      目标对象
     * @param fieldName 字段名
     * @param value     字段值
     */
    private static void setFieldValue(Class<?> classType, Object data, String fieldName, Object value) throws IllegalAccessException {
        Field field = null;
        try {
            field = classType.getDeclaredField(fieldName);
            field.setAccessible(true);
            if (value != null) {
                try {
                    FieldMap fieldMap = field.getAnnotation(FieldMap.class);
                    if (null != fieldMap
                            && READ_RIGHTS.contains(fieldMap.access())
                            && FieldConverter.class != fieldMap.converter()) {
                        try {
                            IFieldConverter converter = fieldMap.converter().newInstance();
                            value = converter.setterChange(value);
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        }
                    }
                    field.set(data, value);
                } catch (IllegalAccessException ex) {
                    throw ex;
                }
            } else {
                try {
                    field.set(data, null);
                } catch (IllegalAccessException ex) {
                    throw ex;
                }
            }
        } catch (NoSuchFieldException ex) {
            if (null != classType.getSuperclass()) {
                setFieldValue(classType.getSuperclass(), data, fieldName, value);
            }
        }
    }
}
