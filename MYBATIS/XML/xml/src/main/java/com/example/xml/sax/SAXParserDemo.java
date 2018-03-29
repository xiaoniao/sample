package com.example.xml.sax;

import java.io.IOException;
import org.springframework.core.io.ClassPathResource;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * SAX不会预先读取内容，只会串行的读取数据
 *
 * Created by liuzz on 2018/03/29
 */
public class SAXParserDemo {


    public static void main(String[] args) {

        ClassPathResource classPathResource = new ClassPathResource("xml.xml");

        try {
            // XMLReader xmlReader = new SAXParser();
            XMLReader xmlReader = XMLReaderFactory.createXMLReader("com.sun.org.apache.xerces.internal.parsers.SAXParser"); // 增加可移植性
            InputSource inputSource = new InputSource(classPathResource.getInputStream());
            xmlReader.setContentHandler(new MyContentHandler());
            xmlReader.setErrorHandler(new MyErrorHandler());
            xmlReader.setEntityResolver(new MyEntityResolver());
            xmlReader.setDTDHandler(new MyDTDHandler());
            // xmlReader.setFeature("", true);
            // xmlReader.setProperty("", "");
            xmlReader.parse(inputSource);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }
}
