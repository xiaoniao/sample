package com.test.session.filter.custom;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HTMLFilter implements CustomFilter {

    /**
     * 父调子 子调父
     *
     * 父：CustomApplicationFilterChain
     * 子：HTMLFilter
     *
     * @param request
     * @param response
     * @param chain
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, CustomFilterChain chain) {
        System.out.println("HTMLFilter 1");
        chain.doFilter(request, response);
        System.out.println("HTMLFilter 2");
    }
}
