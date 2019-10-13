package com.jdjr.knowledge.test.frame.internal.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogStashUtil {
    private static Logger LOG_STASH = LoggerFactory.getLogger(LogStashUtil.class);

    public LogStashUtil() {
    }

    public static Logger get() {
        return LOG_STASH;
    }

    public static void stashInfo(String info) {
        LOG_STASH.info(info);
    }
}
