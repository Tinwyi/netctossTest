package com.qfedu.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qfedu.entity.ModuleInfo;

import com.qfedu.service.ModuleInfoService;
import com.qfedu.service.ModuleInfoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/findAllModule")
public class ModuleInfoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String uri = req.getRequestURI();
        if(uri.endsWith("findAllModule")){
            findAllModule(req,resp);
        }
    }

    private void findAllModule(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ModuleInfoService service = new ModuleInfoServiceImpl();
        List<ModuleInfo> list = service.findAll();

        //把list ==> json
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(list);

        resp.getWriter().write(jsonStr);
    }

}
