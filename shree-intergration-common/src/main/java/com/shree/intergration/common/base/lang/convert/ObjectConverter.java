package com.shree.intergration.common.base.lang.convert;


import com.shree.intergration.common.base.lang.FieldDiff;
import com.shree.intergration.common.base.lang.ObjectDiff;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 对象转换器
 */
public class ObjectConverter {

    public static final String[] NORMAL_FIELDS = {
            "ID", "Id", "id",
            "STATUS", "Status", "status",
            "CREATE_TIME", "CreateTime", "createTime",
            "CREATOR_ID", "CreatorId", "creatorId",
            "CREATOR_NAME", "CreatorName", "creatorName",
            "CREATOR", "Creator", "creator",
            "MODIFY_TIME", "ModifyTime", "modifyTime",
            "MODIFIER_ID", "ModifierId", "modifierId",
            "MODIFIER_NAME", "ModifierName", "modifierName",
            "MODIFIER", "Modifier", "modifier"
    };

    public static final List<String> NORMAL_FIELDS_LIST = Arrays.asList(NORMAL_FIELDS);

    /**
     * 过滤Explorer标准库基本字段
     *
     * @param fieldName 字段名称
     * @return 是否属于标准字段
     */
    public static boolean isNormalField(String fieldName) {
        if (NORMAL_FIELDS_LIST.contains(fieldName)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 将目前对象转为另一个类型, 相同的字段会被填入, 不同的则抛弃
     *
     * @param source      来源对象
     * @param targetClass 目标类型
     * @param <TTarget>   目标泛型
     * @return 转换后的的对象
     */
    public static <TTarget> TTarget toEntity(Object source, Class<TTarget> targetClass) {
        return toEntity(source, targetClass, false);
    }

    /**
     * 将目前对象转为另一个类型, 相同的字段会被填入, 不同的则抛弃
     *
     * @param source          来源对象
     * @param targetClass     目标类型
     * @param isSaveNullValue 是否保存空
     * @param <TTarget>       目标泛型
     * @return 转换后的的对象
     */
    public static <TTarget> TTarget toEntity(Object source, Class<TTarget> targetClass, boolean isSaveNullValue) {
        TTarget t = null;
        try {
            t = (TTarget) targetClass.newInstance();
            fillEntity(source, t, isSaveNullValue);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 将 一个对象里所有的值 填充到另一个 对象
     *
     * @param source    来源对象
     * @param target    目标对象
     * @param <TSource> 来源对象泛型
     * @param <TTarget> 目标对象泛型
     */
    public static <TSource, TTarget> void fillEntity(TSource source, TTarget target) {
        fillEntity(source, target, false);
    }

    /**
     * 将一个对象里所有的值填充到另一个对象
     *
     * @param source          来源对象
     * @param target          目标对象
     * @param isSaveNullValue 空值是否保存
     * @param <TSource>       来源对象泛型
     * @param <TTarget>       目标对象泛型
     */
    public static <TSource, TTarget> void fillEntity(TSource source, TTarget target, boolean isSaveNullValue) {
        // 查找目标的所有字段
        List<Field> targetFieldList = new ArrayList<Field>();
        getFieldList(target.getClass(), targetFieldList);

        for (Field fieldTarget : targetFieldList) {
            Field fieldSource = getField(source.getClass(), fieldTarget.getName());
            if (fieldSource != null) {
                Object value = getObjectFieldValue(source, fieldSource.getName());
                if (value == null && isSaveNullValue) {
                    setObjectFieldValue(target, fieldTarget.getName(), null);
                } else if (value != null) {
                    setObjectFieldValue(target, fieldTarget.getName(), value);
                }
            }
        }
    }

    /**
     * 查找当前类里所有的字段
     *
     * @param classType   被查找的类
     * @param searchSuper 是否查找超类的字段
     * @return 字段集合
     */
    public static Field[] getFields(Class<?> classType, boolean searchSuper) {
        List<Field> fieldList = new ArrayList<Field>();

        getFieldList(classType, fieldList, searchSuper);

        return fieldList.toArray(new Field[fieldList.size()]);
    }

    /**
     * 获取所有字段, 默认查询所有子类
     *
     * @param classType 被查找的类
     * @param fieldList 被填入的字段列表
     */
    public static void getFieldList(Class<?> classType, List<Field> fieldList) {
        getFieldList(classType, fieldList, true);
    }

    /**
     * 获取所有字段
     *
     * @param classType   被查找的类
     * @param fieldList   被填入的字段列表
     * @param searchSuper 是否查找子类
     */
    public static void getFieldList(Class<?> classType, List<Field> fieldList, boolean searchSuper) {
        Field[] fields = classType.getDeclaredFields();
        fieldList.addAll(Arrays.asList(fields));
        Class<?> superClass = classType.getSuperclass();
        if (searchSuper && superClass != null) {
            getFieldList(superClass, fieldList, searchSuper);
        }
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
        } catch (NoSuchFieldException e) {
            Class<?> superClass = classType.getSuperclass();
            if (superClass != null) {
                field = getField(superClass, fieldName);
            }
        }
        return field;
    }

    /**
     * 获取指定字段值
     *
     * @param data      来源对象
     * @param fieldName 字段名
     * @return 指定字段值
     */
    public static Object getObjectFieldValue(Object data, String fieldName) {
        return getObjectFieldValue(data.getClass(), data, fieldName);
    }

    /**
     * 获取指定字段值
     *
     * @param classType 当前类型, 一般为当前类型, 但也有可能是父类型
     * @param data      来源对象
     * @param fieldName 字段名
     * @return 指定字段值
     */
    private static Object getObjectFieldValue(Class<?> classType, Object data, String fieldName) {
        Object value = null;
        Field field = null;
        try {
            field = classType.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        if (field != null) {
            field.setAccessible(true);
            try {
                value = field.get(data);
                FieldMap fieldMap = field.getAnnotation(FieldMap.class);
                if (fieldMap != null && (fieldMap.access() == FieldMap.Access.AUTO || fieldMap.access() == FieldMap.Access.READ_WRITE || fieldMap.access() == FieldMap.Access.READ_ONLY) && fieldMap.converter() != FieldConverter.class) {
                    try {
                        IFieldConverter converter = fieldMap.converter().newInstance();
                        value = converter.getterChange(value);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            if (classType.getSuperclass() != null) {
                value = getObjectFieldValue(classType.getSuperclass(), data, fieldName);
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
    public static void setObjectFieldValue(Object data, String fieldName, Object value) {
        setObjectFieldValue(data.getClass(), data, fieldName, value);
    }

    /**
     * 设置指定字段值
     *
     * @param classType 当前类型, 一般为当前类型, 但也有可能是父类型
     * @param data      目标对象
     * @param fieldName 字段名
     * @param value     字段值
     */
    private static void setObjectFieldValue(Class<?> classType, Object data, String fieldName, Object value) {
        Field field = null;
        try {
            field = classType.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        if (field != null) {
            field.setAccessible(true);
            if (value != null) {
                try {
                    FieldMap fieldMap = field.getAnnotation(FieldMap.class);
                    if (fieldMap != null && (fieldMap.access() == FieldMap.Access.AUTO || fieldMap.access() == FieldMap.Access.READ_WRITE || fieldMap.access() == FieldMap.Access.WRITE_ONLY) && fieldMap.converter() != FieldConverter.class) {
                        try {
                            IFieldConverter converter = fieldMap.converter().newInstance();
                            value = converter.setterChange(value);
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        }
                    }
                    field.set(data, TypeConverter.toChange(value, field.getType()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    field.set(data, null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } else {
            if (classType.getSuperclass() != null) {
                setObjectFieldValue(classType.getSuperclass(), data, fieldName, value);
            }
        }
    }

    /**
     * 比较两个相同类型的对象, 找出值不同的字段, 用于跟踪比较对象的值修改情况
     *
     * @param source    来源对象
     * @param target    目标对象
     * @param <TEntity> 对象泛型
     * @return 比较结果
     */
    public static <TEntity> ObjectDiff compare(TEntity source, TEntity target) {
        return compare(source, target, NORMAL_FIELDS_LIST);
    }

    /**
     * 比较两个相同类型的对象, 找出值不同的字段, 用于跟踪比较对象的值修改情况
     *
     * @param source    来源对象
     * @param target    目标对象
     * @param filter    不参与比较的字段列表
     * @param <TEntity> 对象泛型
     * @return 比较结果
     */
    public static <TEntity> ObjectDiff compare(TEntity source, TEntity target, List<String> filter) {

        Class<?> classType = source.getClass();
        Field[] fields = getFields(classType, true);
        ObjectDiff objectDiff = new ObjectDiff();
        objectDiff.setClassName(classType.getName());
        objectDiff.setClassType(classType);

        for (Field field : fields) {
            if (filter != null && filter.contains(field.getName())) {
                continue;
            }
            //TODO: 获取注解得到字段的 Caption

            Object sourceValue = getObjectFieldValue(source, field.getName());
            Object targetValue = getObjectFieldValue(target, field.getName());

            if (sourceValue != null && !sourceValue.equals(targetValue)) {
                FieldDiff fieldDiff = new FieldDiff(field, sourceValue, targetValue);
                objectDiff.getFields().add(fieldDiff);
            } else if (targetValue != null && !targetValue.equals(sourceValue)) {
                FieldDiff fieldDiff = new FieldDiff(field, sourceValue, targetValue);
                objectDiff.getFields().add(fieldDiff);
            }
        }

        return objectDiff;
    }

}
