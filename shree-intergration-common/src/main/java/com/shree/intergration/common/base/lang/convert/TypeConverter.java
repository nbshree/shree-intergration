package com.shree.intergration.common.base.lang.convert;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * 类型转换器
 */
public class TypeConverter {

    /**
     * 基础类型转换
     *
     * @param source      来源对象
     * @param targetClass 目标类型
     * @param <TSource>   来源对象泛型
     * @param <TTarget>   目标对象泛型
     * @return 目标类型
     * @throws ParseException 转换错误
     */
    public static <TSource, TTarget> TTarget toChange(TSource source, Class<TTarget> targetClass) throws ParseException {
        if (targetClass.equals(source.getClass()) || targetClass.isInstance(source)) {
            return (TTarget) source;
        }

        String temp = "";
        BigDecimal bigDecimal;
        if (source instanceof Double) {
            bigDecimal = new BigDecimal((Double) source);
            temp = bigDecimal.toPlainString();
        } else if (source instanceof Integer) {
            bigDecimal = new BigDecimal((Integer) source);
            temp = bigDecimal.toPlainString();
        } else if (source instanceof Long) {
            bigDecimal = new BigDecimal((Long) source);
            temp = bigDecimal.toPlainString();
        } else {
            temp = String.valueOf(source);
        }

        Object value = null;

        switch (targetClass.getSimpleName().toLowerCase()) {
            case "byte": {
                if (byte.class.equals(targetClass)) {
                    value = Byte.parseByte(temp, 16);
                } else {
                    value = Byte.valueOf(temp, 16);
                }
                break;
            }
            case "byte[]": {
                value = temp.getBytes(Charset.forName("UTF-8"));
                break;
            }
            case "boolean": {
                if (boolean.class.equals(targetClass)) {
                    value = Boolean.parseBoolean(temp);
                } else {
                    value = Boolean.valueOf(temp);
                }
                break;
            }
            case "short": {
                if (short.class.equals(targetClass)) {
                    value = Short.parseShort(temp);
                } else {
                    value = Short.valueOf(temp);
                }
                break;
            }
            case "int":
            case "integer": {
                if (int.class.equals(targetClass)) {
                    value = Integer.parseInt(temp);
                } else {
                    value = Integer.valueOf(temp);
                }
                break;
            }
            case "long": {

                if (source instanceof Date) {
                    value = ((Date) source).getTime();
                } else if (long.class.equals(targetClass)) {
                    value = Long.parseLong(temp);
                } else {
                    value = Long.valueOf(temp);
                }
                break;
            }
            case "float": {
                if (float.class.equals(targetClass)) {
                    value = Float.parseFloat(temp);
                } else {
                    value = Float.valueOf(temp);
                }
                break;
            }
            case "double": {
                if (double.class.equals(targetClass)) {
                    value = Double.parseDouble(temp);
                } else {
                    value = Double.valueOf(temp);
                }
                break;
            }
            case "date": {
                if (source instanceof Long) {
                    value = new Date((Long) source);
                } else if (source instanceof String) {
                    value = CustomDateConverter.toDate(temp);
                } else {
                    temp = String.format("%tF", source) + " " + String.format("%tT", source);
                    value = CustomDateConverter.toDate(temp);
                }
                break;
            }
            case "timestamp": {
                if (source instanceof Long) {
                    value = new Timestamp((Long) source);
                } else if (source instanceof String) {
                    value = CustomDateConverter.toTimestamp((String) source);
                } else {
                    temp = String.format("%tF", source) + " " + String.format("%tT", source);
                    value = CustomDateConverter.toTimestamp(temp);
                }
                break;
            }
            default: {
                // typeof String
                if (source instanceof Date) {
                    value = CustomDateConverter.fromDate((Date) source);
                } else if (source instanceof Timestamp) {
                    value = CustomDateConverter.fromDate((Timestamp) source);
                } else {
                    value = String.valueOf(source);
                }
                break;
            }
        }

        if (value != null) {
            return (TTarget) value;
        } else {
            return null;
        }
    }

    /**
     * 基础类型转换
     *
     * @param source          来源对象
     * @param targetClassName 目标类型名称
     * @param <TSource>       来源对象泛型
     * @param <TTarget>       目标对象泛型
     * @return 目标类型
     * @throws ParseException         转换错误
     * @throws ClassNotFoundException 类型找不到错误
     */
    public static <TSource, TTarget> TTarget toChange(TSource source, String targetClassName) throws ParseException, ClassNotFoundException {
        Class classType = getJavaValueType(targetClassName);
        if (classType.equals(Class.class)) {
            classType = Class.forName(targetClassName);
        }
        return (TTarget) toChange(source, classType);
    }


    /**
     * 按数据类型生成默认数据
     *
     * @param classType 对象类型
     * @param <TValue>  数据类型
     * @return 默认值
     */
    public static <TValue> TValue createDefaultValue(Class<TValue> classType) {
        Object value = null;
        switch (classType.getSimpleName().toLowerCase()) {
            case "byte": {
                value = new Byte("0");
                break;
            }
            case "byte[]": {
                value = new byte[]{0x00, 0x00};
                break;
            }
            case "boolean": {
                value = true;
                break;
            }
            case "short": {
                value = new Short("0");
                break;
            }
            case "int":
            case "integer": {
                value = 0;
                break;
            }
            case "long": {
                value = 0L;
                break;
            }
            case "float": {
                value = 0F;
                break;
            }
            case "double": {
                value = 0D;
                break;
            }
            case "date": {
                value = new Date();
                break;
            }
            case "timestamp": {
                value = new Timestamp(System.currentTimeMillis());
                break;
            }
            default:
                value = "";
                break;
        }
        return (TValue) value;
    }


    /**
     * 通过值类型的名称获取 JAVA 值类型对象
     *
     * @param valueTypeName Boolean, Short, Integer, Long, Float, Double, Byte, Character, Date, String, List, Map
     * @return 指定类型
     */
    public static Class getJavaValueType(String valueTypeName) {
        Class classType = Class.class;
        switch (valueTypeName) {
            case "Boolean":
            case "boolean":
                classType = Boolean.class;
                break;
            case "Short":
            case "short":
                classType = Short.class;
                break;
            case "Integer":
            case "Int":
            case "int":
                classType = Integer.class;
                break;
            case "Long":
            case "long":
                classType = Long.class;
                break;
            case "Float":
            case "float":
                classType = Float.class;
                break;
            case "Double":
            case "double":
                classType = Double.class;
                break;
            case "Byte":
            case "byte":
                classType = Byte.class;
                break;
            case "Character":
            case "Char":
            case "char":
                classType = Character.class;
                break;
            case "Date":
                classType = Date.class;
                break;
            case "List":
                classType = ArrayList.class;
                break;
            case "Map":
            case "HashMap":
                classType = HashMap.class;
                break;
            case "LinkedHashMap":
                classType = LinkedHashMap.class;
                break;
            case "String":
                classType = String.class;
                break;
            default:
                classType = String.class;
                break;
        }
        return classType;
    }

    /**
     * 检查当前类型是不是 JAVA 的自带的基础类型
     *
     * @param className 类型名称
     * @return 校验结果
     */
    public static boolean isJavaClassType(String className) {
        String compareClassName = className.toLowerCase();
        if (compareClassName.startsWith("java.")) {
            return true;
        } else {
            switch (compareClassName) {
                case "boolean":
                case "short":
                case "int":
                case "long":
                case "float":
                case "double":
                case "byte":
                case "char":
                    return true;
                default:
                    return false;
            }
        }
    }
}
