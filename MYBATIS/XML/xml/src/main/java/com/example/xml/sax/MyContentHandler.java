package com.example.xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 * Created by liuzz on 2018/03/29
 */
public class MyContentHandler implements ContentHandler {

    private Locator locator;

    @Override
    public void setDocumentLocator(Locator locator) {
        System.out.println("setDocumentLocator");
        this.locator = locator;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("startDocument");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("endDocument");
    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {
        System.out.println("startPrefixMapping prefix:" + prefix + " uri:" + uri);
    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {
        System.out.println("endPrefixMapping prefix:" + prefix);
    }

    /**
     * 属性
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("startElement uri:" + uri + " localName:" +  localName + " qName:" + qName);
        for (int i = 0 ; i < attributes.getLength(); i++) {
            System.out.println("\t" + attributes.getLocalName(i) + "---" + attributes.getValue(i));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("endElement uri:" + uri + " localName:" +  localName + " qName:" + qName);
    }

    /**
     * 内容
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.println("characters " + new String(ch, start, length) + " start:" +  start + " length:" + length);
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
        System.out.println("空白符 ignorableWhitespace " + " start:" +  start + " length:" + length);
    }

    /**
     * 处理指令 PI
     *   这个PI <?xml version="1.0" encoding="UTF-8" ?> 不会回调
     * @param target
     * @param data
     * @throws SAXException
     */
    @Override
    public void processingInstruction(String target, String data) throws SAXException {
        System.out.println("处理指令 PI processingInstruction target:" + target + " data:" + data);
    }

    @Override
    public void skippedEntity(String name) throws SAXException {
        System.out.println("skippedEntity name:" + name);
    }



}
