package com.jdjr.knowledge.test.frame.internal.request.impl;

import com.jayway.jsonpath.Filter;
import com.jdjr.knowledge.test.frame.internal.config.Config;
import com.jdjr.knowledge.test.frame.internal.response.Response;
import com.jdjr.knowledge.test.frame.internal.util.*;

import java.util.HashMap;

public class ResponseImpl {
    private Config config = null;

    public ResponseImpl(Config config) {
        this.config = config;
    }

    public Response response(String responseBody, HashMap<String, String> headers) {
        switch(this.config.getResponseType()) {
            case JSON:
                return ResponseImpl.Operate.JSON.apply(responseBody, headers);
            case HTML:
                return ResponseImpl.Operate.HTML.apply(responseBody, headers);
            case TEXT:
                return ResponseImpl.Operate.TEXT.apply(responseBody, headers);
            case XML:
                return ResponseImpl.Operate.XML.apply(responseBody, headers);
            default:
                return null;
        }
    }

    private static enum Operate {
        JSON {
            public Response apply(final String responseBody, final HashMap<String, String> headers) {
                return new Response() {
                    public String string() {
                        return responseBody;
                    }

                    public HashMap<String, String> headers() {
                        return headers;
                    }

                    public Response variable(String name, String path) {
                        (<VAR_NAMELESS_ENCLOSURE>.new Variable((SyntheticClass_1)null)).json(name, path, responseBody);
                        return this;
                    }
                };
            }
        },
        HTML {
            public Response apply(final String responseBody, final HashMap<String, String> headers) {
                return new Response() {
                    public String string() {
                        return responseBody;
                    }

                    public HashMap<String, String> headers() {
                        return headers;
                    }

                    public Response variable(String name, String path) {
                        (<VAR_NAMELESS_ENCLOSURE>.new Variable((SyntheticClass_1)null)).html(name, path, responseBody);
                        return this;
                    }
                };
            }
        },
        XML {
            public Response apply(final String responseBody, final HashMap<String, String> headers) {
                return new Response() {
                    public String string() {
                        return responseBody;
                    }

                    public HashMap<String, String> headers() {
                        return headers;
                    }

                    public Response variable(String name, String path) {
                        (<VAR_NAMELESS_ENCLOSURE>.new Variable((SyntheticClass_1)null)).xml(name, path, responseBody);
                        return this;
                    }
                };
            }
        },
        TEXT {
            public Response apply(final String responseBody, final HashMap<String, String> headers) {
                return new Response() {
                    public String string() {
                        return responseBody;
                    }

                    public HashMap<String, String> headers() {
                        return headers;
                    }

                    public Response variable(String name, String path) {
                        (<VAR_NAMELESS_ENCLOSURE>.new Variable((SyntheticClass_1)null)).text(name, path, responseBody);
                        return this;
                    }
                };
            }
        };

        private Operate() {
        }

        public abstract Response apply(String var1, HashMap<String, String> var2);

        private class Variable {
            private Variable() {
            }

            public void json(String name, String path, String response) {
                try {
                    if (JsonUtil.jsonPath(response, path, new Filter[0]) == null) {
                        throw new RunExceptionUtil(new String[]{"JsonPath有误，无法获取值"});
                    }

                    ThreadLocalUtil.setVariableData(name, JsonUtil.jsonPath(response, path, new Filter[0]));
                } catch (RunExceptionUtil var5) {
                    LogUtil.error(var5.getMessage());
                }

            }

            private void xml(String name, String path, String response) {
                try {
                    if (XmlUtil.xPath(response, path) == null) {
                        throw new RunExceptionUtil(new String[]{"xPath有误，无法获取值"});
                    }

                    ThreadLocalUtil.setVariableData(name, XmlUtil.xPath(response, path));
                } catch (RunExceptionUtil var5) {
                    LogUtil.error(var5.getMessage());
                }

            }

            private void text(String name, String path, String response) {
                try {
                    if (TextUtil.rex(response, path).size() == 0) {
                        throw new RunExceptionUtil(new String[]{"无匹配数据，无法获取值"});
                    }

                    ThreadLocalUtil.setVariableData(name, TextUtil.rex(response, path));
                } catch (RunExceptionUtil var5) {
                    LogUtil.error(var5.getMessage());
                }

            }

            private void html(String name, String path, String response) {
                try {
                    if (HtmlUtil.xPath(response, path) == null) {
                        throw new RunExceptionUtil(new String[]{"xPath有误，无法获取值"});
                    }

                    ThreadLocalUtil.setVariableData(name, HtmlUtil.xPath(response, path));
                } catch (RunExceptionUtil var5) {
                    LogUtil.error(var5.getMessage());
                }

            }
        }
    }
}
