package com.test.session.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Order(value = 1)
@WebFilter(filterName = "headerFilter", urlPatterns = "/*")
public class HeaderFilter implements Filter {
    private Logger log = LoggerFactory.getLogger(HeaderFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("INIT");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("FILTER");
        chain.doFilter(request, response);
        log.info("FILTER2");
    }

    @Override
    public void destroy() {
        log.info("DESTROY");
    }
}
