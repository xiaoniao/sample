package com.example.shardingtable.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by liuzz on 2018/05/30
 */
public class CodeUtil {

    public static final String DATE_SIMPLE_FORMAT_STRING = "yyyyMMdd";

    public static String getCodeByHead(String head){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(head); // 3位
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String hashCode = split(String.valueOf(uuid.hashCode())); // 9位
        stringBuffer.append(hashCode);
        stringBuffer.append(parseSimleDate(new Date())); // 8位
        return stringBuffer.toString();
    }

    public static String parseSimleDate(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat(DATE_SIMPLE_FORMAT_STRING);
        return df.format(date);
    }

    // 字符串转换位9位
    private static String split(String hashCode) {
        int length = 9;
        hashCode = hashCode.replaceAll("-", "");

        if (hashCode.length() == length) {
            return hashCode;
        }
        if (hashCode.length() > length) {
            return hashCode.substring(0, length);
        }
        if (hashCode.length() < length) {
            int range = length - hashCode.length();
            for (int i = 0; i < range; i++) {
                hashCode += "0";
            }
            return hashCode;
        }
        return "";
    }
}
