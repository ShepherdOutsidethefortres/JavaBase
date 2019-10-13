package com.jdjr.knowledge.test.frame.internal.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {
    public TextUtil() {
    }

    public static List<String> rex(String text, String rex) {
        List<String> variable = new ArrayList();
        Pattern pattern = Pattern.compile(rex);
        Matcher matcher = pattern.matcher(text);

        while(matcher.find()) {
            variable.add(matcher.group());
        }

        return variable;
    }
}
