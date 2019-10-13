package com.jdjr.knowledge.test.frame.internal.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.jayway.jsonpath.Predicate;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class JsonUtil {
    public JsonUtil() {
    }

    public static String order(String json) {
        String orderString = "";
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

        try {
            JsonParser jsonParser = mapper.getFactory().createParser(json);
            JsonNode jsonNode = (JsonNode)mapper.readTree(jsonParser);
            Object obj = mapper.treeToValue(jsonNode, Object.class);
            orderString = mapper.writeValueAsString(obj);
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        return orderString;
    }

    public static String prettyPrint(String source) {
        String orderString = "";
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

        try {
            JsonParser jsonParser = mapper.getFactory().createParser(source);
            JsonNode jsonNode = (JsonNode)mapper.readTree(jsonParser);
            Object obj = mapper.treeToValue(jsonNode, Object.class);
            orderString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        return orderString;
    }

    public static Object jsonPath(String json, String path, Filter... filter) {
        try {
            return filter.length == 0 ? JsonPath.parse(json).read(path, new Predicate[0]) : JsonPath.parse(json).read(path, new Predicate[]{filter[0]});
        } catch (PathNotFoundException var4) {
            LogUtil.error(var4.getMessage());
            return null;
        }
    }

    public static Object jsonPathWeb(String json, String path, Filter... filter) {
        try {
            return filter.length == 0 ? JsonPath.parse(json).read(path, new Predicate[0]) : JsonPath.parse(json).read(path, new Predicate[]{filter[0]});
        } catch (PathNotFoundException var4) {
            return null;
        }
    }

    public static String remove(String json, String path, Filter... filter) {
        try {
            return filter.length == 0 ? JsonPath.parse(json).delete(path, new Predicate[0]).jsonString() : JsonPath.parse(json).delete(path, new Predicate[]{filter[0]}).jsonString();
        } catch (PathNotFoundException var4) {
            LogUtil.error(var4.getMessage());
            return null;
        }
    }

    public static String remove(String json, Map<String, Filter> pathMap) {
        String localJson = json;
        Iterator var3 = pathMap.entrySet().iterator();

        while(var3.hasNext()) {
            Map.Entry<String, Filter> entry = (Map.Entry)var3.next();
            if (entry.getValue() != null) {
                localJson = JsonPath.parse(localJson).delete((String)entry.getKey(), new Predicate[0]).jsonString();
            } else {
                localJson = JsonPath.parse(localJson).delete((String)entry.getKey(), new Predicate[]{(Predicate)entry.getValue()}).jsonString();
            }
        }

        return localJson;
    }
}
