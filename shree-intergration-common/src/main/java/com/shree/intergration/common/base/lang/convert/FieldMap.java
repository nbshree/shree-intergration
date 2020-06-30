package com.shree.intergration.common.base.lang.convert;


import java.lang.annotation.*;

/**
 * 数据字段类型注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldMap {

    String value();

    FieldMap.Access access() default FieldMap.Access.AUTO;

    Class<? extends IFieldConverter> converter() default FieldConverter.class;

    /**
     * 字段权限
     */
    enum Access {
        /**
         * 默认可读可写
         */
        AUTO,
        /**
         * 仅能读取
         */
        READ_ONLY,
        /**
         * 仅能写入
         */
        WRITE_ONLY,
        /**
         * 可读可写
         */
        READ_WRITE;

        Access() {
        }
    }
}
