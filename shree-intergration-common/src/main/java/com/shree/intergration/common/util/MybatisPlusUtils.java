package com.shree.intergration.common.util;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MybatisPlusUtils {

    private static final String COLUMN_STATUS = "STATUS";
    private static final String STATUS_OK = "1";

    /**
     * 根据查询实体获取MybatisPlus的查询Wrapper
     *
     * @param searchEntity 查询实体
     * @param <T1>         查询实体类型
     * @param <T2>         数据库实体类型
     * @return 查询Wrapper
     */
    public static <T1, T2> QueryWrapper<T1> getQueryWrapper(T2 searchEntity) {
        QueryWrapper<T1> wrapper = new QueryWrapper<>();

        //改成获取所有父类和子类的private、protect、public方法
        List<Field> fields = new ArrayList<>();
        Class tempClass = searchEntity.getClass();
        while (tempClass !=null){
            fields.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            tempClass = tempClass.getSuperclass();
        }
        //只取当前类字段
//        Field[] fields = searchEntity.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object value = null;
            try {
                field.setAccessible(true);
                value = field.get(searchEntity);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            // 过滤NULL值的条件拼接
            if (value != null) {
                String fieldName = field.getName();
                String columnName = fieldName;
                TableField tableField = field.getAnnotation(TableField.class);
                if (tableField != null) {
                    columnName = tableField.value();
                }
                if (value instanceof String) {
                    String valueText = (String) value;
                    // 过滤空值的查询条件拼接
                    if (!"".equals(valueText)) {
                        if (valueText.contains("%")) {
                            // 包含
                            wrapper.like(columnName, valueText);
                        } else {
                            // 等于
                            wrapper.eq(columnName, valueText);
                        }
                    }
                } else if (fieldName.endsWith("Start")) {
                    // 大于等于
                    wrapper.ge(columnName, value);
                } else if (fieldName.endsWith("End")) {
                    // 小于等于
                    wrapper.le(columnName, value);
                } else {
                    // 等于
                    wrapper.eq(columnName, value);
                }
            }
        }
        return wrapper;
    }

    /**
     * 获取表的标准基础查询Wrapper（默认Status为1）
     *
     * @param <T> 表实体
     * @return 查询Wrapper
     */
    public static <T> QueryWrapper<T> getBaseQuery() {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.eq(COLUMN_STATUS, STATUS_OK);
        return wrapper;
    }
}
