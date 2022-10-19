package com.qfedu.servlet;

import com.qfedu.dao.AdminInfoDao;
import com.qfedu.dao.AdminInfoDaoImpl;
import com.qfedu.entity.AdminInfo;
import com.qfedu.service.AdminInfoServiceImpl;
import com.qfedu.service.ModuleInfoService;
import com.qfedu.service.ModuleInfoServiceImpl;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if(uri.endsWith("login")){
            login(req,resp);
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String adminCode = req.getParameter("adminCode");
        String password = req.getParameter("password");

        AdminInfoServiceImpl service = new AdminInfoServiceImpl();
        AdminInfo adminInfo = service.login(adminCode, password);

        if(adminInfo !=null){
            HttpSession session = req.getSession();
            session.setAttribute("currentAdmin",adminInfo);

            ModuleInfoService moduleInfoService = new ModuleInfoServiceImpl();
            String moduleNames = moduleInfoService.getModuleNamesByAdminId(adminInfo.getAdminId());
            session.setAttribute("moduleNames",moduleNames);

            req.getRequestDispatcher("jsp/index.jsp").forward(req,resp);

        } else  {
            req.setAttribute("msg","用户名或密码错误");
            req.getRequestDispatcher("jsp/login.jsp").forward(req,resp);
        }
    }
}
