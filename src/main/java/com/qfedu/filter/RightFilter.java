package com.qfedu.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
public class RightFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String uri = req.getRequestURI();

        HttpSession session = req.getSession();
        String moduleNames = (String) session.getAttribute("moduleNames");
        if(uri.contains("login") || uri.endsWith("/") || uri.contains("logout")
                || uri.contains("Module") || uri.contains("index")){
            filterChain.doFilter(req,servletResponse);
            return;
        }else if (uri.contains("Role") && moduleNames.contains("角色管理")){
            filterChain.doFilter(req,servletResponse);
            return;
        }else if (uri.contains("Admin") && moduleNames.contains("管理员管理")){
            filterChain.doFilter(req,servletResponse);
            return;
        }else {
            req.setAttribute("msg","请先登录系统");
            req.getRequestDispatcher("jsp/login.jsp").forward(req, servletResponse);
            return;
        }
    }

    @Override
    public void destroy() {

    }
}
