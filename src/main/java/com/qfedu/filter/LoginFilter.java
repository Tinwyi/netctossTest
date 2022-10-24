package com.qfedu.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
//@WebFilter(urlPatterns = {"/*"})
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        /*
        放行登录
         */
        String uri = req.getRequestURI();

        if(uri.contains("login")||uri.endsWith("/")){
            filterChain.doFilter(req,servletResponse);
            return;
        }
        HttpSession session = req.getSession();
        if(session.getAttribute("currentAdmin") == null){
            req.setAttribute("msg","请登录系统");
            req.getRequestDispatcher("jsp/login.jsp").forward(req,servletResponse);
            return;
        }
        filterChain.doFilter(req,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
