package com.example.xml.sax;

import org.xml.sax.DTDHandler;
import org.xml.sax.SAXException;

/**
 * Created by liuzz on 2018/03/29
 */
public class MyDTDHandler implements DTDHandler {

    @Override
    public void notationDecl(String name, String publicId, String systemId) throws SAXException {
        System.out.println("DTD >>> notationDecl");
    }

    @Override
    public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) throws SAXException {
        System.out.println("DTD >>> unparsedEntityDecl");
    }
}
