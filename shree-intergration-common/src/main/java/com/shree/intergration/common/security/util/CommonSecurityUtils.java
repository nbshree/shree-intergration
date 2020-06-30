package com.shree.intergration.common.security.util;

import com.shree.intergration.common.base.singleton.Singleton;
import com.shree.intergration.common.security.messagedigest.MD5;
import com.shree.intergration.common.security.messagedigest.SHA1;

import java.nio.charset.StandardCharsets;

public class CommonSecurityUtils {

    private static final String DEFAULT_ILD_SIGN = "20!^)50$DeV2AuthBaseS1gn";
    private static final String DEFAULT_ILD_TOKEN = "20!^1&)8DeV2AuthBaset0k1!";

    public static String getPassword(String userName, String userPwd) {
        StringBuffer token = new StringBuffer();
        char[] cstrArray = Singleton.getInstance(MD5.class).getDigest(userName + "_" + userPwd + "_" + DEFAULT_ILD_SIGN, StandardCharsets.US_ASCII, false).toCharArray();
        char[] bstrArray = Singleton.getInstance(MD5.class).getDigest(DEFAULT_ILD_TOKEN + "_" + DEFAULT_ILD_SIGN.substring(5, 20), StandardCharsets.UTF_16LE, false).toCharArray();
        for (int i = 0; i < cstrArray.length; i++) {
            if (cstrArray[i] > bstrArray[i]) {
                token.append(cstrArray[i]);
            } else {
                token.append(bstrArray[i]);
            }
        }
        String tokenString = token.toString();
        return Singleton.getInstance(SHA1.class).getDigest(token.toString(), StandardCharsets.UTF_16LE, false);
    }
}
