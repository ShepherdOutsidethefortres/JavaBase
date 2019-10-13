package com.jdjr.knowledge.test.frame.internal.util;

import org.apache.commons.lang3.StringUtils;

public class RunExceptionUtil extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String[] err = null;

    String getErr() {
        return StringUtils.join(this.err, ";");
    }

    public RunExceptionUtil(String... err) {
        this.err = err;
        String[] var2 = err;
        int var3 = err.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            String e = var2[var4];
            LogUtil.error(e);
        }

    }

    public Throwable fillInStackTrace() {
        return this;
    }
}
