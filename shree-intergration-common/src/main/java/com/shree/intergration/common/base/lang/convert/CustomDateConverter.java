package com.shree.intergration.common.base.lang.convert;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 传统日期时间的转换工具
 */
public class CustomDateConverter {

    /**
     * 一天的毫秒数
     */
    public static final long DAY = 24L * 60L * 60L * 1000L;

    /**
     * 一周的毫秒数
     */
    public static final long WEEK = 7 * DAY;

    /**
     * 内置自定义事件格式字符串数组
     */
    private static final DateFormat[] CUSTOM_DATE_FORMATS;

    /**
     * 默认时区
     */
    private static final TimeZone TIMEZONE = TimeZone.getDefault(); //$NON-NLS-1$

    /**
     * 将日期字符串转为 Date 对象
     *
     * @param dateString 日期字符串
     * @return 转换的Date对象
     */
    public static Date toDate(String dateString) {

        // Return in case the string date is not set
        if (dateString == null || dateString.length() == 0) {
            return null;
        }

        Date result = null;
        dateString = dateString.trim();
        if (dateString.length() > 10) {

            // Open: deal with +4:00 (no zero before hour)
            if ((dateString.substring(dateString.length() - 5).indexOf("+") == 0 || dateString.substring(dateString.length() - 5).indexOf("-") == 0) && dateString.substring(dateString.length() - 5).indexOf(":") == 2) {
                //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                String sign = dateString.substring(dateString.length() - 5, dateString.length() - 4);
                dateString = dateString.substring(0, dateString.length() - 5) + sign + "0" + dateString.substring(dateString.length() - 4);
                //$NON-NLS-1$
            }

            String dateEnd = dateString.substring(dateString.length() - 6);

            /*
             * try to deal with -05:00 or +02:00 at end of date replace with -0500 or
             * +0200
             */
            if ((dateEnd.indexOf("-") == 0 || dateEnd.indexOf("+") == 0) && dateEnd.indexOf(":") == 3) {
                //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
                if (!"GMT".equals(dateString.substring(dateString.length() - 9, dateString.length() - 6))) {
                    //$NON-NLS-1$
                    String oldDate = dateString;
                    String newEnd = dateEnd.substring(0, 3) + dateEnd.substring(4);
                    dateString = oldDate.substring(0, oldDate.length() - 6) + newEnd;
                }
            }
        }

        /* Try to parse the date */
        int i = 0;
        while (i < CUSTOM_DATE_FORMATS.length) {
            try {

                /*
                 * This Block needs to be synchronized, because the parse-Method in
                 * SimpleDateFormat is not Thread-Safe.
                 */
                synchronized (CUSTOM_DATE_FORMATS[i]) {
                    return CUSTOM_DATE_FORMATS[i].parse(dateString);
                }
            } catch (ParseException e) {
                i++;
            } catch (NumberFormatException e) {
                i++;
            }
        }
        return result;
    }

    /**
     * 将日期字符串转为 Timestamp 对象
     *
     * @param dateString 日期字符串
     * @return 转换的时间戳对象
     */
    public static Timestamp toTimestamp(String dateString) {
        Date date = toDate(dateString);
        return new Timestamp(date.getTime());
    }

    /**
     * 将 Date 对象转为指定格式的 Date 对象
     *
     * @param date   日期对象
     * @param format 格式化字符串
     * @return 指定格式的 Date对象
     */
    public static Date formatDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateText = sdf.format(date);
        try {
            return sdf.parse(dateText);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将 Date 对象转为 yyyy-MM-dd HH:mm:ss 的日期字符串
     *
     * @param date 日期对象
     * @return 格式化的日期字符串
     */
    public static String fromDate(Date date) {
        return fromDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 将 Date 对象转为 指定格式 的日期字符串
     *
     * @param date   日期对象
     * @param format 格式化字符串
     * @return 格式化的日期字符串
     */
    public static String fromDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 将 Timestamp 对象转为 yyyy-MM-dd HH:mm:ss 的日期字符串
     *
     * @param timestamp 时间戳对象
     * @return 格式化的日期字符串
     */
    public static String fromTimestamp(Timestamp timestamp) {
        return fromTimestamp(timestamp, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 将 Timestamp 对象转为 指定格式 的日期字符串
     *
     * @param timestamp 时间戳对象
     * @param format    格式化字符串
     * @return 格式化的日期字符串
     */
    public static String fromTimestamp(Timestamp timestamp, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(timestamp);
    }

    /**
     * 获取当前系统日期 yyyy-MM-dd 00:00:00
     *
     * @return 当前日期的 Date 对象
     */
    public static Date nowDate() {
        return formatDate(new Date(), "yyyy-MM-dd");
    }

    /**
     * 获取当前系统时间, 并指定时间格式
     *
     * @param format 时间格式
     * @return 当前时间的 Date 对象
     */
    public static Date nowDateTime(String format) {
        return formatDate(new Date(), format);
    }

    /**
     * 获取当前系统时间 yyyy-MM-dd HH24:mi:ss
     *
     * @return 当前时间的 Date 对象
     */
    public static Date nowDateTime() {
        return new Date();
    }

    /**
     * 获取当前系统时间
     *
     * @return 当前时间的 Timestamp 对象
     */
    public static Timestamp nowTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 获取当前系统时间
     *
     * @return 当前时间的 Calendar 对象
     */
    public static Calendar nowCalendar() {
        return Calendar.getInstance();
    }

    /**
     * 获取日历板的时间
     *
     * @param calendar 日历板
     * @return 日期对象
     */
    public static Date getDate(Calendar calendar) {
        return calendar.getTime();
    }

    /**
     * 获取指定时间的日历板类
     *
     * @param date 日期对象
     * @return 日历板
     */
    public static Calendar getCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 获取指定时间的日历板类
     *
     * @param timeMillis 时间毫秒
     * @return 日历板
     */
    public static Calendar getCalendar(long timeMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeMillis);
        return calendar;
    }

    /* Initialize the array of common date formats and formatter */
    static {
        /* Create Date Formats */
        final String[] possibleDateFormats = {
                /* Simple Date Format */"yyyy-MM-dd HH:mm:ss",
                /* Simple Date Format */"yyyy-MM-dd",
                /* Simple Date Format */"MMM dd, yyyy",
                /* ISO 8601 */"yyyy-MM-dd'T'HH:mm:ssz",
                /* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ssZ",
                /* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ss'Z'",
                /* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:sszzzz",
                /* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ss z",
                /* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ss.SSSz",
                /* ISO 8601 slightly modified */"yyyy-MM-dd'T'HHmmss.SSSz",
                /* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ss",
                /* ISO 8601 w/o seconds */"yyyy-MM-dd'T'HH:mmZ",
                /* ISO 8601 w/o seconds */"yyyy-MM-dd'T'HH:mm'Z'",
                /* RFC 1123 with 2-digit Year */"EEE, dd MMM yy HH:mm:ss z",
                /* RFC 1123 with 4-digit Year */"EEE, dd MMM yyyy HH:mm:ss z",
                /* RFC 1123 with no Timezone */"EEE, dd MMM yy HH:mm:ss",
                /* RFC 1123 with no Seconds */"EEE, dd MMM yy HH:mm z",
                /* RFC 1123 with no Day */"dd MMM yy HH:mm:ss z",
                /* RFC 1123 with no Day or Seconds */"dd MMM yy HH:mm z",
                /* RFC 1123 without Day Name */"dd MMM yyyy HH:mm:ss z",
                /* RFC 1123 without Day Name and Seconds */"dd MMM yyyy HH:mm z",
                /* Variant of RFC 1123 */"EEE, MMM dd yy HH:mm:ss",
                /* Variant of RFC 1123 */"EEE dd MMM yyyy HH:mm:ss"
        };

        /* Create the dateformats */
        CUSTOM_DATE_FORMATS = new SimpleDateFormat[possibleDateFormats.length];

        for (int i = 0; i < possibleDateFormats.length; i++) {
            CUSTOM_DATE_FORMATS[i] = new SimpleDateFormat(possibleDateFormats[i]);
            //CUSTOM_DATE_FORMATS[i].setTimeZone(TIMEZONE);
        }
    }
}
