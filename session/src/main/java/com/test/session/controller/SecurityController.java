package com.test.session.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/security")
public class SecurityController {
    private Logger log = LoggerFactory.getLogger(SecurityController.class);

    @RequestMapping(path = "/anony")
    public String anonymity() {
        log.info("------------------------anonymity");
        return "anonymity";
    }

    @RequestMapping(path = "/login")
    public String login() {
        log.info("------------------------login");
        return "login";
    }

    @RequestMapping(path = "/user")
    public String user() {
        log.info("------------------------user");
        return "user";
    }
}
