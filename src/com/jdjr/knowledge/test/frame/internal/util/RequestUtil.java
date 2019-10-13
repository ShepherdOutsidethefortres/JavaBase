package com.jdjr.knowledge.test.frame.internal.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RequestUtil {
    private static final MediaType JSON = MediaType.parse("application/json;charset=UTF-8");
    private static ObjectMapper objectMapper;

    public RequestUtil() {
    }

    public static RequestUtil.KnowledgeTaskInfo validTaskId(String id) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        RequestUtil.KnowledgeTaskInfo knowledgeTaskInfo = new RequestUtil.KnowledgeTaskInfo();

        try {
            Map<String, String> userInfo = new HashMap();
            userInfo.put("task_id", id);
            userInfo.put("mail", SystemInfoUtil.user());
            OkHttpClient client = (new OkHttpClient()).newBuilder().connectTimeout(60L, TimeUnit.SECONDS).readTimeout(60L, TimeUnit.SECONDS).writeTimeout(60L, TimeUnit.SECONDS).build();
            Request request = (new Request.Builder()).cacheControl(CacheControl.FORCE_NETWORK).url(PropertiesUtil.data("dhf.web.task.valid.url")).post(RequestBody.create(JSON, objectMapper.writeValueAsString(userInfo))).build();
            String response = client.newCall(request).execute().body().string();
            knowledgeTaskInfo = (RequestUtil.KnowledgeTaskInfo)objectMapper.readValue(response, RequestUtil.KnowledgeTaskInfo.class);
            return knowledgeTaskInfo;
        } catch (IOException var6) {
            knowledgeTaskInfo.setSuccess(false);
            return knowledgeTaskInfo;
        }
    }

    public static class KnowledgeTaskInfo {
        private int type;
        private String name;
        private String dev_erp;
        private String dev_name;
        private String tester_erp;
        private String tester_name;
        private String module_name;
        private String version;
        private String time;
        private String tester_group;
        private String tester_org;
        private String inflence;
        private boolean success;

        public KnowledgeTaskInfo() {
        }

        public int getType() {
            return this.type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDev_erp() {
            return this.dev_erp;
        }

        public void setDev_erp(String dev_erp) {
            this.dev_erp = dev_erp;
        }

        public String getDev_name() {
            return this.dev_name;
        }

        public void setDev_name(String dev_name) {
            this.dev_name = dev_name;
        }

        public String getTester_erp() {
            return this.tester_erp;
        }

        public void setTester_erp(String tester_erp) {
            this.tester_erp = tester_erp;
        }

        public String getTester_name() {
            return this.tester_name;
        }

        public void setTester_name(String tester_name) {
            this.tester_name = tester_name;
        }

        public String getModule_name() {
            return this.module_name;
        }

        public void setModule_name(String module_name) {
            this.module_name = module_name;
        }

        public String getVersion() {
            return this.version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getTester_org() {
            return this.tester_org;
        }

        public void setTester_org(String tester_org) {
            this.tester_org = tester_org;
        }

        public String getTime() {
            return this.time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public boolean isSuccess() {
            return this.success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getInflence() {
            return this.inflence;
        }

        public void setInflence(String inflence) {
            this.inflence = inflence;
        }

        public String getTester_group() {
            return this.tester_group;
        }

        public void setTester_group(String tester_group) {
            this.tester_group = tester_group;
        }
    }
}
