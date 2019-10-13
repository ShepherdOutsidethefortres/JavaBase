package com.jdjr.knowledge.test.frame.internal.request;

import com.jdjr.knowledge.test.frame.internal.request.impl.HttpImpl;
import com.jdjr.knowledge.test.frame.internal.config.Config;
import com.jdjr.knowledge.test.frame.internal.config.Init.Save;
import com.jdjr.knowledge.test.frame.internal.request.impl.HttpsImpl;
import com.jdjr.knowledge.test.frame.internal.request.impl.JsfImpl;

import java.util.ArrayList;
import java.util.List;

public class Request {
    private Config config;
    private String description;

    private Request(Config config, String description) {
        this.config = config;
        this.description = description;
    }

    public static Request create(Config config, String description) {
        return new Request(config, description);
    }

    public static Request create(String description) {
        return new Request(new Config(), description);
    }

    public Http http() {
        return new HttpImpl(this.config, Save.SINGLE, this.description);
    }

    public Https https() {
        return new HttpsImpl(this.config, Save.SINGLE, this.description);
    }

    public Jsf jsf() {
        return new JsfImpl(this.config, Save.SINGLE, this.description);
    }

}
