package com.jdjr.knowledge.test.frame.internal.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;

import java.util.List;

public class XmlUtil {
    public XmlUtil() {
    }

    public static String xPath(String xml, String xpath) {
        try {
            Document document = DocumentHelper.parseText(xml);
            Node node = document.selectSingleNode(xpath);
            return node.getStringValue();
        } catch (DocumentException var4) {
            LogUtil.error(var4.getMessage());
            return null;
        }
    }

    public static Document document(String xml) {
        Document document = null;

        try {
            document = DocumentHelper.parseText(xml);
        } catch (DocumentException var3) {
            LogUtil.error(var3.getMessage());
        }

        return document;
    }

    public static Node node(String xml, String xpath) {
        try {
            Document document = DocumentHelper.parseText(xml);
            return document.selectSingleNode(xpath);
        } catch (DocumentException var3) {
            LogUtil.error(var3.getMessage());
            return null;
        }
    }

    public static List<Node> nodes(String xml, String xpath) {
        try {
            Document document = DocumentHelper.parseText(xml);
            return document.selectNodes(xpath);
        } catch (DocumentException var3) {
            LogUtil.error(var3.getMessage());
            return null;
        }
    }

    public static String remove(String xml, String xpath) {
        try {
            Document document = DocumentHelper.parseText(xml);
            List<Node> nodes = document.selectNodes(xpath);
            nodes.forEach(Node::detach);
            return document.asXML();
        } catch (DocumentException var4) {
            LogUtil.error(var4.getMessage());
            return null;
        }
    }

    public static String remove(String xml, List<String> xpath) {
        try {
            Document document = DocumentHelper.parseText(xml);
            xpath.forEach((eachPath) -> {
                List<Node> nodes = document.selectNodes(eachPath);
                nodes.forEach(Node::detach);
            });
            return document.asXML();
        } catch (DocumentException var3) {
            LogUtil.error(var3.getMessage());
            return null;
        }
    }
}
