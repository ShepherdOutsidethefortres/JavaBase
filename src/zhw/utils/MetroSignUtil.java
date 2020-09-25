package zhw.utils;

import com.google.common.collect.Lists;
import zhw.domain.BaseRequestVo;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MetroSignUtil {

    public static final String NONCE = "nonce";

    public static final String TIMESTAMP = "timestamp";

    /**
     * 参数拼接为字符串
     *
     * @param isForSign->true:用作签名;false:用作请求
     * @return
     */
    public static String toBodyString(Object originObj, Boolean isForSign) {
        StringBuilder sb = new StringBuilder();
        List<Field> fields = Lists.newArrayList();
        wrapperClassFields(originObj.getClass(), fields);
        List<String> paramList = new ArrayList<>();
        for (Field field : fields) {
            Object obj;
            try {
                field.setAccessible(true);
                String fieldName = field.getName();
                obj = field.get(originObj);
                //参数串(参数名=编码后的参数值)按字典顺序排序
                if (obj != null) {
                    try {
                        if (isForSign) {
                            if (!NONCE.equals(fieldName) && !TIMESTAMP.equals(fieldName)) {
                                paramList.add(fieldName + "=" + URLEncoder.encode(String.valueOf(obj), "UTF-8"));
                            }
                        } else {
                            paramList.add(fieldName + "=" + URLEncoder.encode(String.valueOf(obj), "UTF-8"));
                        }
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        Collections.sort(paramList);
        for (int i = 0; i < paramList.size(); i++) {
            if (i == paramList.size() - 1) {
                sb.append(paramList.get(i));
            } else {
                sb.append(paramList.get(i)).append("&");
            }
        }
        return sb.toString();
    }

    public static String toSignString(String bodyStr, String key, BaseRequestVo baseRequestVo) {
        StringBuffer sb = new StringBuffer();
        sb.append(bodyStr);
        sb.append(key);
        String nonce = baseRequestVo.getNonce();
        String timeStamp = baseRequestVo.getTimestamp();
        sb.append(nonce);
        sb.append(timeStamp);
        System.out.println("nonce："+nonce);
        System.out.println("timestamp："+timeStamp);
        return sb.toString();
    }

    private static void wrapperClassFields(Class currClass, List<Field> fieldList) {
        Field[] fields = currClass.getDeclaredFields();
        fieldList.addAll(Arrays.asList(fields));
        if (currClass.getSuperclass() != null) {
            wrapperClassFields(currClass.getSuperclass(), fieldList);
        }
    }
}
