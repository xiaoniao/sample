package com.test.session.filter.custom;

import javax.servlet.*;

public interface CustomFilter {

    void doFilter(ServletRequest request, ServletResponse response, CustomFilterChain chain);
}
