package com.jdjr.knowledge.test.frame.internal.request.impl;

import com.jdjr.knowledge.test.frame.internal.config.Config;
import com.jdjr.knowledge.test.frame.internal.entity.HttpEntity;
import com.jdjr.knowledge.test.frame.internal.request.Http;
import com.jdjr.knowledge.test.frame.internal.request.sender.HttpRequestSender;
import com.jdjr.knowledge.test.frame.internal.response.Response;
import com.jdjr.knowledge.test.frame.internal.config.Init.Save;
import com.jdjr.knowledge.test.frame.internal.response.ResultContent;
import com.jdjr.knowledge.test.frame.internal.util.LogUtil;
import com.jdjr.knowledge.test.frame.internal.util.SaveUtil;

public class HttpImpl implements Http {

    private Config config;
    private Save save;
    private String description;

    public HttpImpl(Config config, Save save, String description) {
        this.config = config;
        this.save = save;
        this.description = description;
    }

    public Response post(HttpEntity httpEntity) {
        ResultContent resultContent = (new HttpRequestSender()).executePost(this.config, httpEntity, this.description);
        LogUtil.Http.log(this.config, resultContent);
        SaveUtil.save(this.save, resultContent);
        return (new ResponseImpl(this.config)).response(resultContent.getResponse().getResponseBody(), resultContent.getHeaders());
    }

    public Response get(HttpEntity httpEntity) {
        ResultContent resultContent = (new HttpRequestSender()).executeGet(this.config, httpEntity, this.description);
        LogUtil.Http.log(this.config, resultContent);
        SaveUtil.save(this.save, resultContent);
        return (new ResponseImpl(this.config)).response(resultContent.getResponse().getResponseBody(), resultContent.getHeaders());
    }
}
