package io.netty.example.definitive.serialize.java;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by liuzhuang on 6/24/18.
 *
 * 1. 无法跨语言
 * 2. 序列化后占得空间太大
 * 3. 性能太低
 *
 */
public class TestUserInfo {

    public static void main(String[] args) throws IOException {

        UserInfo userInfo = new UserInfo().buildUserName("jack").buildUserId(1);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(userInfo);
        objectOutputStream.flush();
        objectOutputStream.close();

        byte[] result = byteArrayOutputStream.toByteArray();
        System.out.println("java Serializable length: " + result.length);
        byteArrayOutputStream.close();

        System.out.println("base length:" + userInfo.codeC().length);
    }
}
