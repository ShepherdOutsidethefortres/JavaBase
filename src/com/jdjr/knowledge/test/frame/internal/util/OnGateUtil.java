package com.jdjr.knowledge.test.frame.internal.util;

import com.jd.cpa.stamp.Signature;
import com.jdjr.knowledge.test.frame.internal.config.Init;
import com.jdjr.knowledge.test.frame.internal.entity.HttpEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OnGateUtil {
    public OnGateUtil() {
    }

    public static Map<String, ?> onGateparameter(String query, HttpEntity httpEntityInfo) {
        String body = httpEntityInfo.getBody();
        Map<String, String> parameter = (Map<String, String>) httpEntityInfo.getParameter();
        Map<String, String> signMap = new HashMap();
        List<String> signList = new ArrayList();
        String sv;
        if (query != null && !query.isEmpty()) {
            String[] strings = query.split("&");
            String[] var7 = strings;
            int var8 = strings.length;

            for(int var9 = 0; var9 < var8; ++var9) {
                sv = var7[var9];
                String[] item = sv.split("=");
                parameter.put(item[0], item[1]);
            }
        }

        String functionId = (String)parameter.get("functionId");
        ThreadLocalUtil.setFunctionId(Init.FunctionId.HTTP.name(), functionId);
        String client = (String)parameter.get("client");
        String clientVersion = (String)parameter.get("clientVersion");
        String st = String.valueOf(System.currentTimeMillis());
        sv = getSv(client, clientVersion);

        String sign;
        try {
            sign = client.toLowerCase();
            byte var12 = -1;
            switch(sign.hashCode()) {
                case -861391249:
                    if (sign.equals("android")) {
                        var12 = 0;
                    }
                    break;
                case 109:
                    if (sign.equals("m")) {
                        var12 = 1;
                    }
                    break;
                case 3491:
                    if (sign.equals("mp")) {
                        var12 = 2;
                    }
                    break;
                case 3238794:
                    if (sign.equals("ipad")) {
                        var12 = 4;
                    }
                    break;
                case 93029210:
                    if (sign.equals("apple")) {
                        var12 = 3;
                    }
            }

            switch(var12) {
                case 0:
                case 1:
                    signMap.put("functionId", functionId);
                    signMap.put("body", body);
                    signMap.put("client", client);
                    signMap.put("clientVersion", clientVersion);
                    signMap.put("sign", "");
                    signMap.put("st", st);
                    signMap.put("sv", sv);
                    signMap.put("uuid", parameter.get("uuid"));
                    signList.add("functionId");
                    signList.add("body");
                    signList.add("uuid");
                    signList.add("client");
                    signList.add("clientVersion");
                    break;
                case 2:
                    signMap.put("functionId", functionId);
                    signMap.put("body", body);
                    signMap.put("client", client);
                    signMap.put("sign", "");
                    signMap.put("st", st);
                    signMap.put("sv", sv);
                    signMap.put("uuid", ((String)parameter.get("uuid")).toString().toLowerCase());
                    signList.add("functionId");
                    signList.add("body");
                    signList.add("uuid");
                    signList.add("client");
                    break;
                case 3:
                case 4:
                    signMap.put("functionId", functionId);
                    signMap.put("body", body);
                    signMap.put("client", client);
                    signMap.put("clientVersion", clientVersion);
                    signMap.put("sign", "");
                    signMap.put("st", st);
                    signMap.put("sv", sv);
                    signMap.put("openudid", parameter.get("openudid"));
                    signList.add("functionId");
                    signList.add("body");
                    signList.add("openudid");
                    signList.add("client");
                    signList.add("clientVersion");
                    break;
                default:
                    throw new RunExceptionUtil(new String[]{"请输入正确的设备类型"});
            }
        } catch (Exception var13) {
            LogUtil.error("缺少计算sign的参数信息");
        }

        sign = Signature.getSignV1(signMap, signList).getSign();
        parameter.put("st", st);
        parameter.put("sign", sign);
        parameter.put("sv", sv);
        return parameter;
    }

    private static String getSv(String client, String clientVersion) {
        if ("5.0.1".compareTo(clientVersion) > 0) {
            return "android".equals(client.toLowerCase()) ? "dd" : "1";
        } else {
            String[] sv;
            int ran;
            if ("5.0.1".compareTo(clientVersion) <= 0 && "5.2.0".compareTo(clientVersion) > 0) {
                sv = new String[]{"100", "101", "110", "111"};
                ran = (int)(Math.random() * (double)sv.length);
                return sv[ran];
            } else {
                sv = new String[]{"100", "101", "102", "110", "111", "112", "120", "121", "122"};
                ran = (int)(Math.random() * (double)sv.length);
                return sv[ran];
            }
        }
    }
}
