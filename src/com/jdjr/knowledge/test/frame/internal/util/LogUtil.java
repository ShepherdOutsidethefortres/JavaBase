package com.jdjr.knowledge.test.frame.internal.util;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.jdjr.knowledge.test.frame.internal.util.RequestUtil.KnowledgeTaskInfo;
import com.jdjr.knowledge.test.frame.internal.config.Config;
import com.jdjr.knowledge.test.frame.internal.response.ResultContent;
import com.jdjr.knowledge.test.frame.internal.config.Init.Params;


public class LogUtil {
    private static ObjectMapper objectMapper;
    private static final Logger LOG = LoggerFactory.getLogger(LogUtil.class);

    public LogUtil() {
    }

    public static void info(String info) {
        LOG.info(info);
    }

    public static void warn(String warn) {
        LOG.warn(warn);
    }

    public static void error(String message) {
        message = message.replace("\n", "\t").trim();
        LOG.error(message);
    }

    public static void error(String message, Object e) {
        message = message.replace("\n", "\t").trim();
        LOG.error(message, e);
    }

    public static void versionNotValid() {
        LOG.warn("┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅");
        LOG.warn("→ 依赖的大黄蜂版本过低，请pull最新的master到您的分支 ←");
        LOG.warn("┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅");
    }

    public static void versionNotUpdated() {
        LOG.warn("┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅");
        LOG.warn("→ 依赖的大黄蜂版本不是最新，建议您pull最新的master到您的分支 ←");
        LOG.warn("┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅");
    }

    public static void taskNotPresent() {
        LOG.warn("┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅");
        LOG.warn("→ 测试类未使用@Task注解，本次执行结果不进行数据上传 ←");
        LOG.warn("┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅");
    }

    public static void taskNotValid() {
        LOG.warn("┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅");
        LOG.warn("→ 服务端未找到合法的TaskID，本次执行结果不进行数据上传 ←");
        LOG.warn("┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅");
    }

    public static void taskValid(KnowledgeTaskInfo knowledgeTaskInfo) {
        switch(knowledgeTaskInfo.getType()) {
            case 1:
                LOG.debug("┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅");
                LOG.debug("→ 任务名称 : " + knowledgeTaskInfo.getModule_name() + " -- " + knowledgeTaskInfo.getName());
                LOG.debug("→ 开发人员 : " + knowledgeTaskInfo.getDev_name() + " [ " + knowledgeTaskInfo.getDev_erp() + " ] ");
                LOG.debug("→ 测试人员 : " + knowledgeTaskInfo.getTester_name() + " [ " + knowledgeTaskInfo.getTester_erp() + "--" + knowledgeTaskInfo.getTester_org() + " ] ");
                LOG.debug("→ 上线时间 : " + knowledgeTaskInfo.getTime() + " (" + knowledgeTaskInfo.getVersion() + "版本)");
                LOG.debug("→ 影响范围 : " + knowledgeTaskInfo.getInflence());
                LOG.debug("┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅");
                break;
            case 2:
                LOG.info("┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅");
                LOG.info("→ 任务类型 : 回归任务 ←");
                LOG.info("→ 任务名称 : " + knowledgeTaskInfo.getName());
                LOG.info("→ 测试人员 : " + knowledgeTaskInfo.getTester_name() + " [ " + knowledgeTaskInfo.getTester_erp() + " ] ");
                LOG.info("┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅");
                break;
            case 3:
                LOG.info("┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅");
                LOG.info("→ 任务类型 : 监控任务 ←");
                LOG.info("→ 任务名称 : " + knowledgeTaskInfo.getName());
                LOG.info("→ 测试人员 : " + knowledgeTaskInfo.getTester_name() + " [ " + knowledgeTaskInfo.getTester_erp() + " ] ");
                LOG.info("┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅");
                break;
            case 4:
                LOG.info("┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅");
                LOG.info("→ 任务名称 : " + knowledgeTaskInfo.getModule_name() + " -- " + knowledgeTaskInfo.getName());
                LOG.debug("→ 测试人员 : " + knowledgeTaskInfo.getTester_name() + " [ " + knowledgeTaskInfo.getTester_erp() + " -- " + knowledgeTaskInfo.getTester_org() + " ] ");
                LOG.info("→ 上线时间 : " + knowledgeTaskInfo.getTime() + " (" + knowledgeTaskInfo.getVersion() + "版本)");
                LOG.info("┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅");
        }

    }

    public static void descNotPresent(String name) {
        LOG.error("请在测试方法\"" + name + "\"下添加@Test注解的description信息，用于组成测试用例");
    }

    public static void report(String s) {
        LOG.info("┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅");
        LOG.info("→ 本次执行报告 ←");
        LOG.info(s);
        LOG.info("┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅ ┅");
    }

    public static class Jsf {
        public Jsf() {
        }

        public static void log(Config config, ResultContent resultContent) {
            switch(config.getLog()) {
                case NONE:
                default:
                    break;
                case ALL:
                    logJsfRequest(resultContent);
                    logJsfBody(resultContent);
                    logJsfResponse(resultContent);
                    LogUtil.LOG.info("");
                    break;
                case SIMPLE:
                    logJsfBody(resultContent);
                    logJsfResponse(resultContent);
                    LogUtil.LOG.info("");
            }

        }

        private static void logJsfRequest(ResultContent resultContent) {
            String[] info = resultContent.getRequest().getUrl().split("/");
            LogUtil.LOG.info("----------------------------Request---------------------------------");
            LogUtil.LOG.info("Interface : " + info[0]);
            LogUtil.LOG.info("Alias : " + info[1]);
            LogUtil.LOG.info("Method :  " + info[2]);
            if (!resultContent.getRequest().getIp().isEmpty()) {
                LogUtil.LOG.info("url :  " + resultContent.getRequest().getIp());
            }

        }

        private static void logJsfBody(ResultContent resultContent) {
            if (LogUtil.objectMapper == null) {
                LogUtil.objectMapper = new ObjectMapper();
            }

            LogUtil.LOG.info("----------------------------Body------------------------------------");
            Object map = new HashMap();

            try {
                map = (Map)LogUtil.objectMapper.readValue(resultContent.getRequest().getContent(), new TypeReference<Map<Params, Object>>() {
                });
            } catch (IOException var3) {
                LogUtil.error(var3.getMessage());
            }

            LogUtil.LOG.info(JSON.toJSONString(map));
        }

        private static void logJsfResponse(ResultContent resultContent) {
            LogUtil.LOG.info("----------------------------Response--------------------------------");
            LogUtil.LOG.info(resultContent.getResponse().getResponseBody());
        }
    }

    public static class Http {
        public Http() {
        }

        public static void log(Config config, ResultContent resultContent) {
            switch(config.getLog()) {
                case NONE:
                default:
                    break;
                case ALL:
                    logHttpRequest(resultContent);
                    logHttpHeader(resultContent);
                    logHttpCookie(resultContent);
                    logHttpParameter(resultContent);
                    logHttpBody(resultContent);
                    logHttpResponse(resultContent);
                    LogUtil.LOG.info("");
                    break;
                case SIMPLE:
                    logHttpRequest(resultContent);
                    logHttpBody(resultContent);
                    logHttpResponse(resultContent);
                    LogUtil.LOG.info("");
            }

        }

        private static void logHttpCookie(ResultContent resultContent) {
            if (resultContent.getRequest().getHeader().get("Cookie") != null) {
                String[] cookies = ((String)resultContent.getRequest().getHeader().get("Cookie")).split(";");
                LogUtil.LOG.info("----------------------------Cookie----------------------------------");
                String[] var2 = cookies;
                int var3 = cookies.length;

                for(int var4 = 0; var4 < var3; ++var4) {
                    String cookie = var2[var4];

                    try {
                        LogUtil.LOG.info(URLDecoder.decode(cookie.replace("=", " : ").trim(), "UTF-8"));
                    } catch (UnsupportedEncodingException var7) {
                        var7.printStackTrace();
                    }
                }
            }

        }

        private static void logHttpHeader(ResultContent resultContent) {
            if (resultContent.getRequest().getHeader().size() != 0) {
                LogUtil.LOG.info("----------------------------Header----------------------------------");
                Iterator var1 = resultContent.getRequest().getHeader().entrySet().iterator();

                while(var1.hasNext()) {
                    Map.Entry<String, String> entry = (Map.Entry)var1.next();
                    LogUtil.LOG.info((String)entry.getKey() + " : " + (String)entry.getValue());
                }
            }

        }

        private static void logHttpParameter(ResultContent resultContent) {
            if (resultContent.getRequest().getParameter().size() != 0) {
                LogUtil.LOG.info("----------------------------Parameter-------------------------------");
                Iterator var1 = resultContent.getRequest().getParameter().entrySet().iterator();

                while(var1.hasNext()) {
                    Map.Entry<String, ?> entry = (Map.Entry)var1.next();
                    LogUtil.LOG.info((String)entry.getKey() + " : " + entry.getValue());
                }
            }

        }

        private static void logHttpBody(ResultContent resultContent) {
            if (resultContent.getRequest().getContent() != null && !resultContent.getRequest().getContent().equals("")) {
                LogUtil.LOG.info("----------------------------Body------------------------------------");
                LogUtil.LOG.info(resultContent.getRequest().getContent());
            }

        }

        private static void logHttpRequest(ResultContent resultContent) {
            LogUtil.LOG.info("----------------------------Request---------------------------------");
            String params = getParameter(resultContent.getRequest().getParameter());
            String body = resultContent.getRequest().getContent() != null ? resultContent.getRequest().getContent() : "";
            if (params.equals("") && body.equals("")) {
                LogUtil.LOG.info(resultContent.getRequest().getUrl());
            } else if (body.equals("")) {
                LogUtil.LOG.info(resultContent.getRequest().getUrl() + "?" + params);
            } else if (params.equals("")) {
                LogUtil.LOG.info(resultContent.getRequest().getUrl() + "?body=" + body);
            } else {
                LogUtil.LOG.info(resultContent.getRequest().getUrl() + "?" + params + "&body=" + body);
            }

        }

        private static void logHttpResponse(ResultContent resultContent) {
            LogUtil.LOG.info("----------------------------Response--------------------------------");
            LogUtil.LOG.info(resultContent.getResponse().getResponseBody());
        }

        private static String getParameter(Map<String, ?> parameter) {
            StringBuilder sb = new StringBuilder();
            boolean first = true;
            Iterator var3 = parameter.entrySet().iterator();

            while(var3.hasNext()) {
                Map.Entry<String, ?> entry = (Map.Entry)var3.next();
                if (first) {
                    sb.append((String)entry.getKey()).append("=").append(entry.getValue());
                    first = false;
                } else {
                    sb.append("&").append((String)entry.getKey()).append("=").append(entry.getValue());
                }
            }

            return sb.toString();
        }
    }
}
