package com.jdjr.knowledge.test.frame.internal.response;

import java.util.HashMap;
import java.util.Map;
import com.jdjr.knowledge.test.frame.internal.config.Init.Evn;
import com.jdjr.knowledge.test.frame.internal.config.Init.RequestType;
import com.jdjr.knowledge.test.frame.internal.entity.Entity.EntityType;

public final class ResultContent {
    final ResultContent.Request request;
    final ResultContent.Response response;
    final String description;
    final HashMap<String, String> headers;

    ResultContent(ResultContent.Builder builder) {
        this.request = builder.request;
        this.response = builder.response;
        this.description = builder.description;
        this.headers = builder.headers;
    }

    public ResultContent.Response getResponse() {
        return this.response;
    }

    public ResultContent.Request getRequest() {
        return this.request;
    }

    public String getDescription() {
        return this.description;
    }

    public HashMap<String, String> getHeaders() {
        return this.headers;
    }

    public ResultContent.Builder newBuilder() {
        return new ResultContent.Builder(this);
    }

    public static class Request {
        private boolean onGate;
        private String ip;
        private String content;
        private String info;
        private String url;
        private Evn evn;
        private Map<String, ?> parameter;
        private Map<String, String> header;
        private EntityType entityType;
        private RequestType requestMethod;

        public Request() {
        }

        public boolean isOnGate() {
            return this.onGate;
        }

        public void setOnGate(boolean onGate) {
            this.onGate = onGate;
        }

        public String getIp() {
            return this.ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getContent() {
            return this.content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getInfo() {
            return this.info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Evn getEvn() {
            return this.evn;
        }

        public void setEvn(Evn evn) {
            this.evn = evn;
        }

        public Map<String, ?> getParameter() {
            return this.parameter;
        }

        public void setParameter(Map<String, ?> parameter) {
            this.parameter = parameter;
        }

        public Map<String, String> getHeader() {
            return this.header;
        }

        public void setHeader(Map<String, String> header) {
            this.header = header;
        }

        public EntityType getEntityType() {
            return this.entityType;
        }

        public void setEntityType(EntityType entityType) {
            this.entityType = entityType;
        }

        public RequestType getRequestMethod() {
            return this.requestMethod;
        }

        public void setRequestMethod(RequestType requestMethod) {
            this.requestMethod = requestMethod;
        }
    }

    public static class Response {
        boolean status;
        long responseTime;
        long executeTime;
        String responseBody;
        private String responseType;

        public Response() {
        }

        public boolean isStatus() {
            return this.status;
        }

        public void setResponseTime(long responseTime) {
            this.responseTime = responseTime;
        }

        public void setExecuteTime(long executeTime) {
            this.executeTime = executeTime;
        }

        public void setResponseBody(String responseBody) {
            this.responseBody = responseBody;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public long getResponseTime() {
            return this.responseTime;
        }

        public long getExecuteTime() {
            return this.executeTime;
        }

        public String getResponseBody() {
            return this.responseBody;
        }

        public String getResponseType() {
            return this.responseType;
        }

        public void setResponseType(String responseType) {
            this.responseType = responseType;
        }
    }

    public static class Builder {
        ResultContent.Request request;
        ResultContent.Response response;
        String description;
        HashMap<String, String> headers;

        public Builder() {
        }

        Builder(ResultContent resultContent) {
            this.response = resultContent.response;
            this.request = resultContent.request;
            this.description = resultContent.description;
            this.headers = resultContent.headers;
        }

        public ResultContent.Builder response(ResultContent.Response response) {
            this.response = response;
            return this;
        }

        public ResultContent.Builder request(ResultContent.Request request) {
            this.request = request;
            return this;
        }

        public ResultContent.Builder description(String description) {
            this.description = description;
            return this;
        }

        public ResultContent.Builder headers(HashMap<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public ResultContent build() {
            return new ResultContent(this);
        }
    }
}
