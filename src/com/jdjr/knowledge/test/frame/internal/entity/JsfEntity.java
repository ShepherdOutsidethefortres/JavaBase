package com.jdjr.knowledge.test.frame.internal.entity;

import java.util.HashMap;
import java.util.Map;
import com.jdjr.knowledge.test.frame.internal.config.Init.Evn;
import com.jdjr.knowledge.test.frame.internal.config.Init.Params;
import com.jdjr.knowledge.test.frame.internal.config.Init.RequestType;

public final class JsfEntity implements Entity {
    final String ip;
    final String jsfRegistry;
    final String interfaceId;
    final String protocol;
    final String alias;
    final String body;
    final String methodName;
    final String info;
    final int timeOut;
    final Evn evn;
    final EntityType entityType;
    final Map<Params, Object> methodParams;
    final Map<String, String> jsfParameter;
    final RequestType requestMethod;

    JsfEntity(JsfEntity.Builder builder) {
        this.entityType = builder.entityType;
        this.jsfRegistry = builder.jsfRegistry;
        this.interfaceId = builder.interfaceId;
        this.protocol = builder.protocol;
        this.alias = builder.alias;
        this.body = builder.body;
        this.timeOut = builder.timeOut;
        this.methodParams = builder.methodParams;
        this.methodName = builder.methodName;
        this.evn = builder.evn;
        this.info = builder.info;
        this.ip = builder.ip;
        this.jsfParameter = builder.jsfParameter;
        this.requestMethod = builder.requestMethod;
    }

    public RequestType getRequestMethod() {
        return this.requestMethod;
    }

    public EntityType getEntityType() {
        return this.entityType;
    }

    public String getIp() {
        return this.ip;
    }

    public String getJsfRegistry() {
        return this.jsfRegistry;
    }

    public String getInterfaceId() {
        return this.interfaceId;
    }

    public String getProtocol() {
        return this.protocol;
    }

    public String getAlias() {
        return this.alias;
    }

    public String getBody() {
        return this.body;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public Evn getEvn() {
        return this.evn;
    }

    public int getTimeOut() {
        return this.timeOut;
    }

    public Map<Params, Object> getMethodParams() {
        return this.methodParams;
    }

    public String getInfo() {
        return this.info;
    }

    public Map<String, String> getJsfParameter() {
        return this.jsfParameter;
    }

    public String toString() {
        return " " + this.info + " ";
    }

    public JsfEntity.Builder newBuilder() {
        return new JsfEntity.Builder(this);
    }

    public static class Builder {
        String ip;
        String jsfRegistry;
        String interfaceId;
        String protocol;
        String alias;
        String body;
        String methodName;
        String info;
        int timeOut;
        Evn evn;
        EntityType entityType;
        Map<Params, Object> methodParams;
        Map<String, String> jsfParameter;
        RequestType requestMethod;

        public Builder() {
            this.entityType = EntityType.JSF;
            this.timeOut = 5000;
            this.jsfRegistry = "i.jsf.jd.com";
            this.protocol = "jsf";
            this.evn = Evn.TEST;
            this.interfaceId = "";
            this.info = "";
            this.ip = "";
            this.jsfParameter = new HashMap();
        }

        public Builder(JsfEntity jsfEntityInfo) {
            this.entityType = jsfEntityInfo.entityType;
            this.jsfRegistry = jsfEntityInfo.jsfRegistry;
            this.interfaceId = jsfEntityInfo.interfaceId;
            this.protocol = jsfEntityInfo.protocol;
            this.alias = jsfEntityInfo.alias;
            this.body = jsfEntityInfo.body;
            this.methodName = jsfEntityInfo.methodName;
            this.timeOut = jsfEntityInfo.timeOut;
            this.methodParams = jsfEntityInfo.methodParams;
            this.evn = jsfEntityInfo.evn;
            this.info = jsfEntityInfo.info;
            this.ip = jsfEntityInfo.ip;
            this.jsfParameter = jsfEntityInfo.jsfParameter;
            this.requestMethod = jsfEntityInfo.requestMethod;
        }

        public JsfEntity.Builder evn(Evn evn) {
            this.evn = evn;
            return this;
        }

        public JsfEntity.Builder requestMethod(RequestType requestMethod) {
            this.requestMethod = requestMethod;
            return this;
        }

        public JsfEntity.Builder jsfRegistry(String jsfRegistry) {
            this.jsfRegistry = jsfRegistry.trim();
            return this;
        }

        public JsfEntity.Builder info(String info) {
            this.info = "  " + info.trim() + "  ";
            return this;
        }

        public JsfEntity.Builder ip(String ip) {
            this.ip = ip;
            return this;
        }

        public JsfEntity.Builder interfaceId(String interfaceId) {
            this.interfaceId = interfaceId.trim();
            return this;
        }

        public JsfEntity.Builder alias(String alias) {
            this.alias = alias.trim();
            return this;
        }

        public JsfEntity.Builder body(String body) {
            this.body = body.trim();
            return this;
        }

        public JsfEntity.Builder timeOut(int timeOut) {
            this.timeOut = timeOut;
            return this;
        }

        public JsfEntity.Builder methodParams(Map<Params, Object> methodParams) {
            this.methodParams = methodParams;
            return this;
        }

        public JsfEntity.Builder methodName(String methodName) {
            this.methodName = methodName.trim();
            return this;
        }

        public JsfEntity.Builder jsfParameter(Map<String, String> jsfParameter) {
            this.jsfParameter = jsfParameter;
            return this;
        }

        public JsfEntity.Builder jsfParameter(String key, String value) {
            if (this.jsfParameter == null) {
                this.jsfParameter = new HashMap();
            }

            this.jsfParameter.put(key, value);
            return this;
        }

        public JsfEntity build() {
            return new JsfEntity(this);
        }
    }
}
