package com.jdjr.knowledge.test.frame.internal.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.CacheControl;
import okhttp3.Request;
import okhttp3.Response;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DpCcUtil {
    private static final String CC_URL = "http://dhf.jd.com/bgapi/dpcc";

    public DpCcUtil() {
    }

    public static String getData(String pathKey) {
        return getBaseData(pathKey, "value");
    }

    public static Map<String, String> getDatas(String path) {
        Map<String, String> retMap = new HashMap();
        String[] pathArray = path.split("/");
        if (pathArray.length > 0) {
            String url = "http://dhf.jd.com/bgapi/dpcc/list/leaves/" + path.replace("/", "@");
            Request request = (new Request.Builder()).cacheControl(CacheControl.FORCE_NETWORK).get().url(url).build();
            Response response = HttpRequestUtil.execute(request);
            if (response != null && response.code() == 200 && response.body() != null) {
                try {
                    JSONArray leafArray = JSON.parseObject(response.body().string()).getJSONArray("leaves");
                    Iterator var7 = leafArray.iterator();

                    while (var7.hasNext()) {
                        Object leaf = var7.next();
                        retMap.put(JSON.parseObject(leaf.toString()).getString("dataId"), JSON.parseObject(leaf.toString()).getString("dataInfo"));
                    }
                } catch (Exception var9) {
                    LogUtil.error("请求异常： " + var9.getMessage());
                }
            } else {
                LogUtil.error("DpCcUtil->getDatas failed.");
            }

            if (response != null) {
                response.close();
            }
        }

        return retMap;
    }

    public static String getRemoteData(String pathKey) {
        String retData = getBaseData(pathKey, "remote");

        try {
            JSONObject jsonObject = JSON.parseObject(retData);
            if (jsonObject.containsKey("skuInfo") && jsonObject.containsKey("failInfo")) {
                retData = jsonObject.getString("skuInfo");
                if (!jsonObject.getString("failInfo").isEmpty()) {
                    LogUtil.error("DpCcUtil->getRemoteData failed: " + jsonObject.getString("failInfo"));
                }

                if (jsonObject.containsKey("ignoreInfo") && !jsonObject.getString("ignoreInfo").isEmpty()) {
                    LogUtil.warn("DpCcUtil->getRemoteData ignored: " + jsonObject.getString("ignoreInfo"));
                }
            }
        } catch (Exception var3) {
        }

        return retData;
    }

    public static Map<String, String> getRemoteDatas(String path) {
        Map<String, String> retMap = new HashMap();
        String[] pathArray = path.split("/");
        if (pathArray.length > 0) {
            String url = "http://dhf.jd.com/bgapi/dpcc/list/leaves/" + path.replace("/", "@");
            Request request = (new Request.Builder()).cacheControl(CacheControl.FORCE_NETWORK).get().url(url).build();
            Response response = HttpRequestUtil.execute(request);
            if (response != null && response.code() == 200 && response.body() != null) {
                try {
                    JSONArray leafArray = JSON.parseObject(response.body().string()).getJSONArray("leaves");
                    Iterator var7 = leafArray.iterator();

                    while (var7.hasNext()) {
                        Object leaf = var7.next();
                        String pathKey = path + "/" + JSON.parseObject(leaf.toString()).getString("dataId");
                        retMap.put(JSON.parseObject(leaf.toString()).getString("dataId"), getRemoteData(pathKey));
                    }
                } catch (Exception var10) {
                    LogUtil.error("请求异常： " + var10.getMessage());
                }
            } else {
                LogUtil.error("DpCcUtil->getRemoteDatas failed.");
            }

            if (response != null) {
                response.close();
            }
        }

        return retMap;
    }

    private static String getBaseData(String pathKey, String valKey) {
        String retData = "";
        String[] pathArray = pathKey.split("/");
        if (pathArray.length > 2) {
            String path = pathKey.substring(0, pathKey.lastIndexOf("/")).replace("/", "@");
            String key = pathArray[pathArray.length - 1];
            String url = "http://dhf.jd.com/bgapi/dpcc/get/data/" + path + "/" + key;
            Request request = (new Request.Builder()).cacheControl(CacheControl.FORCE_NETWORK).get().url(url).build();
            Response response = HttpRequestUtil.execute(request);
            if (response != null && response.code() == 200 && response.body() != null) {
                try {
                    retData = JSON.parseObject(response.body().string()).getString(valKey);
                } catch (Exception var10) {
                    LogUtil.info("请求异常： " + var10.getMessage());
                }
            } else {
                LogUtil.info("DpCcUtil->getBaseData failed.");
            }

            if (response != null) {
                response.close();
            }
        }

        return retData;
    }
}
