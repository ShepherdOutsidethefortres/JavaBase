package com.jdjr.knowledge.test.frame.internal.config;

public interface Init {

    public static enum RequestType {
        POST,
        GET,
        INVOKE;

        private RequestType() {
        }
    }

    public static enum ResponseType {
        JSON,
        HTML,
        TEXT,
        XML;

        private ResponseType() {
        }
    }

    public static enum Params {
        VALUE,
        CLASS;

        private Params() {
        }
    }

    public static enum FunctionId {
        HTTP,
        JSF;

        private FunctionId() {
        }
    }

    public static enum Save {
        SINGLE,
        SCENE;

        private Save() {
        }
    }

    public static enum Evn {
        TEST,
        ONLINE;

        private Evn() {
        }
    }

    public static enum Log {
        NONE,
        ALL,
        SIMPLE;

        private Log() {
        }
    }
}
