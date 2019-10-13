package com.jdjr.knowledge.test.frame.internal.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import com.jdjr.knowledge.test.frame.internal.response.ResultContent.Request;
import com.jdjr.knowledge.test.frame.internal.entity.HttpEntity;
import com.jdjr.knowledge.test.frame.internal.entity.JsfEntity;
import com.jdjr.knowledge.test.frame.internal.config.Init.Evn;
import com.jdjr.knowledge.test.frame.internal.response.ResultContent.Request;


public class EntityUtil {
    private static ObjectMapper objectMapper;

    public EntityUtil() {
    }

    public static String encodeHeader(String value) {
        StringBuilder sb = null;

        try {
            sb = new StringBuilder();
            boolean flag = false;
            String[] var3 = value.trim().split(";");
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String v = var3[var5];
                String[] vPair = v.split("=");
                String[] var8 = vPair;
                int var9 = vPair.length;

                for(int var10 = 0; var10 < var9; ++var10) {
                    String pair = var8[var10];
                    if (ChineseUtil.isChinese(pair)) {
                        flag = true;
                    }
                }

                if (flag) {
                    sb.append(vPair[0]).append("=").append(URLEncoder.encode(vPair[1], "UTF-8")).append(";");
                } else {
                    sb.append(vPair[0]).append("=").append(vPair[1]).append(";");
                }
            }
        } catch (UnsupportedEncodingException var12) {
            LogUtil.error("设置 header 时，出现不支持的编码转换");
        }

        return sb.toString();
    }

    public static Map<String, String> encodeHeaders(Map<String, String> header) {
        Map<String, String> encodeHeader = new HashMap();
        if (header.size() != 0) {
            Iterator var2 = header.entrySet().iterator();

            while(true) {
                while(true) {
                    while(var2.hasNext()) {
                        Map.Entry<String, String> entry = (Map.Entry)var2.next();
                        if (((String)entry.getKey()).equals("Cookie")) {
                            if (!ChineseUtil.isChinese((String)entry.getKey()) && !ChineseUtil.isChinese((String)entry.getValue())) {
                                encodeHeader.put(entry.getKey(), entry.getValue());
                            } else {
                                encodeHeader.put(entry.getKey(), encodeHeader((String)entry.getValue()));
                            }
                        } else {
                            encodeHeader.put(entry.getKey(), entry.getValue());
                        }
                    }

                    return encodeHeader;
                }
            }
        } else {
            return encodeHeader;
        }
    }

    public static Request httpConvert(HttpEntity httpEntity) {
        Request request = new Request();
        request.setOnGate(httpEntity.isOnGate());
        request.setUrl(httpEntity.getUrl());
        request.setIp(httpEntity.getIp());
        request.setContent(httpEntity.getBody());
        request.setHeader(httpEntity.getHeader());
        request.setParameter(httpEntity.getParameter());
        request.setEntityType(httpEntity.getEntityType());
        request.setEvn(Evn.TEST);
        request.setRequestMethod(httpEntity.getRequestMethod());
        request.setInfo(httpEntity.getInfo());
        return request;
    }

    public static Request jsfConvert(JsfEntity jsfEntity) {
        Request request = new Request();
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        try {
            request.setOnGate(false);
            request.setUrl(jsfEntity.getInterfaceId() + "/" + jsfEntity.getAlias() + "/" + jsfEntity.getMethodName());
            request.setIp(jsfEntity.getIp());
            request.setContent(objectMapper.writeValueAsString(jsfEntity.getMethodParams()));
            request.setHeader(new HashMap());
            request.setParameter(jsfEntity.getJsfParameter());
            request.setEntityType(jsfEntity.getEntityType());
            request.setEvn(jsfEntity.getEvn());
            request.setRequestMethod(jsfEntity.getRequestMethod());
            request.setInfo(jsfEntity.getInfo());
        } catch (JsonProcessingException var3) {
            LogUtil.error(var3.getMessage());
        }

        return request;
    }
}
