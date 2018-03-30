package com.example.mybatisTest;

import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.parsing.XPathParser;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class XpathDemo {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = new ClassPathResource("nodelet_test.xml").getInputStream();

        XPathParser parser = new XPathParser(inputStream, false, null, null);
        Properties properties = new Properties();
        properties.put("id_var", "THIS IS ME");
        parser.setVariables(properties);

        /**
         * 替换${id_var}属性
         */
        System.out.println(parser.evalString("/employee/@id")); // 查询employee的id属性
        System.out.println(parser.evalNode("/employee/@id").getName());
        System.out.println(parser.evalNode("/employee/@id").getStringBody());
        System.out.println(parser.evalNode("/employee/@id").toString().trim());

        System.out.println(parser.evalLong("/employee/birth_date/year"));
        System.out.println(parser.evalShort("/employee/birth_date/month"));
        System.out.println(parser.evalInteger("/employee/birth_date/day"));
        System.out.println(parser.evalFloat("/employee/height"));
        System.out.println(parser.evalDouble("/employee/height"));
        System.out.println(parser.evalBoolean("/employee/active"));
        System.out.println(parser.evalNodes("/employee/*").size());

        XNode node = parser.evalNode("/employee/height");
        System.out.println(node.getPath());
        System.out.println(node.getValueBasedIdentifier());
        inputStream.close();
    }
}