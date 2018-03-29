package com.example.xml.sax;

import java.io.IOException;
import org.springframework.core.io.ClassPathResource;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Created by liuzz on 2018/03/29
 */
public class MyEntityResolver implements EntityResolver {

    @Override
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
        System.out.println("EntityResolver -- publicId:" + publicId + " systemId:" + systemId);
        return new InputSource(new ClassPathResource("mapper.dtd").getInputStream());
    }
}
