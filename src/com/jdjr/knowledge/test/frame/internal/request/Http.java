package com.jdjr.knowledge.test.frame.internal.request;

import com.jdjr.knowledge.test.frame.internal.entity.HttpEntity;
import com.jdjr.knowledge.test.frame.internal.response.Response;

public interface Http {
    Response post(HttpEntity httpEntity);

    Response get(HttpEntity httpEntity);
}
