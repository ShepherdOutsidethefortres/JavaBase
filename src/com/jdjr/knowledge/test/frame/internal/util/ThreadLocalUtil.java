package com.jdjr.knowledge.test.frame.internal.util;

import com.jd.jsf.gd.GenericService;
import com.jdjr.knowledge.test.frame.internal.TestListener;
import com.jdjr.knowledge.test.frame.internal.response.ResultContent;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadLocalUtil {
    public ThreadLocalUtil() {
    }

    public static List<String> getHost() {
        Object hostList = new ArrayList();

        try {
            hostList = (List) TestListener.threadHost.get();
        } catch (NullPointerException var2) {
            LogUtil.error("请继承基础类 : BaseRequest -- threadHost 初始化失败");
            System.exit(0);
        }

        return (List)hostList;
    }

    public static void setHost(String newValue) {
        List<String> newList = getHost();
        newList.add(newValue);
        TestListener.threadHost.set(newList);
    }

    public static void setVariableData(final String newKey, final Object newValue) {
        Map<String, Object> newMap = getVariableData();
        newMap.putAll(new HashMap<String, Object>() {
            {
                this.put(newKey, newValue);
            }
        });
        TestListener.threadVariableData.set(newMap);
    }

    public static Map<String, Object> getVariableData() {
        Object variableData = new HashMap();

        try {
            variableData = (Map)TestListener.threadVariableData.get();
        } catch (NullPointerException var2) {
            LogUtil.error("TestListener -- threadVariableData  初始化失败");
            System.exit(0);
        }

        return (Map)variableData;
    }

    public static void setFunctionId(String key, String newValue) {
        Map<String, Set<String>> functionId = getFunctionId();
        Set<String> newSet = (Set)functionId.get(key);
        if (null == newSet) {
            newSet = new HashSet();
        }

        ((Set)newSet).add(newValue);
        functionId.put(key, newSet);
        TestListener.threadFunctionIdMap.set(functionId);
    }

    public static Map<String, Set<String>> getFunctionId() {
        Object functionId = new HashMap();

        try {
            functionId = (Map)TestListener.threadFunctionIdMap.get();
        } catch (NullPointerException var2) {
            LogUtil.error("TestListener -- threadFunctionIdSet  初始化失败");
            System.exit(0);
        }

        return (Map)functionId;
    }

    static void setSceneResultContentList(ResultContent resultContent) {
        List<ResultContent> newList = getSceneResultContentList();
        newList.add(resultContent);
        TestListener.threadScenelResultContentList.set(newList);
    }

    private static List<ResultContent> getSignalResultContentList() {
        Object newList = new ArrayList();

        try {
            newList = (List)TestListener.threadSignalResultContentList.get();
        } catch (NullPointerException var2) {
            LogUtil.error("TestListener -- threadSignalResultContentList  初始化失败");
            System.exit(0);
        }

        return (List)newList;
    }

    static void setSignalResultContentList(ResultContent resultContent) {
        List<ResultContent> newList = getSignalResultContentList();
        newList.add(resultContent);
        TestListener.threadSignalResultContentList.set(newList);
    }

    private static List<ResultContent> getSceneResultContentList() {
        Object newList = new ArrayList();

        try {
            newList = (List)TestListener.threadScenelResultContentList.get();
        } catch (NullPointerException var2) {
            LogUtil.error("TestListener -- threadScenelResultContentList  初始化失败");
            System.exit(0);
        }

        return (List)newList;
    }

    public static void setGenericService(final String newKey, final GenericService newValue) {
        ConcurrentHashMap<String, GenericService> newMap = getGenericService();
        newMap.putAll(new ConcurrentHashMap<String, GenericService>() {
            {
                this.put(newKey, newValue);
            }
        });
        TestListener.threadGenericService.set(newMap);
    }

    public static GenericService getGenericService(String key) {
        ConcurrentHashMap genericService = new ConcurrentHashMap();

        try {
            genericService = (ConcurrentHashMap)TestListener.threadGenericService.get();
        } catch (NullPointerException var3) {
            LogUtil.error("TestListener -- threadGenericService  初始化失败");
            System.exit(0);
        }

        return (GenericService)genericService.get(key);
    }

    private static ConcurrentHashMap<String, GenericService> getGenericService() {
        ConcurrentHashMap genericService = new ConcurrentHashMap();

        try {
            genericService = (ConcurrentHashMap)TestListener.threadGenericService.get();
        } catch (NullPointerException var2) {
            LogUtil.error("TestListener -- threadGenericService  初始化失败");
            System.exit(0);
        }

        return genericService;
    }

    static boolean getTestSaveFlag() {
        return (Boolean)TestListener.threadSaveFlag.get();
    }
}
