package com.example.springlearn.basic.aliasfor.guide;

/**
 */
//@ContextConfiguration("/root/java")
//@XmlTestConfig(xmlFiles = {"a.file", "b.file"})
//@MyTestConfig(value = {"c.file", "d.file"})
@GroovyOrXmlTestConfig(groovy = {"a.groovy", "b.groovy"})
public class Example {

    public void show() {
        System.out.println("Example");
    }
}
