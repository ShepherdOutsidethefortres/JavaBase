package com.jdjr.knowledge.test.frame.internal.request;

import com.jdjr.knowledge.test.frame.internal.entity.JsfEntity;
import com.jdjr.knowledge.test.frame.internal.response.Response;

public interface Jsf {
    Response invoke(JsfEntity jsfEntity);
}
