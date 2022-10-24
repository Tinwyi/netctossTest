package com.qfedu.servlet;

import com.qfedu.entity.Cost;
import com.qfedu.service.CostServiceImpl;
import com.qfedu.util.PageResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/pageFindCost","/addCost","/toAddCost","/checkCostName",
        "/findCostById","/toModifyCost","/modifyCost","/enableCost","/deleteCost"})
public class CostServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String uri = req.getRequestURI();

        if(uri.endsWith("pageFindCost")){
            pageFindCost(req,resp);
        }else if(uri.endsWith("toAddCost")){
            req.getRequestDispatcher("jsp/cost/cost_add.jsp").forward(req,resp);
        }else if(uri.endsWith("addCost")){
            addCost(req,resp);
        }else if(uri.endsWith("checkCostName")){
            checkCostName(req,resp);
        }else if(uri.endsWith("findCostById")){
            findCostById(req,resp);
        }else if(uri.endsWith("toModifyCost")){
            toModifyCost(req,resp);
        }else if(uri.endsWith("modifyCost")){
            modifyCost(req,resp);
        }else if(uri.endsWith("enableCost")){
            enableCost(req,resp);
        }else if(uri.endsWith("deleteCost")){
            deleteCost(req,resp);
        }
    }

    private void deleteCost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer costId = Integer.valueOf(req.getParameter("costId"));

        CostServiceImpl service = new CostServiceImpl();
        service.deleteCost(costId);

        resp.sendRedirect("pageFindCost");
    }

    private void enableCost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer costId = Integer.valueOf(req.getParameter("costId"));

        CostServiceImpl service = new CostServiceImpl();
        service.enableCost(costId);

        resp.sendRedirect("pageFindCost");
    }

    private void toModifyCost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Integer costId = Integer.valueOf(req.getParameter("costId"));

        CostServiceImpl service = new CostServiceImpl();
        Cost cost = service.findCostById(costId);

        req.setAttribute("cost",cost);
        req.getRequestDispatcher("jsp/cost/cost_modify.jsp").forward(req,resp);
    }

    private void modifyCost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Integer costId = Integer.valueOf(req.getParameter("costId"));
        String costName = req.getParameter("costName");
        String baseDurationStr = req.getParameter("baseDuration");
        String baseCostStr = req.getParameter("baseCost");
        String unitCostStr = req.getParameter("unitCost");
        String desr = req.getParameter("desr");
        String costType = req.getParameter("costType");

        Double baseCost = baseCostStr==null || baseCostStr=="" ? null: Double.valueOf(baseCostStr);
        Integer baseDuration = baseDurationStr==null||baseDurationStr==""
                ?null: Integer.valueOf(baseDurationStr);

        Double unitCost = unitCostStr==null || unitCostStr=="" ? null: Double.valueOf(unitCostStr);
        Cost cost = new Cost(costId,costName,baseDuration,baseCost,unitCost,desr,costType);

        //业务层
        CostServiceImpl service = new CostServiceImpl();
        service.modifyCost(cost);

        resp.sendRedirect("pageFindCost");
    }

    private void findCostById(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Integer costId = Integer.valueOf(req.getParameter("costId"));

        CostServiceImpl service = new CostServiceImpl();
        Cost cost = service.findCostById(costId);

        req.setAttribute("cost",cost);

        req.getRequestDispatcher("jsp/cost/cost_detail.jsp").forward(req,resp);
    }

    private void checkCostName(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String costName = req.getParameter("param");
        String name = req.getParameter("costName");

        CostServiceImpl service = new CostServiceImpl();
        String result = service.checkCostName(costName);

        if(costName.equals(name)){
            result="y";
        }

        resp.getWriter().write(result);
    }

    private void addCost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String costName = req.getParameter("costName");
        String baseDurationStr = req.getParameter("baseDuration");
        String baseCostStr = req.getParameter("baseCost");
        String unitCostStr = req.getParameter("unitCost");
        String desr = req.getParameter("desr");
        String costType = req.getParameter("costType");

        Double baseCost = baseCostStr==null || baseCostStr=="" ? null: Double.valueOf(baseCostStr);
        Integer baseDuration = baseDurationStr==null||baseDurationStr==""
                ?null: Integer.valueOf(baseDurationStr);

        Double unitCost = unitCostStr==null || unitCostStr=="" ? null: Double.valueOf(unitCostStr);


        Cost cost = new Cost(costName,baseDuration,baseCost,unitCost,desr,costType);
        CostServiceImpl service = new CostServiceImpl();
        int costId = service.addCost(cost);


        resp.sendRedirect("pageFindCost");
    }

    private void pageFindCost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String order = req.getParameter("orderType");
        String currentPageStr = req.getParameter("currentPage");
        String pageSizeStr = req.getParameter("pageSize");

        String orderType = order==null || order=="" ? "aa,ba,ca":order;
        int currentPage = currentPageStr==null||currentPageStr==""?1:Integer.parseInt(currentPageStr);
        int pageSize = pageSizeStr==null||pageSizeStr==""?5:Integer.parseInt(pageSizeStr);

        CostServiceImpl service = new CostServiceImpl();
        PageResult page = service.pageFind(orderType, currentPage, pageSize);

        req.setAttribute("page",page);
        req.setAttribute("orderType",orderType);

        //转发
        req.getRequestDispatcher("jsp/cost/cost_list.jsp").forward(req,resp);
    }
}

