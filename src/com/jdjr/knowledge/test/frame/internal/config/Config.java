package com.jdjr.knowledge.test.frame.internal.config;

import com.jdjr.knowledge.test.frame.internal.config.Init.Log;
import com.jdjr.knowledge.test.frame.internal.config.Init.ResponseType;

public class Config {
    private Log log;
    private ResponseType responseType;

    public Config() {
        this.responseType = ResponseType.JSON;
        this.log = Log.NONE;
    }

    public Config Log(Log log) {
        this.log = log;
        return this;
    }

    public Config ResponseType(ResponseType responseType) {
        this.responseType = responseType;
        return this;
    }

    public Log getLog() {
        return this.log;
    }

    public ResponseType getResponseType() {
        return this.responseType;
    }
}
