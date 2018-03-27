package com.test.springioc;

import com.test.springioc.dao.AddressDAO;
import com.test.springioc.dao.BankDAO;
import com.test.springioc.dao.UserDAO;
import com.test.springioc.ioc.MyClassPathXMLApplicationContext;
import com.test.springioc.service.impl.UserServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringIocApplication {


    public static void main(String[] args) {
        myIOC();
        springIOC();
    }

    private static void myIOC() {
        MyClassPathXMLApplicationContext path = new MyClassPathXMLApplicationContext("configAnnotation.xml");
        UserServiceImpl userService = (UserServiceImpl) path.getBean("userService");
        userService.show();
    }

    private static void springIOC() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationAnnotation.xml");

        BankDAO bankDAO = (BankDAO) beanFactory.getBean("bankDAO");
        bankDAO.show();

        UserDAO userDAO = (UserDAO) beanFactory.getBean("userDAO");
        userDAO.show();

        AddressDAO addressDAO = (AddressDAO) beanFactory.getBean("addressDAO");
        addressDAO.show();

        UserServiceImpl u = (UserServiceImpl) beanFactory.getBean("userService");
        u.show();
    }
}