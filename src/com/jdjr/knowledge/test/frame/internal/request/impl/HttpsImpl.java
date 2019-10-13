package com.jdjr.knowledge.test.frame.internal.request.impl;

import com.jdjr.knowledge.test.frame.internal.config.Config;
import com.jdjr.knowledge.test.frame.internal.config.Init;
import com.jdjr.knowledge.test.frame.internal.config.Init.Save;
import com.jdjr.knowledge.test.frame.internal.entity.HttpEntity;
import com.jdjr.knowledge.test.frame.internal.request.Https;
import com.jdjr.knowledge.test.frame.internal.response.Response;

public class HttpsImpl implements Https {
    private Config config;
    private Save save;
    private String description;

    public HttpsImpl(Config config, Save save, String description) {
        this.config = config;
        this.save = save;
        this.description = description;
    }

    public Response post(HttpEntity httpEntity) {
        return (new HttpImpl(this.config, this.save, this.description)).post(httpEntity);
    }

    public Response get(HttpEntity httpEntity) {
        return (new HttpImpl(this.config, this.save, this.description)).get(httpEntity);
    }
}
