package com.jdjr.knowledge.test.frame.internal.util;

import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HttpRequestUtil {
    private static OkHttpClient okHttpClient = null;

    public HttpRequestUtil() {
    }

    public static Response execute(Request request) {
        Response response = null;

        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException var3) {
            LogUtil.error("请求产生异常： " + var3.getMessage());
        }

        return response;
    }

    public static void execute(Request request, Callback callback) {
        okHttpClient.newCall(request).enqueue(callback);
    }

    static {
        okHttpClient = HttpRequestUtil.OkSingleton.INSTANCE.init();
    }

    private static enum OkSingleton {
        INSTANCE;

        private OkHttpClient ohc;

        private OkSingleton() {
            List<Protocol> pList = new ArrayList();
            pList.add(Protocol.HTTP_1_1);
            pList.add(Protocol.HTTP_2);
            this.ohc = (new OkHttpClient()).newBuilder().protocols(pList).connectTimeout(60L, TimeUnit.SECONDS).readTimeout(60L, TimeUnit.SECONDS).writeTimeout(60L, TimeUnit.SECONDS).build();
        }

        public OkHttpClient init() {
            return this.ohc;
        }
    }
}
