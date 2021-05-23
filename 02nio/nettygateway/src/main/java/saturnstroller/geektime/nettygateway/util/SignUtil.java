package saturnstroller.geektime.nettygateway.util;

import io.netty.util.internal.StringUtil;
import org.apache.commons.codec.digest.DigestUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class SignUtil {
    final static String KEY = "netty;23#^";

    public static boolean checkSignForPost(Object object, String sign) {
        if (null == object) {
            return false;
        }
        Map<String, String> param = new HashMap<String, String>();
        param.putAll(signMap(object, param));
        // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String mysign = DigestUtils.md5Hex((createLinkString(param) + KEY).getBytes(StandardCharsets.UTF_8));
        System.out.println("请求内容生成签名：" + mysign);
        return mysign.equals(sign);
    }

    public static boolean checkSignForGet(String text, String sign) {
        if (StringUtil.isNullOrEmpty(text)) {
            return false;
        }
        String mysign = DigestUtils.md5Hex((text + KEY).getBytes(StandardCharsets.UTF_8));
        System.out.println("请求内容生成签名：" + mysign);
        return mysign.equals(sign);
    }

    public static String createLinkString(Map<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }

    private static Map<String, String> signMap(Object object, Map<String, String> signMap) {
        if (null == object) {
            return signMap;
        }
        Field[] fs = object.getClass().getDeclaredFields();
        for (Field field : fs) {
            if (field.getType().isAssignableFrom(String.class)) {
                Object obj = getFieldValueByName(field.getName(), object);
                if (null == obj) {
                    continue;
                }
                signMap.put(field.getName(), obj.toString());
            } else {
                if (field.getType().isPrimitive()) {
                    continue;
                }
                signMap(getFieldValueByName(field.getName(), object), signMap);
            }
        }
        return signMap;
    }

    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (Exception e) {
            return null;
        }
    }
}
