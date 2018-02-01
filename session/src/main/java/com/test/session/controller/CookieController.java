package com.test.session.controller;

import java.util.Enumeration;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuzz on 2018/02/01
 */
@RestController
public class CookieController {

    private Logger log = LoggerFactory.getLogger(CookieController.class);

    @RequestMapping(value = "/test/cookie")
    public String cookie(HttpServletRequest request, HttpSession httpSession) {
        for (Cookie cookie : request.getCookies()) {
            log.info("name:{} - value:{}", cookie.getName(), cookie.getValue());
        }

        log.info("sessionId:{}", httpSession.getId());
        Enumeration<String> stringEnumeration = httpSession.getAttributeNames();
        while (stringEnumeration.hasMoreElements()) {
            log.info("attributeName: ", stringEnumeration.nextElement());
        }
        log.info("=====================================================");
        return "";
    }
}
