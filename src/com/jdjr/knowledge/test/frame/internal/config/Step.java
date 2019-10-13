package com.jdjr.knowledge.test.frame.internal.config;

public interface Step {
    public static enum JSF implements Step {
        INVOKE;

        private JSF() {
        }
    }

    public static enum HTTPS implements Step {
        POST,
        GET;

        private HTTPS() {
        }
    }

    public static enum HTTP implements Step {
        POST,
        GET;

        private HTTP() {
        }
    }
}
