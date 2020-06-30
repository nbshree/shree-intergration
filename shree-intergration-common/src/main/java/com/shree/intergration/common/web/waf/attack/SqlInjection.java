package com.shree.intergration.common.web.waf.attack;


/**
 * SQL注入攻击
 */
public class SqlInjection  {

    /**
     * @param value 待处理内容
     * @return SQL注入剥离内容
     */
    public String strip(String value) {

        //剥离SQL注入部分代码
        return value.replaceAll("('.+--)|(--)|(\\|)|(%7C)", "");
    }
}
