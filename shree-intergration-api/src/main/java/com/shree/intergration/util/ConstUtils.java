package com.shree.intergration.util;

import java.util.HashMap;
import java.util.Map;

public class ConstUtils {

    public static final String STATUS_ERROR = "-1";
    public static final String STATUS_DELETE = "0";
    public static final String STATUS_ACTIVE = "1";

    public static final String DEFAULT_PERMISSION = "system";

    public static final Map<Integer, String> ROOT_PATH = new HashMap<>();

    public static String getPageRootPath(int depth) {
        if (!ROOT_PATH.containsKey(depth)) {
            StringBuffer pathBuilder = new StringBuffer();
            if (depth > 1) {
                for (int i = 1; i < depth; i++) {
                    pathBuilder.append("../");
                }
            } else {
                pathBuilder.append("./");
            }
            ROOT_PATH.put(depth, pathBuilder.toString());

        }
        return ROOT_PATH.get(depth);
    }

}
