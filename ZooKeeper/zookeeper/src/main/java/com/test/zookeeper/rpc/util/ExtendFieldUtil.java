package com.test.zookeeper.rpc.util;

import java.util.HashMap;
import java.util.Map;

/**
 * ��չ�ֶεĹ�����
 * @author zhangwei
 * @version $Id: ExtendFieldUtil.java, v 0.1 2014��7��7�� ����6:04:25 zhangwei Exp $
 */
public class ExtendFieldUtil {
    /**��չ�ֶ��еķָ����**/
    private static String spliteSymbol = "#";

    /**key value �ָ����**/
    private static String equalSymbol  = "=";

    /**
     * ������չ�ֶ�,����#�ָ�ָ��Ľ����key=value
     *
     * @param extendField ��չ�ֶε�����
     * @return
     */
    public static Map<String, String> parseExtend(String extendField) {
        Map<String, String> result = new HashMap<String, String>();
        if (extendField != null && !extendField.trim().isEmpty()) {
            String[] keyValues = extendField.split(spliteSymbol);
            for (String string : keyValues) {
                if (string.contains(equalSymbol)) {
                    // ��ȡ��һ��=��λ��������
                    int index = string.indexOf(equalSymbol);
                    //key
                    String key = string.substring(0, index);
                    String value = string.substring(index + 1);
                    result.put(key, value);
                }

            }
        }
        return result;
    }

    /**
     * ��map ת��Ϊ��չ�ֶεĸ�ʽ�ַ���
     *
     * @param map  key,value �ļ�ֵ��
     * @return ͨ��#������ֵ�Ե��ַ���
     */
    public static String formateMaptoExtend(Map<String, String> map) {
        if (map != null) {
            StringBuilder result = new StringBuilder();
            for (String key : map.keySet()) {
                result.append(key);
                result.append(equalSymbol);
                result.append(map.get(key));
                result.append(spliteSymbol);
            }
            String str = result.toString();
            if (str.endsWith(spliteSymbol)) {
                str = str.substring(0, str.length() - 1);
            }
            return str;
        }
        return null;
    }

    /**
     * ����չ�ֶ��������Ϣ
     *
     * @param extend �Ѿ����ڵ���չ��Ϣ
     * @param key ��������չ��Ϣ��key
     * @param value  ����������
     * @return
     */
    public static String putExtend(String extend, String key, String value) {
        Map<String, String> map = parseExtend(extend);
        map.put(key, value);
        return formateMaptoExtend(map);
    }
}
