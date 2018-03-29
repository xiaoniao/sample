package com.example.xml.sax;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Created by liuzz on 2018/03/29
 */
public class MyErrorHandler implements ErrorHandler {

    @Override
    public void warning(SAXParseException e) throws SAXException {
        System.err.println("*****warning");
        // e.printStackTrace();
        throw e;
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        System.err.println("*****error");
        // e.printStackTrace();
        throw e;
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        System.err.println("*****fatalError");
        // e.printStackTrace();
        throw e;
    }
}
