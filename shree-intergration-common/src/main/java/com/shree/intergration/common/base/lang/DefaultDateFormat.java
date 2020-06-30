package com.shree.intergration.common.base.lang;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class DefaultDateFormat {
    /**
     * 默认日期时间格式字符串yyyy-MM-dd HH:mm:ss
     */
    public static final String DATE_TIME_FORMAT_STR = "yyyy-MM-dd HH:mm:ss";

    /**
     * 默认日期格式字符串yyyy-MM-dd
     */
    public static final String DATE_FORMAT_STR = "yyyy-MM-dd";

    /**
     * 默认时间格式字符串HH:mm:ss
     */
    public static final String TIME_FORMAT_STR = "HH:mm:ss";

    /**
     * 默认日期时间简单格式
     */
    public static final SimpleDateFormat DATE_TIME_SIMPLE_FORMAT = new SimpleDateFormat(DATE_TIME_FORMAT_STR);

    /**
     * 默认日期简单格式
     */
    public static final SimpleDateFormat DATE_SIMPLE_FORMAT = new SimpleDateFormat(DATE_FORMAT_STR);

    /**
     * 默认时间简单格式
     */
    public static final SimpleDateFormat TIME_SIMPLE_FORMAT = new SimpleDateFormat(TIME_FORMAT_STR);

    /**
     * 默认日期时间格式
     */
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_STR);

    /**
     * 默认日期格式
     */
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT_STR);

    /**
     * 默认时间格式
     */
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT_STR);
}
