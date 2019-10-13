package com.jdjr.knowledge.test.frame.internal.constant;

public abstract class RequestConstant {
    public RequestConstant() {
    }

    public abstract static class ReplaceToken {
        public static final String OPEN_TOKEN = "${";
        public static final String CLOSE_TOKEN = "}";

        public ReplaceToken() {
        }
    }

    public abstract static class Header {
        public static final String FORM_CONTENTTYPE = "application/x-www-form-urlencoded;charset=UTF-8";
        public static final String JSON_CONTENTTYPE = "application/json;charset=UTF-8";

        public Header() {
        }
    }

    public abstract static class Http {
        public static final String HTTP_PREFIX = "http://";
        public static final String HTTPS_PREFIX = "https://";
        public static final String ONGATE_SUFFIX = "?";
        public static final String BODY = "body";

        public Http() {
        }
    }

    public abstract static class Client {
        public static final String ANDROID = "android";
        public static final String APPLE = "apple";
        public static final String IPAD = "ipad";
        public static final String M = "m";
        public static final String MP = "mp";
        public static final String PC = "pc";

        public Client() {
        }
    }
}
