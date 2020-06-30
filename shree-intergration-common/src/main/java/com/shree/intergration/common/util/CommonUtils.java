package com.shree.intergration.common.util;

import java.util.UUID;

public class CommonUtils {

    private static final String STRING_BAR = "-";
    private static final String STRING_EMPTY = "";

    public static String randomUUID() {
        return randomUUID(true);
    }

    public static String randomUUID(boolean isSimple) {
        String uuid = UUID.randomUUID().toString();
        if (isSimple) {
            return uuid.replaceAll(STRING_BAR, STRING_EMPTY);
        } else {
            return uuid;
        }
    }
}
