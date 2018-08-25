package com.test.springioc.resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;

/**
 * 1、ClassPathResource
 * 2、FileSystemResource
 * 3、UrlResource
 * 4、ByteArrayResource
 * 5、ServletContextResource
 * 6、InputStreamResource。
 *
 * https://docs.spring.io/spring/docs/3.0.x/reference/resources.html
 *
 * Created by liuzz on 2018/02/06
 */
public class ResourceTest {


    public static void main(String[] args) {
        classPathResource("application.properties");
        fileSystemResource("/Users/xiezx/Github/sample/spring-ioc/target/classes/application.properties");
        urlResource("http://baidu.com");
        byteArrayResource();
    }

    private static void classPathResource(String path) {
        ClassPathResource classPathResource = new ClassPathResource(path);
        try {
            System.out.println(classPathResource.getFile().getAbsolutePath());
            showContent(classPathResource.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fileSystemResource(String path) {
        FileSystemResource fileSystemResource = new FileSystemResource(path);
        try {
            showContent(fileSystemResource.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void urlResource(String path) {
        try {
            UrlResource urlResource = new UrlResource(path);
            showContent(urlResource.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void byteArrayResource() {
        String name = "hello world!!";
        ByteArrayResource byteArrayResource = new ByteArrayResource(name.getBytes());
        try {
            showContent(byteArrayResource.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showContent(InputStream is) throws IOException {
        if (is == null) {
            System.out.println("InputStream is null");
            return;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        is.close();
        br.close();
    }
}
