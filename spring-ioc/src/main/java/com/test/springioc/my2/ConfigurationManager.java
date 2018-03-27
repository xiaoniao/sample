package com.test.springioc.my2;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


/**
 * Created by liuzz on 2018/02/05
 */
public class ConfigurationManager {

    /**
     * 根据指定的路径读取配置文件
     *
     * @param path 配置文件路径
     */
    public static Map<String, Bean> getBeanConfig(String path) {
        Map<String, Bean> result = new HashMap<>();
        SAXReader reader = new SAXReader();
        InputStream is = ConfigurationManager.class.getResourceAsStream(path);
        Document doc;
        try {
            doc = reader.read(is);
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException("加载配置文件出错");
        }

        Element beans = doc.getRootElement();
        for (Iterator<Element> beansList = beans.elementIterator(); beansList.hasNext(); ) {
            Element ele = beansList.next();
            Bean bean = new Bean();
            String name = ele.attributeValue("name");
            bean.setName(name == null ? ele.attributeValue("id") : name);

            bean.setClassName(ele.attributeValue("class"));
            String scope = ele.attributeValue("scope");
            if (scope != null && scope.trim().length() > 0) {
                bean.setScope(scope);
            }

            List<Element> propNodes = ele.elements("property");
            if (propNodes != null) {
                for (Element prop : propNodes) {
                    Property p = new Property();
                    p.setName(prop.attributeValue("name"));
                    p.setValue(prop.attributeValue("value"));
                    p.setRef(prop.attributeValue("ref"));
                    bean.getProperties().add(p);
                }
            }
            result.put(bean.getName(), bean);
        }
        return result;
    }
}
