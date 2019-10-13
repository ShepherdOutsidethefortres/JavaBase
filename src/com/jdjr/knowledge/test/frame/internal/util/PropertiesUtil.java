package com.jdjr.knowledge.test.frame.internal.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    public PropertiesUtil() {
    }

    public static String data(String key) {
        Properties prop = new Properties();
        InputStream in = PropertiesUtil.class.getResourceAsStream("/config.properties");

        try {
            if (in == null) {
                throw new RunExceptionUtil(new String[]{"配置文件（/resources/config.properties）没有编译成功"});
            }

            prop.load(in);
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        return prop.getProperty(key).trim();
    }
}
