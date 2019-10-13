package com.jdjr.knowledge.test.frame.internal.request.impl;

import com.jdjr.knowledge.test.frame.internal.config.Config;
import com.jdjr.knowledge.test.frame.internal.entity.JsfEntity;
import com.jdjr.knowledge.test.frame.internal.request.Jsf;
import com.jdjr.knowledge.test.frame.internal.config.Init.Save;
import com.jdjr.knowledge.test.frame.internal.request.sender.JsfRequestSender;
import com.jdjr.knowledge.test.frame.internal.response.Response;
import com.jdjr.knowledge.test.frame.internal.response.ResultContent;
import com.jdjr.knowledge.test.frame.internal.util.LogUtil;
import com.jdjr.knowledge.test.frame.internal.util.SaveUtil;

import java.util.HashMap;

public class JsfImpl implements Jsf {
    private Config config;
    private Save save;
    private String description;

    public JsfImpl(Config config, Save save, String description) {
        this.config = config;
        this.save = save;
        this.description = description;
    }

    public Response invoke(JsfEntity entity) {
        ResultContent resultContent = (new JsfRequestSender()).execute(this.config, entity, this.description);
        LogUtil.Jsf.log(this.config, resultContent);
        SaveUtil.save(this.save, resultContent);
        return (new ResponseImpl(this.config)).response(resultContent.getResponse().getResponseBody(), (HashMap)null);
    }
}
