package com.jdjr.knowledge.test.frame.internal.util;

import java.util.Map;

public class TokenParserUtil {
    public TokenParserUtil() {
    }

    public static String parse(String text) {
        if (text != null && !text.isEmpty()) {
            String openToken = "${";
            int start = text.indexOf(openToken, 0);
            if (start == -1) {
                return text;
            } else {
                char[] src = text.toCharArray();
                int offset = 0;
                StringBuilder builder = new StringBuilder();

                for(StringBuilder expression = null; start > -1; start = text.indexOf(openToken, offset)) {
                    if (start > 0 && src[start - 1] == '\\') {
                        builder.append(src, offset, start - offset - 1).append(openToken);
                        offset = start + openToken.length();
                    } else {
                        if (expression == null) {
                            expression = new StringBuilder();
                        } else {
                            expression.setLength(0);
                        }

                        builder.append(src, offset, start - offset);
                        offset = start + openToken.length();
                        String closeToken = "}";

                        int end;
                        for(end = text.indexOf(closeToken, offset); end > -1; end = text.indexOf(closeToken, offset)) {
                            if (end <= offset || src[end - 1] != '\\') {
                                expression.append(src, offset, end - offset);
                                break;
                            }

                            expression.append(src, offset, end - offset - 1).append(closeToken);
                            offset = end + closeToken.length();
                        }

                        if (end == -1) {
                            builder.append(src, start, src.length - start);
                            offset = src.length;
                        } else {
                            builder.append(handleToken(expression.toString()));
                            offset = end + closeToken.length();
                        }
                    }
                }

                if (offset < src.length) {
                    builder.append(src, offset, src.length - offset);
                }

                return builder.toString();
            }
        } else {
            return "";
        }
    }

    private static Object handleToken(String content) {
        Map<String, Object> variables = ThreadLocalUtil.getVariableData();
        return variables != null && variables.containsKey(content) ? variables.get(content) : "${" + content + "}";
    }
}
