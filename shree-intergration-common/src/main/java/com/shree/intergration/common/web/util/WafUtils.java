package com.shree.intergration.common.web.util;

import com.shree.intergration.common.web.waf.attack.SqlInjection;
import com.shree.intergration.common.web.waf.attack.XSS;

/**
 * WebApplicationFirewall便捷工具
 *
 * @author Darkprayer
 */
public class WafUtils {

    /**
     * 过滤XSS脚本内容
     *
     * @param value 待处理内容
     * @return 过滤后内容
     */
    public static String stripXss(String value) {
        if (value == null) {
            return null;
        }

        return new XSS().strip(value);
    }

    /**
     * 过滤SQL注入内容
     *
     * @param value 待处理内容
     * @return 过滤后内容
     */
    public static String stripSqlInjection(String value) {
        if (value == null) {
            return null;
        }

        return new SqlInjection().strip(value);
    }

    /**
     * 过滤SQL/XSS注入内容
     *
     * @param value 待处理内容
     * @return 过滤后内容
     */
    public static String stripSqlXss(String value) {
        if (value == null) {
            return null;
        }

        return stripXss(stripSqlInjection(value));
    }
}
