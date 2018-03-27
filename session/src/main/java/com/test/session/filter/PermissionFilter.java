package com.test.session.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(value = 2)
@WebFilter(filterName = "permissionFilter", urlPatterns = "/*")
public class PermissionFilter implements Filter {
    private Logger log = LoggerFactory.getLogger(PermissionFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("INIT");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("REQUEST");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String path = httpServletRequest.getServletPath();
        if (path.equals("/security/user")) {
            httpServletResponse.sendRedirect("/security/login");
            log.info("LOGIN RESPONSE");
            return;
        }
        chain.doFilter(request, response);
        log.info("RESPONSE");
    }

    @Override
    public void destroy() {
        log.info("DESTROY");
    }
}
