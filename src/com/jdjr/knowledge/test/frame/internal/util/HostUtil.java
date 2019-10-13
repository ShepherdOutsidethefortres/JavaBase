package com.jdjr.knowledge.test.frame.internal.util;

import io.leopard.javahost.JavaHost;
import io.leopard.javahost.model.Host;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HostUtil {
    public HostUtil() {
    }

    public static synchronized void updateVirtualDns(String hostIp) {
        if (hostIp != null && !hostIp.trim().isEmpty()) {
            String[] hostInfo = hostIp.trim().replaceAll(" +", " ").split(" ");
            if (hostInfo.length != 2 || !JavaHost.updateVirtualDns(hostInfo[1], hostInfo[0])) {
                System.out.println("虚拟DNS 格式输入有误，示例：'127.0.0.1 localhost'");
            }
        } else {
            System.out.println("虚拟DNS 格式输入有误，示例：'127.0.0.1 localhost'");
        }

    }

    public static synchronized void clearVirtualDnsByHostName(String hostIp) {
        if (hostIp != null && !hostIp.trim().isEmpty()) {
            String[] hostInfo = hostIp.trim().replaceAll(" +", " ").split(" ");
            if (hostInfo.length == 2) {
                clearVirtualDns(hostInfo[1]);
            } else {
                System.out.println("虚拟DNS 格式输入有误，示例：'127.0.0.1 localhost'");
            }
        } else {
            System.out.println("虚拟DNS 格式输入有误，示例：'127.0.0.1 localhost'");
        }

    }

    public static synchronized void clearVirtualDns(String hostName) {
        if (JavaHost.getDns() == null || !JavaHost.getDns().remove(hostName)) {
            System.out.println("虚拟DNS 格式输入有误，示例：'localhost'");
        }

    }

    public static synchronized void clearAllVirtualDns() {
        Boolean bRet = true;
        Host host;
        if (JavaHost.getDns() != null) {
            for(Iterator var1 = JavaHost.getDns().list().iterator(); var1.hasNext(); bRet = bRet & JavaHost.getDns().remove(host.getHost())) {
                host = (Host)var1.next();
            }
        }

        if (!bRet) {
            System.out.println("虚拟DNS清理失败！");
        }

    }

    public static synchronized void addHost(String hostIp) {
        if (hostIp != null && !hostIp.trim().isEmpty()) {
            String[] hostInfo = hostIp.trim().replaceAll(" +", " ").split(" ");
            if (hostInfo.length == 2) {
                try {
                    List<String> newHostLines = new ArrayList();
                    Iterator var3 = FileUtils.readLines(new File(getHostFile()), "UTF-8").iterator();

                    while(var3.hasNext()) {
                        String hostLine = (String)var3.next();
                        if (hostLine.startsWith("#")) {
                            newHostLines.add(hostLine);
                        } else if (!hostLine.contains(hostInfo[1])) {
                            newHostLines.add(hostLine);
                        }
                    }

                    newHostLines.add(hostInfo[0] + " " + hostInfo[1]);
                    FileUtils.writeLines(new File(getHostFile()), "UTF-8", newHostLines);
                    clearCache(hostInfo[1]);
                } catch (IOException var5) {
                    LogUtil.error(var5.getMessage());
                } catch (IllegalAccessException var6) {
                    var6.printStackTrace();
                } catch (NoSuchFieldException var7) {
                    var7.printStackTrace();
                }
            }

        } else {
            throw new RunExceptionUtil(new String[]{"host 格式输入有误，示例：'127.0.0.1 localhost'"});
        }
    }

    public static void clearCache(String host) throws NoSuchFieldException, IllegalAccessException {
        Class clazz = InetAddress.class;
        Field cacheField = clazz.getDeclaredField("addressCache");
        cacheField.setAccessible(true);
        Object obj = cacheField.get(clazz);
        Class cacheClazz = obj.getClass();
        Field cachePolicyField = cacheClazz.getDeclaredField("type");
        Field cacheMapField = cacheClazz.getDeclaredField("cache");
        cachePolicyField.setAccessible(true);
        cacheMapField.setAccessible(true);
        Map cacheMap = (Map)cacheMapField.get(obj);
        System.out.println(cacheMap);
        cacheMap.remove(host);
    }

    public static synchronized void deleteHost(String hostIp) {
        if (hostIp != null && !hostIp.trim().isEmpty()) {
            String[] hostInfo = hostIp.trim().replaceAll(" +", " ").split(" ");
            if (hostInfo.length == 2) {
                try {
                    List<String> newHostLines = new ArrayList();
                    Iterator var3 = FileUtils.readLines(new File(getHostFile()), "UTF-8").iterator();

                    while(var3.hasNext()) {
                        String hostLine = (String)var3.next();
                        if (!hostLine.equals(hostInfo[0] + " " + hostInfo[1])) {
                            newHostLines.add(hostLine);
                        }
                    }

                    FileUtils.writeLines(new File(getHostFile()), "UTF-8", newHostLines);
                    clearCache(hostInfo[1]);
                } catch (IOException var5) {
                    LogUtil.error(var5.getMessage());
                } catch (IllegalAccessException var6) {
                    var6.printStackTrace();
                } catch (NoSuchFieldException var7) {
                    var7.printStackTrace();
                }
            }

        } else {
            throw new RunExceptionUtil(new String[]{"host 格式输入有误，示例：'127.0.0.1 localhost'"});
        }
    }

    private static String getHostFile() {
        String os = System.getProperty("os.name").toLowerCase();
        return os.contains("windows") ? "C:/Windows/System32/drivers/etc/hosts" : "/etc/hosts";
    }
}
