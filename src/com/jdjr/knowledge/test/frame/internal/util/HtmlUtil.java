package com.jdjr.knowledge.test.frame.internal.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HtmlUtil {
    public HtmlUtil() {
    }

    public static Element xPath(String html, String xPath) {
        Document doc = Jsoup.parse(html);
        return doc.selectFirst(xPath);
    }

    public static Document document(String html) {
        return Jsoup.parse(html);
    }
}
