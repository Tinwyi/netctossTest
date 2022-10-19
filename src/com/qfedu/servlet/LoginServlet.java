package com.qfedu.servlet;

import com.qfedu.entity.AdminInfo;
import com.qfedu.service.AdminInfoServiceImpl;
import com.qfedu.service.ModuleInfoServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login","/logout","/indexPage"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();

        if(uri.endsWith("login")){
            login(req,resp);
        }else if(uri.endsWith("logout")){
            logout(req,resp);
        }else if(uri.endsWith("indexPage")){
            req.getRequestDispatcher("jsp/index.jsp").forward(req,resp);
        }
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        session.invalidate();

        req.getRequestDispatcher("jsp/login.jsp").forward(req,resp);
    }

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String adminCode = req.getParameter("adminCode");
        String password = req.getParameter("password");

        AdminInfoServiceImpl service = new AdminInfoServiceImpl();
        AdminInfo admin = service.login(adminCode, password);

        //判断是否登录成功
        if(admin!=null){
            //把当前用户对象，放到Session对象
            HttpSession session = req.getSession();
            session.setAttribute("currentAdmin",admin);

            ModuleInfoServiceImpl moduleInfoService = new ModuleInfoServiceImpl();
            String moduleNames = moduleInfoService.getModuleNamesByAdminId(admin.getAdminId());
            session.setAttribute("moduleNames",moduleNames);

            req.getRequestDispatcher("jsp/index.jsp").forward(req,resp);
        }else {
            req.setAttribute("msg","用户名或密码错误");
            req.getRequestDispatcher("jsp/login.jsp").forward(req,resp);
        }
    }
}
