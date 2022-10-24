package com.qfedu.servlet;

import com.qfedu.entity.RoleInfo;
import com.qfedu.service.RoleInfoService;
import com.qfedu.service.RoleInfoServiceImpl;
import com.qfedu.util.PageResult;
import com.qfedu.vo.RoleInfoUpdateVo;
import com.qfedu.vo.RoleInfoVo;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/pageFindRole","/toAddRolePage","/addRole","/checkRoleName","/toUpdatePolePage","/updateRole","/deleteRoleById"})
public class RoleInfoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");

        String uri = req.getRequestURI();
        if (uri.endsWith("pageFindRole")) {
            pageFindRole(req, resp);
        } else if(uri.endsWith("toAddRolePage")) { //跳转到添加页面
            toAddRolePage(req,resp);
        } else if(uri.endsWith("addRole")) { //跳转到添加页面
            addRole(req,resp);
        }else if(uri.endsWith("checkRoleName")){
            checkRoleName(req,resp);
        }else if(uri.endsWith("toUpdatePolePage")){
            toUpdatePolePage(req,resp);
        }else if(uri.endsWith("updateRole")){
            updateRole(req,resp);
        }else if (uri.endsWith("deleteRoleById")){
            deleteRoleById(req,resp);
        }
    }

    private void deleteRoleById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取参数
        int roleId = Integer.parseInt(req.getParameter("roleId"));

        //调用业务层处理请求
        RoleInfoService service = new RoleInfoServiceImpl();
        service.deleteByRoleId(roleId);

        //重定向到列表页面请求
        resp.sendRedirect("pageFindRole");
    }

    private void updateRole(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int roleId = Integer.parseInt(req.getParameter("roleId"));
        String roleName = req.getParameter("roleName");
        String[] moduleIds = req.getParameterValues("moduleId");
        System.out.println(roleId);
        //数据类型转换 ，封装
        RoleInfo roleInfo =new RoleInfo(roleId,roleName);

        RoleInfoService service = new RoleInfoServiceImpl();
        service.updateRole(roleInfo,moduleIds);

        resp.sendRedirect("pageFindRole");
    }

    private void toUpdatePolePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int roleId = Integer.parseInt(req.getParameter("roleId"));
        RoleInfoServiceImpl service = new RoleInfoServiceImpl();
        RoleInfoUpdateVo vo = service.findById(roleId);

        req.setAttribute("vo",vo);
        req.getRequestDispatcher("jsp/role/role_update.jsp").forward(req,resp);
    }

    private void checkRoleName(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=utf-8");

        String roleName = req.getParameter("param");
        RoleInfoServiceImpl service = new RoleInfoServiceImpl();
        String checkRoleName = service.checkRoleName(roleName);
        resp.getWriter().write(checkRoleName);
    }

    private void addRole(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取参数
        String roleName = req.getParameter("roleName");
        String[] moduleIds = req.getParameterValues("moduleId");
        //数据类型转化，封装

        //调用业务层，处理请求
        RoleInfoService service = new RoleInfoServiceImpl();
        service.addRole(roleName, moduleIds);
        //重定向
        resp.sendRedirect("pageFindRole");
    }

    private void toAddRolePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/role/role_add.jsp").forward(req,resp);
    }

    //分页查询
    protected void pageFindRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //接受查询参数
        String roleName = req.getParameter("roleName");

        //获取分页查询参数   currentPage pageSize
        String currentPageStr = req.getParameter("currentPage");
        String pageSizeStr = req.getParameter("pageSize");

        //为currentPage pageSize赋默认值
        int currentPage = currentPageStr!=null&&currentPageStr!=""?Integer.parseInt(currentPageStr):1;
        int pageSize = pageSizeStr!=null&&pageSizeStr!=""?Integer.parseInt(pageSizeStr):5;

        //如果需要，进行数据类型转化，封装

        //调用service,处理请求
        RoleInfoService service = new RoleInfoServiceImpl();
        PageResult page = service.pageFind(roleName, currentPage, pageSize);

        req.setAttribute("roleName",roleName);
        req.setAttribute("page",page);

        //转发到角色列表页面
        req.getRequestDispatcher("jsp/role/role_list.jsp").forward(req,resp);
    }



}
