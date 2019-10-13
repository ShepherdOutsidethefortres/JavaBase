package com.jdjr.knowledge.test.frame.internal.entity;

import java.util.HashMap;
import java.util.Map;
import com.jdjr.knowledge.test.frame.internal.config.Init.RequestType;
import com.jdjr.knowledge.test.frame.internal.util.EntityUtil;

public final class HttpEntity implements Entity{
    final boolean onGate;
    final String url;
    final String ip;
    final String body;
    final String info;
    final Map<String, ?> parameter;
    final Map<String, String> header;
    final Map<String, String> form;
    final EntityType entityType;
    final RequestType requestMethod;

    HttpEntity(HttpEntity.Builder builder) {
        this.entityType = builder.entityType;
        this.onGate = builder.onGate;
        this.url = builder.url;
        this.ip = builder.ip;
        this.body = builder.body;
        this.parameter = builder.parameter;
        this.header = builder.header;
        this.form = builder.form;
        this.info = builder.info;
        this.requestMethod = builder.requestMethod;
    }

    public EntityType getEntityType() {
        return this.entityType;
    }

    public boolean isOnGate() {
        return this.onGate;
    }

    public String getUrl() {
        return this.url;
    }

    public String getIp() {
        return this.ip;
    }

    public String getBody() {
        return this.body;
    }

    public String getInfo() {
        return this.info;
    }

    public RequestType getRequestMethod() {
        return this.requestMethod;
    }

    public Map<String, ?> getParameter() {
        return (Map)(this.parameter == null ? new HashMap() : this.parameter);
    }

    public Map<String, String> getHeader() {
        return (Map)(this.header == null ? new HashMap() : this.header);
    }

    public Map<String, String> getForm() {
        return (Map)(this.form == null ? new HashMap() : this.form);
    }

    public String getHeader(String name) {
        return this.header == null ? "" : (String)this.header.get(name);
    }

    public String toString() {
        return " " + this.info + " ";
    }

    public HttpEntity.Builder newBuilder() {
        return new HttpEntity.Builder(this);
    }

    public static class Builder {
        boolean onGate;
        String url;
        String ip;
        String body;
        String info;
        Map<String, ?> parameter;
        Map<String, String> header;
        Map<String, String> form;
        EntityType entityType;
        RequestType requestMethod;

        public Builder() {
            this.entityType = EntityType.HTTP;
            this.onGate = false;
            this.info = "";
            this.ip = "";
        }

        public Builder(HttpEntity httpEntityInfo) {
            this.entityType = httpEntityInfo.entityType;
            this.onGate = httpEntityInfo.onGate;
            this.url = httpEntityInfo.url;
            this.ip = httpEntityInfo.ip;
            this.body = httpEntityInfo.body;
            this.parameter = httpEntityInfo.parameter;
            this.header = httpEntityInfo.header;
            this.form = httpEntityInfo.form;
            this.info = httpEntityInfo.info;
            this.requestMethod = httpEntityInfo.requestMethod;
        }

        public HttpEntity.Builder onGate(boolean onGate) {
            this.onGate = onGate;
            return this;
        }

        public HttpEntity.Builder url(String url) {
            this.url = url.trim();
            return this;
        }

        public HttpEntity.Builder requestMethod(RequestType requestMethod) {
            this.requestMethod = requestMethod;
            return this;
        }

        public HttpEntity.Builder ip(String ip) {
            this.ip = ip.trim();
            return this;
        }

        public HttpEntity.Builder info(String info) {
            this.info = info.trim();
            return this;
        }

        public HttpEntity.Builder body(String body) {
            this.body = body.trim();
            return this;
        }

        public HttpEntity.Builder parameter(Map<String, ?> parameter) {
            this.parameter = parameter;
            return this;
        }

        public HttpEntity.Builder header(Map<String, String> header) {
            this.header = EntityUtil.encodeHeaders(header);
            return this;
        }

        public HttpEntity.Builder form(Map<String, String> form) {
            this.form = form;
            return this;
        }

        public HttpEntity.Builder header(String name, String value) {
            if (this.header == null) {
                this.header = new HashMap();
            }

            if (name.equals("Cookie")) {
                this.header.put(name, EntityUtil.encodeHeader(value));
            } else {
                this.header.put(name, value);
            }

            return this;
        }

        public HttpEntity build() {
            return new HttpEntity(this);
        }
    }
}
