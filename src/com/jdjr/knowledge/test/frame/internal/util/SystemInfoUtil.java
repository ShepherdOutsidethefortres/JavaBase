package com.jdjr.knowledge.test.frame.internal.util;

public class SystemInfoUtil {
    public SystemInfoUtil() {
    }

    public static String user() {
        return System.getProperty("user.name") + "@jd.com";
    }
}
