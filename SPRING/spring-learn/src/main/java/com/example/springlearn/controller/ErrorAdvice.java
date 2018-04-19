package com.example.springlearn.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice("com.example.springlearn.controller")
public class ErrorAdvice {

    @ExceptionHandler
    @ResponseBody
    public Map<String, String> exceptionHandler(Exception e) {
        e.printStackTrace();
        Map<String, String> map = new HashMap<>();
        map.put("msg", e.getMessage());
        return map;
    }
}
