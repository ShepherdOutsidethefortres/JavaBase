package com.jdjr.knowledge.test.frame.internal.request.sender;

import com.jdjr.knowledge.test.frame.internal.config.Config;
import com.jdjr.knowledge.test.frame.internal.config.Init.RequestType;
import com.jdjr.knowledge.test.frame.internal.config.Init.FunctionId;
import com.jdjr.knowledge.test.frame.internal.response.ResultContent;
import com.jdjr.knowledge.test.frame.internal.util.*;
import okhttp3.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;

import com.jdjr.knowledge.test.frame.internal.entity.HttpEntity;
import org.apache.commons.io.IOUtils;

public class HttpRequestSender {
    private HttpEntity httpEntity;

    public HttpRequestSender() {
    }

    public HashMap<String, String> getResponseHeaders(Response response) {
        HashMap<String, String> result = new HashMap();
        if (response.priorResponse() != null) {
            result.putAll(this.getResponseHeaders(response.priorResponse()));
        }

        if (response.networkResponse() != null) {
            result.putAll(this.getResponseHeaders(response.networkResponse()));
        }

        Headers headers = response.headers();
        Iterator var4 = headers.names().iterator();

        while (true) {
            while (var4.hasNext()) {
                String name = (String) var4.next();
                if (name.equalsIgnoreCase("Set-Cookie")) {
                    List<String> values = headers.values(name);
                    Iterator var7 = values.iterator();

                    while (var7.hasNext()) {
                        String val = (String) var7.next();
                        int index = val.indexOf("=");
                        if (index > 0) {
                            result.put(val.substring(0, index), val.substring(index + 1, val.length()));
                        }
                    }
                } else {
                    result.put(name, headers.get(name));
                }
            }

            return result;
        }
    }

    public ResultContent executePost(Config config, HttpEntity httpEntityInfo, String description) {
        this.httpEntity = httpEntityInfo;
        ResultContent resultContent = null;
        Response response = null;
        if (httpEntityInfo.getIp() != null && !httpEntityInfo.getIp().equals("") && !httpEntityInfo.getIp().equals("127.0.0.1 localhost")) {
            HostUtil.updateVirtualDns(httpEntityInfo.getIp());
            ThreadLocalUtil.setHost(httpEntityInfo.getIp());
        }

        try {
            RequestBody body = this.getRequestBody();
            HttpUrl url = this.getHttpUrl();
            Request request = (new Request.Builder()).cacheControl(CacheControl.FORCE_NETWORK).headers(this.getHeaders()).url(url).post(body).build();
            response = HttpRequestSender.HttpClient.INSTANCE.client.newCall(request).execute();
            if (httpEntityInfo.getIp() != null && !httpEntityInfo.getIp().equals("") && !httpEntityInfo.getIp().equals("127.0.0.1 localhost")) {
                HostUtil.clearVirtualDnsByHostName(httpEntityInfo.getIp());
            }
        } catch (Exception var9) {
            if (httpEntityInfo.getIp() != null && !httpEntityInfo.getIp().equals("") && !httpEntityInfo.getIp().equals("127.0.0.1 localhost")) {
                HostUtil.clearVirtualDnsByHostName(httpEntityInfo.getIp());
            }

            LogUtil.error(var9.getMessage());
        }

        if (response != null) {
            resultContent = this.getResponseContent(response, config).newBuilder().request(EntityUtil.httpConvert(this.httpEntity.newBuilder().requestMethod(RequestType.POST).build())).description(description).headers(this.getResponseHeaders(response)).build();
            response.close();
        }

        return resultContent;
    }

    public ResultContent executeGet(Config config, HttpEntity httpEntityInfo, String description) {
        this.httpEntity = httpEntityInfo;
        ResultContent resultContent = null;
        Response response = null;
        if (httpEntityInfo.getIp() != null && !httpEntityInfo.getIp().equals("") && !httpEntityInfo.getIp().equals("127.0.0.1 localhost")) {
            HostUtil.updateVirtualDns(httpEntityInfo.getIp());
            ThreadLocalUtil.setHost(httpEntityInfo.getIp());
        }

        try {
            HttpUrl url = this.getHttpUrl();
            Request request = (new Request.Builder()).cacheControl(CacheControl.FORCE_NETWORK).headers(this.getHeaders()).url(url).build();
            response = HttpRequestSender.HttpClient.INSTANCE.client.newCall(request).execute();
            if (httpEntityInfo.getIp() != null && !httpEntityInfo.getIp().equals("") && !httpEntityInfo.getIp().equals("127.0.0.1 localhost")) {
                HostUtil.clearVirtualDnsByHostName(httpEntityInfo.getIp());
            }
        } catch (Exception var8) {
            if (httpEntityInfo.getIp() != null && !httpEntityInfo.getIp().equals("") && !httpEntityInfo.getIp().equals("127.0.0.1 localhost")) {
                HostUtil.clearVirtualDnsByHostName(httpEntityInfo.getIp());
            }

            LogUtil.error(var8.getMessage());
        }

        if (response != null) {
            resultContent = this.getResponseContent(response, config).newBuilder().request(EntityUtil.httpConvert(this.httpEntity.newBuilder().requestMethod(RequestType.GET).build())).description(description).headers(this.getResponseHeaders(response)).build();
            response.close();
        }

        return resultContent;
    }

    private RequestBody getRequestBody() {
        String contentType = "application/x-www-form-urlencoded;charset=UTF-8";
        if (this.httpEntity.getHeader().containsKey("Content-Type")) {
            contentType = (String) this.httpEntity.getHeader().get("Content-Type");
        }

        byte var3 = -1;
        switch (contentType.hashCode()) {
            case 316570532:
                if (contentType.equals("application/x-www-form-urlencoded;charset=UTF-8")) {
                    var3 = 0;
                }
                break;
            case 1816680781:
                if (contentType.equals("application/json;charset=UTF-8")) {
                    var3 = 1;
                }
        }

        switch (var3) {
            case 0:
                okhttp3.FormBody.Builder builder = new okhttp3.FormBody.Builder();
                builder.add("body", this.replaceBodyParameter());
                Iterator var5 = this.httpEntity.getForm().keySet().iterator();

                while (var5.hasNext()) {
                    String name = (String) var5.next();
                    builder.add(name, (String) this.httpEntity.getForm().get(name));
                }

                return builder.build();
            case 1:
                MediaType mediaType = MediaType.parse("application/json");
                return RequestBody.create(mediaType, this.replaceBodyParameter());
            default:
                return null;
        }
    }

    private Headers getHeaders() {
        okhttp3.Headers.Builder headersBuilder = new okhttp3.Headers.Builder();
        Map<String, String> map = new HashMap();
        map.putAll((Map) (this.httpEntity.getHeader() != null ? this.httpEntity.getHeader() : new HashMap()));
        Iterator var3 = map.entrySet().iterator();

        while (var3.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry) var3.next();
            headersBuilder.add((String) entry.getKey(), (String) entry.getValue());
        }

        return headersBuilder.build();
    }

    private HttpUrl getHttpUrl() {
        okhttp3.HttpUrl.Builder url = new okhttp3.HttpUrl.Builder();

        try {
            String formatUrl = this.formatUrl();
            URL baseUrl = new URL(formatUrl);
            String functionId = baseUrl.getPath();
            int var8;
            if (functionId.startsWith("/client.action")) {
                String queries = baseUrl.getQuery();
                String[] var6 = queries.split("&");
                int var7 = var6.length;

                for (var8 = 0; var8 < var7; ++var8) {
                    String query = var6[var8];
                    String[] items = query.split("=");
                    if (items.length == 2 && items[0].equalsIgnoreCase("functionId")) {
                        functionId = items[1];
                    }
                }
            }

            while (functionId.contains("//")) {
                functionId = functionId.replaceAll("//", "/");
            }

            if (functionId.length() > 0 && functionId.charAt(functionId.length() - 1) == '/') {
                functionId = functionId.substring(0, functionId.length() - 1);
            }

            if (functionId.length() > 0 && functionId.charAt(0) == '/') {
                functionId = functionId.substring(1, functionId.length());
            }

            ThreadLocalUtil.setFunctionId(FunctionId.HTTP.name(), functionId);
            url.scheme(baseUrl.getProtocol()).host(baseUrl.getHost()).addPathSegments(baseUrl.getPath().equals("") ? "" : baseUrl.getPath().substring(1));
            if (baseUrl.getQuery() != null && !baseUrl.getQuery().isEmpty()) {
                String[] strings = baseUrl.getQuery().split("&");
                HashMap<String, Object> parameter = new HashMap();
                String[] var17 = strings;
                var8 = strings.length;

                for (int var20 = 0; var20 < var8; ++var20) {
                    String queryItem = var17[var20];
                    String[] item = queryItem.split("=");
                    if (item.length >= 2) {
                        parameter.put(item[0], item[1]);
                    }
                }

                Map<String, Object> parameters = (Map<String, Object>) this.httpEntity.getParameter();
                parameters.putAll(parameter);
                this.httpEntity = this.httpEntity.newBuilder().parameter(parameters).build();
                Iterator var19 = this.setParameterThroughGate(baseUrl.getQuery()).entrySet().iterator();

                while (var19.hasNext()) {
                    Map.Entry<String, ?> entry = (Map.Entry) var19.next();
                    url.addQueryParameter((String) entry.getKey(), entry.getValue().toString());
                }
            } else {
                Iterator var13 = this.setParameterThroughGate(baseUrl.getQuery()).entrySet().iterator();

                while (var13.hasNext()) {
                    Map.Entry<String, ?> entry = (Map.Entry) var13.next();
                    url.addQueryParameter((String) entry.getKey(), entry.getValue().toString());
                }
            }
        } catch (MalformedURLException var12) {
            LogUtil.error("请求参数异常");
        }

        return url.build();
    }

    private String formatUrl() {
        String url;
        for (url = this.httpEntity.getUrl(); url.charAt(url.length() - 1) == '/'; url = url.substring(0, url.length() - 1)) {
        }

        url = url.startsWith("http://") ? url : (url.startsWith("https://") ? url : "http://" + url);
        url = url.endsWith("?") ? url.substring(0, url.length() - 1) : url;
        return url;
    }

    private Map<String, ?> setParameterThroughGate(String query) {
        return this.httpEntity.isOnGate() ? OnGateUtil.onGateparameter(query, this.httpEntity) : this.httpEntity.getParameter();
    }

    private String replaceBodyParameter() {
        if (this.httpEntity.getBody() == null) {
            return "";
        } else {
            if (ThreadLocalUtil.getVariableData() != null) {
                this.httpEntity = (new com.jdjr.knowledge.test.frame.internal.entity.HttpEntity.Builder(this.httpEntity)).body(TokenParserUtil.parse(this.httpEntity.getBody())).build();
            }

            return this.httpEntity.getBody();
        }
    }

    private ResultContent getResponseContent(Response response, Config config) {
        ResultContent resultContent = null;

        try {
            String encoding = response.header("Content-Encoding");
            if (response.body() != null) {
                InputStream inputStream = response.body().byteStream();
                if ("gzip".equals(encoding)) {
                    inputStream = new GZIPInputStream((InputStream) inputStream);
                }

                String result = this.formatResponse(IOUtils.toString((InputStream) inputStream, "UTF-8"));
                com.jdjr.knowledge.test.frame.internal.response.ResultContent.Response responseContent = new com.jdjr.knowledge.test.frame.internal.response.ResultContent.Response();
                responseContent.setResponseType(config.getResponseType().name());
                responseContent.setExecuteTime(response.sentRequestAtMillis());
                responseContent.setResponseBody(result);
                responseContent.setResponseTime(response.receivedResponseAtMillis() - response.sentRequestAtMillis());
                responseContent.setStatus(response.code() == 200);
                resultContent = (new com.jdjr.knowledge.test.frame.internal.response.ResultContent.Builder()).response(responseContent).build();
            }
        } catch (IOException var8) {
            LogUtil.error(var8.getMessage());
        }

        return resultContent;
    }

    private String formatResponse(String response) {
        if (response.startsWith("\"") && response.endsWith("\"")) {
            response = response.replace("\\", "");
            response = response.substring(1, response.length() - 1);
        }

        return response;
    }

    private static enum HttpClient {
        INSTANCE;

        private OkHttpClient client;

        private HttpClient() {
            List<Protocol> pList = new ArrayList();
            pList.add(Protocol.HTTP_1_1);
            pList.add(Protocol.HTTP_2);
            this.client = (new OkHttpClient()).newBuilder().protocols(pList).connectTimeout(60L, TimeUnit.SECONDS).readTimeout(60L, TimeUnit.SECONDS).writeTimeout(60L, TimeUnit.SECONDS).build();
        }
    }
}
