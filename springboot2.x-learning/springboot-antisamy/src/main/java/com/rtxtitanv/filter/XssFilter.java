package com.rtxtitanv.filter;

import com.rtxtitanv.wrapper.XssRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.filter.XssFilter
 * @description XSS过滤器
 * @date 2021/8/23 15:01
 */
public class XssFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        // 拦截请求，处理XSS过滤
        chain.doFilter(new XssRequestWrapper((HttpServletRequest)request), response);
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }
}