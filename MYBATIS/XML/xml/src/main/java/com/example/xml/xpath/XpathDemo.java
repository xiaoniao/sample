package com.example.xml.xpath;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Created by liuzz on 2018/03/29
 */
public class XpathDemo {

    public static void main(String[] args) {
        ClassPathResource classPathResource = new ClassPathResource("xml.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(classPathResource.getInputStream());

            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            evaluate(xPath, "/mapper/sql", doc.getDocumentElement());

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    private static void evaluate(XPath xPath, String expression, Object root) throws XPathExpressionException {
        Object result = xPath.evaluate(expression, root, XPathConstants.STRING);
        System.out.println(result);
    }
}
