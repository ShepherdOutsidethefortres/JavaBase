package com.jdjr.knowledge.test.frame.internal.request.sender;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jd.jsf.gd.GenericService;
import com.jd.jsf.gd.config.ConsumerConfig;
import com.jd.jsf.gd.config.RegistryConfig;
import com.jdjr.knowledge.test.frame.internal.config.Config;
import com.jdjr.knowledge.test.frame.internal.config.Init;
import com.jdjr.knowledge.test.frame.internal.config.Init.Params;
import com.jdjr.knowledge.test.frame.internal.config.Init.RequestType;
import com.jdjr.knowledge.test.frame.internal.config.Init.FunctionId;
import com.jdjr.knowledge.test.frame.internal.entity.JsfEntity;
import com.jdjr.knowledge.test.frame.internal.response.ResultContent;
import com.jdjr.knowledge.test.frame.internal.response.ResultContent.Response;
import com.jdjr.knowledge.test.frame.internal.response.ResultContent.Builder;
import com.jdjr.knowledge.test.frame.internal.util.EntityUtil;
import com.jdjr.knowledge.test.frame.internal.util.LogUtil;
import com.jdjr.knowledge.test.frame.internal.util.PropertiesUtil;
import com.jdjr.knowledge.test.frame.internal.util.ThreadLocalUtil;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsfRequestSender {
    private JsfEntity jsfEntity;
    private static ObjectMapper objectMapper;

    public JsfRequestSender() {
    }

    public ResultContent execute(Config config, JsfEntity jsfEntityInfo, String description) {
        this.jsfEntity = jsfEntityInfo;
        return this.call(config).newBuilder().request(EntityUtil.jsfConvert(jsfEntityInfo.newBuilder().requestMethod(RequestType.INVOKE).build())).description(description).build();
    }

    private ResultContent call(Config config) {
        ResultContent resultContent = null;
        Response responseContent = new Response();
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        switch (this.jsfEntity.getEvn()) {
            case TEST:
                GenericService genericService = this.getGenericService();

                long startTime = System.currentTimeMillis();

                Object test_result;
                try {
                    test_result = genericService.$invoke(this.jsfEntity.getMethodName(), (String[]) ((String[]) this.jsfEntity.getMethodParams().get(Params.CLASS)), (Object[]) ((Object[]) this.jsfEntity.getMethodParams().get(Params.VALUE)));
                } catch (Exception var13) {
                    responseContent.setStatus(false);
                    test_result = var13.getMessage();
                }

                long endTime = System.currentTimeMillis();
                responseContent.setResponseType(config.getResponseType().name());
                responseContent.setExecuteTime(startTime);
                responseContent.setResponseBody(String.valueOf(test_result));
                responseContent.setResponseTime(endTime - startTime);
                responseContent.setStatus(true);
                resultContent = (new Builder()).response(responseContent).build();
                return resultContent;
            case ONLINE:
                Map online_result = null;

                try {
                    online_result = (Map) objectMapper.readValue(serverJsfExecute(this.jsfEntity), new TypeReference<Map<String, Object>>() {
                    });
                    responseContent.setResponseBody(objectMapper.writeValueAsString(online_result.get("responseBody")));
                } catch (IOException var12) {
                    LogUtil.error(var12.getMessage());
                }

                ThreadLocalUtil.setFunctionId(FunctionId.JSF.name(), this.jsfEntity.getInterfaceId() + "-" + this.jsfEntity.getMethodName());
                responseContent.setResponseType(config.getResponseType().name());
                responseContent.setExecuteTime(Long.parseLong(online_result.get("executeTime").toString()));
                responseContent.setResponseTime(Long.parseLong(online_result.get("responseTime").toString()));
                responseContent.setStatus(Boolean.parseBoolean(String.valueOf(online_result.get("status"))));
                resultContent = (new Builder()).response(responseContent).build();
                return resultContent;
            default:
                return resultContent;
        }
    }

    private GenericService getGenericService() {
        RegistryConfig jsfRegistry = new RegistryConfig();
        GenericService genericService = ThreadLocalUtil.getGenericService(this.jsfEntity.getInterfaceId() + this.jsfEntity.getAlias() + this.jsfEntity.getIp());
        if (genericService == null) {
            ConsumerConfig<GenericService> consumerConfig = new ConsumerConfig();
            consumerConfig.setRegistry(jsfRegistry);
            consumerConfig.setProtocol(this.jsfEntity.getProtocol());
            consumerConfig.setInterfaceId(this.jsfEntity.getInterfaceId());
            consumerConfig.setGeneric(true);
            consumerConfig.setUrl(this.jsfEntity.getIp());
            consumerConfig.setAlias(this.jsfEntity.getAlias());
            if (this.jsfEntity.getJsfParameter() != null) {
                Iterator var4 = this.jsfEntity.getJsfParameter().keySet().iterator();

                while (var4.hasNext()) {
                    String key = (String) var4.next();
                    consumerConfig.setParameter(key, (String) this.jsfEntity.getJsfParameter().get(key));
                }
            }

            consumerConfig.setTimeout(this.jsfEntity.getTimeOut());
            genericService = (GenericService) consumerConfig.refer();
            ThreadLocalUtil.setGenericService(this.jsfEntity.getInterfaceId() + this.jsfEntity.getAlias() + this.jsfEntity.getIp(), genericService);
            ThreadLocalUtil.setFunctionId(FunctionId.JSF.name(), this.jsfEntity.getInterfaceId() + "-" + this.jsfEntity.getMethodName());
        }

        return genericService;
    }

    private static String serverJsfExecute(JsfEntity jsfEntity) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        String result = "";
        Map<String, Object> jsf = new HashMap();
        jsf.put("ip", jsfEntity.getIp());
        jsf.put("interfaceId", jsfEntity.getInterfaceId());
        jsf.put("alias", jsfEntity.getAlias());
        jsf.put("methodName", jsfEntity.getMethodName());
        jsf.put("methodParams", jsfEntity.getMethodParams());
        jsf.put("jsfParameter", jsfEntity.getJsfParameter());
        String jsonstring = null;

        try {
            jsonstring = JSON.toJSONString(jsf, new SerializerFeature[]{SerializerFeature.WriteClassName, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty});
        } catch (Exception var13) {
            LogUtil.error(var13.getMessage());
        }

        Object[] object = (Object[]) ((Object[]) jsfEntity.getMethodParams().get(Params.VALUE));
        List<String> list = new ArrayList();
        Object[] var6 = object;
        int var7 = object.length;

        for (int var8 = 0; var8 < var7; ++var8) {
            Object anObject = var6[var8];
            if (anObject != null) {
                String[] type = anObject.getClass().getTypeName().split("\\.");
                list.add(type[type.length - 3] + "." + type[type.length - 2] + "." + type[type.length - 1]);
            }
        }

        Iterator var14 = list.iterator();

        while (var14.hasNext()) {
            String aList = (String) var14.next();
            Pattern pattern = Pattern.compile("\"@type\":\"com(.+?)" + aList + "\",");

            for (Matcher matcher = pattern.matcher(jsonstring); matcher.find(); jsonstring = jsonstring.replace(matcher.group(), "")) {
            }
        }

        String requestUrl = PropertiesUtil.data("dhf.web.jsf.url");
        if (!StringUtils.isEmpty(jsfEntity.getJsfRegistry()) && jsfEntity.getJsfRegistry().contains(".th.")) {
            requestUrl = PropertiesUtil.data("dhf.web.jsf.th.url");
        }

        Request request = (new okhttp3.Request.Builder()).cacheControl(CacheControl.FORCE_NETWORK).url(requestUrl).post(RequestBody.create(MediaType.parse("application/json"), jsonstring)).build();

        try {
            OkHttpClient client = (new okhttp3.OkHttpClient.Builder()).connectTimeout(15L, TimeUnit.SECONDS).readTimeout(20L, TimeUnit.SECONDS).build();
            okhttp3.Response response = client.newCall(request).execute();
            result = response.body().string();
            response.close();
        } catch (SocketTimeoutException var11) {
            LogUtil.error("请求超时： " + var11.getMessage());
        } catch (NullPointerException | IOException var12) {
            var12.printStackTrace();
        }

        return result;
    }
}
