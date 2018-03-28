package com.example.mybatisTest;

import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.parsing.XPathParser;
import org.springframework.core.io.ClassPathResource;

import java.io.*;

public class ParsingTest {

    public static void main(String[] args) {
        ClassPathResource classPathResource = new ClassPathResource("ActivityDOMapper.xml");
        try {
            XPathParser xPathParser = new XPathParser(classPathResource.getInputStream());

//            System.out.println(xPathParser.evalString("/!DOCTYPE"));

            System.out.println(xPathParser.evalString("/mapper/sql"));

            XNode xNode = xPathParser.evalNode("/mapper/resultMap");
            System.out.println(xNode.getStringAttribute("id"));
            System.out.println(xNode.getStringAttribute("type"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printXmlContent() {
        ClassPathResource classPathResource = new ClassPathResource("ActivityDOMapper.xml");
        try {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(classPathResource.getInputStream()));
            String read = null;
            while ((read = bufferedReader.readLine()) != null) {
                System.out.println(read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
