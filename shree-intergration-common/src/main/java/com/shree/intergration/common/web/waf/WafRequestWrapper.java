package com.shree.intergration.common.web.waf;


import com.shree.intergration.common.web.util.WafUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Request请求攻击过滤器
 */
public class WafRequestWrapper extends HttpServletRequestWrapper {

    private boolean filterXSS = true;
    private boolean filterSQL = true;

    public WafRequestWrapper(HttpServletRequest request, boolean filterXSS, boolean filterSQL) {
        super(request);
        this.filterXSS = filterXSS;
        this.filterSQL = filterSQL;
    }

    public WafRequestWrapper(HttpServletRequest request) {
        this(request, true, true);
    }

    /**
     * @param parameter 过滤参数
     * @return 参数过滤
     */
    @Override
    public String getParameter(String parameter) {
        //return filterParamString(super.getParameter(parameter));
        String value = super.getParameter(filterParamString(parameter));
        if (value != null) {
            value = filterParamString(value);
        }
        return value;
    }

    /**
     * @param parameter 过滤参数
     * @return 数组参数过滤
     */
    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }

        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = filterParamString(values[i]);
        }

        return encodedValues;
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Map getParameterMap() {
        Map<String, String[]> primary = super.getParameterMap();
        Map<String, String[]> result = new HashMap<String, String[]>(primary.size());
        for (Map.Entry<String, String[]> entry : primary.entrySet()) {
            result.put(entry.getKey(), filterEntryString(entry.getValue()));
        }
        return result;

    }

    /**
     * @param name 过滤内容
     * @return 请求头过滤
     */
    @Override
    public String getHeader(String name) {
        //return filterParamString(super.getHeader(name));
        String value = super.getHeader(filterParamString(name));
        if (value != null) {
            value = filterParamString(value);
        }
        return value;
    }

    /**
     * @return Cookie内容过滤
     */
    @Override
    public Cookie[] getCookies() {
        Cookie[] existingCookies = super.getCookies();
        if (existingCookies != null) {
            for (int i = 0; i < existingCookies.length; ++i) {
                Cookie cookie = existingCookies[i];
                cookie.setValue(filterParamString(cookie.getValue()));
            }
        }
        return existingCookies;
    }

    @Override
    public Object getAttribute(String name) {
        Object value = super.getAttribute(filterParamString(name));
        if (value instanceof String) {
            return filterParamString(value.toString());
        }
        return value;
    }

    @Override
    public String getRequestURI() {
        String strVal = "";
        try {
            strVal = URLDecoder.decode(super.getRequestURI(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String value = filterParamString(strVal);
        if (value != null) {
            value = filterParamString(value);
        }
        return value;
    }

    protected String[] filterEntryString(String[] rawValue) {
        for (int i = 0; i < rawValue.length; i++) {
            rawValue[i] = filterParamString(rawValue[i]);
        }
        return rawValue;
    }

    /**
     * @param rawValue 待处理内容
     * @return 过滤字符串内容
     */
    protected String filterParamString(String rawValue) {
        if (rawValue == null) {
            return null;
        }
        String tmpStr = rawValue;
        if (this.filterXSS) {
            tmpStr = WafUtils.stripXss(rawValue);
        }
        if (this.filterSQL) {
            tmpStr = WafUtils.stripSqlInjection(tmpStr);
        }
        return tmpStr;
    }
}

