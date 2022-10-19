package com.qfedu.servlet;

import com.qfedu.entity.AdminInfo;
import com.qfedu.service.AdminInfoServiceImpl;
import com.qfedu.service.RoleInfoService;
import com.qfedu.service.RoleInfoServiceImpl;
import com.qfedu.util.PageResult;
import com.qfedu.vo.AdminRoleVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/pageFindAdmin","/addAdmin","/toAddAdmin","/checkAdminCode"
        ,"/toModifyAdmin","/modifyAdmin","/resetAdminPassword","/deleteAdmin"})
public class AdminInfoServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String uri = req.getRequestURI();
        if(uri.endsWith("pageFindAdmin")){
            pageFindAdmin(req,resp);
        }else if(uri.endsWith("addAdmin")){
            addAdmin(req,resp);
        }else if(uri.endsWith("toAddAdmin")){
            req.getRequestDispatcher("jsp/admin/admin_add.jsp").forward(req,resp);
        }else if(uri.endsWith("checkAdminCode")){
            checkAdminCode(req,resp);
        }else if(uri.endsWith("toModifyAdmin")){
            toModifyAdmin(req,resp);
        }else if(uri.endsWith("modifyAdmin")){
            modifyAdmin(req,resp);
        }else if(uri.endsWith("resetAdminPassword")){
            resetAdminPassword(req,resp);
        }else if(uri.endsWith("deleteAdmin")){
            deleteAdmin(req,resp);
        }
    }

    private void deleteAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer adminId = Integer.valueOf(req.getParameter("adminId"));

        AdminInfoServiceImpl service = new AdminInfoServiceImpl();
        service.deleteAdmin(adminId);

        resp.sendRedirect("pageFindAdmin");
    }

    private void resetAdminPassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer adminId = Integer.valueOf(req.getParameter("adminId"));

        AdminInfoServiceImpl service = new AdminInfoServiceImpl();
        service.resetPassword(adminId);

        resp.sendRedirect("pageFindAdmin");
    }

    private void modifyAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String adminIdStr = req.getParameter("adminId");
        String adminCode = req.getParameter("adminCode");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String telephone = req.getParameter("telephone");
        String email = req.getParameter("email");
        String[] roleIds = req.getParameterValues("roleId");

        Integer adminId = Integer.valueOf(adminIdStr);
        AdminInfo adminInfo = new AdminInfo(adminId,adminCode, password, name, telephone, email);

        AdminInfoServiceImpl service = new AdminInfoServiceImpl();
        service.modifyAdminRole(adminInfo,roleIds);

        resp.sendRedirect("pageFindAdmin");
    }

    private void toModifyAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Integer id = Integer.valueOf(req.getParameter("adminId"));

        AdminInfoServiceImpl service = new AdminInfoServiceImpl();
        AdminRoleVo admin = service.findById(id);

        req.setAttribute("admin",admin);

        req.getRequestDispatcher("jsp/admin/admin_modify.jsp").forward(req,resp);
    }

    //验证管理员账号是否重复
    private void checkAdminCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取参数
        String adminCode = req.getParameter("param"); // validform规定接受参数的名称
        String code = req.getParameter("adminCode");
        //数据类型转化，封装

        //调用业务层，处理请求
        RoleInfoService service = new RoleInfoServiceImpl();
        String result = service.checkRoleName(adminCode);
        if(adminCode.equals(code)){
            result = "y";
        }
        resp.getWriter().write(result);

    }

    //新增
    private void addAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String adminCode = req.getParameter("adminCode");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String telephone = req.getParameter("telephone");
        String email = req.getParameter("email");
        String[] roleIds = req.getParameterValues("roleId");

        AdminInfo adminInfo = new AdminInfo(adminCode, password, name, telephone, email);

        AdminInfoServiceImpl service = new AdminInfoServiceImpl();
        service.addAdmin(adminInfo,roleIds);

        resp.sendRedirect("pageFindAdmin");
    }

    //查询
    protected void pageFindAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String roleIdStr = req.getParameter("roleId");
        Integer roleId = roleIdStr==null||roleIdStr==""?null:Integer.parseInt(roleIdStr);
        String currentPageStr = req.getParameter("currentPage");
        String pageSizeStr = req.getParameter("pageSize");

        int currentPage = currentPageStr==null||currentPageStr==""?1:Integer.parseInt(currentPageStr);
        int pageSize = pageSizeStr==null||pageSizeStr==""?5:Integer.parseInt(pageSizeStr);

        AdminInfoServiceImpl service = new AdminInfoServiceImpl();
        PageResult page = service.pageFind(roleId, name,currentPage, pageSize);

        //传参
        req.setAttribute("page",page);
        req.setAttribute("name",name);
        req.setAttribute("roleId",roleId);

        //转发
        req.getRequestDispatcher("jsp/admin/admin_list.jsp").forward(req,resp);
    }
}

