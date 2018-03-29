package com.example.xml.jaxp;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.springframework.core.io.ClassPathResource;
import org.xml.sax.SAXException;

/**
 * 忽略厂商具体实现
 *
 * Created by liuzz on 2018/03/29
 */
public class JaxpSAXDemo {

    public static void main(String[] args) {

        ClassPathResource classPathResource = new ClassPathResource("xml.xml");
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        saxParserFactory.setValidating(true);
        saxParserFactory.setNamespaceAware(true);
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(classPathResource.getInputStream(), new MyDefaultHandler());

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
