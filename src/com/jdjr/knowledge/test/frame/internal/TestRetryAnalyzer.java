package com.jdjr.knowledge.test.frame.internal;

import com.jdjr.knowledge.test.frame.annotation.RetryConfig;
import com.jdjr.knowledge.test.frame.internal.util.LogUtil;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestRetryAnalyzer implements IRetryAnalyzer {
    private int count = 0;
    private int maxRetryTime = 5;

    public TestRetryAnalyzer() {
        this.count = 0;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMaxRetryTime() {
        return this.maxRetryTime;
    }

    public void setMaxRetryTime(int maxRetryTime) {
        this.maxRetryTime = maxRetryTime;
    }

    public boolean retry(ITestResult arg0) {
        RetryConfig retryConfig = (RetryConfig)arg0.getMethod().getConstructorOrMethod().getMethod().getAnnotation(RetryConfig.class);
        if (retryConfig != null) {
            this.maxRetryTime = retryConfig.retryCount();
        }

        if (this.count > 0) {
            LogUtil.info("当前第" + this.count + "次重跑");
            LogUtil.info("最大的重跑次数" + this.maxRetryTime);
        }

        if (this.count < this.maxRetryTime) {
            arg0.setAttribute("RETRY", this.count);
            ++this.count;
            return true;
        } else {
            this.count = 0;
            return false;
        }
    }
}