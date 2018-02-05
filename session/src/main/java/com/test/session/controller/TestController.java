package com.test.session.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TestController extends AbstractController {

    @RequestMapping(value = "/test")
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("aaaaaaaaaaaa");
        ModelAndView modelAndView = new ModelAndView("hello");
        modelAndView.addObject("name", "jack");
        return modelAndView;
    }
}
