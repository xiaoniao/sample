package com.test.session.filter.custom;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SensitiveFilter implements CustomFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, CustomFilterChain chain) {
        System.out.println("SensitiveFilter 1");
        chain.doFilter(request, response);
        System.out.println("SensitiveFilter 2");
    }
}
