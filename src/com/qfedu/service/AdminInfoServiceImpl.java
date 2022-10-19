package com.qfedu.service;

import com.qfedu.dao.AdminInfoDaoImpl;
import com.qfedu.entity.AdminInfo;
import com.qfedu.util.PageResult;
import com.qfedu.vo.AdminInfoVo;
import com.qfedu.vo.AdminRoleVo;

import java.util.List;

public class AdminInfoServiceImpl implements AdminInfoService{
    @Override
    public AdminInfo login(String adminCode, String password) {
        AdminInfoDaoImpl dao = new AdminInfoDaoImpl();
        return dao.findByAdminCodeAndPwd(adminCode, password);
    }

    @Override
    public PageResult pageFind(Integer roleId, String name, int currentPage, int pageSize) {
        AdminInfoDaoImpl dao = new AdminInfoDaoImpl();
        List<Integer> ids = dao.pageFindAdminId(roleId, name);
        List<AdminInfoVo> list = dao.pageFind(ids, name, currentPage, pageSize);
        return new PageResult(list,currentPage,pageSize,ids.size());
    }

    @Override
    public List<Integer> pageFindAdminId(Integer roleId, String name) {
        AdminInfoDaoImpl dao = new AdminInfoDaoImpl();
        return dao.pageFindAdminId(roleId,name);
    }

    @Override
    public void addAdmin(AdminInfo admin, String[] roleIds) {
        AdminInfoDaoImpl dao = new AdminInfoDaoImpl();
        int adminId = dao.addAdmin(admin);
        dao.addAdminRole(adminId,roleIds);
    }

    @Override
    public String checkAdminCode(String adminCode) {
        AdminInfoDaoImpl dao = new AdminInfoDaoImpl();
        int count = dao.findCountByAdminCode(adminCode);
        if(count==0){
            return "y";
        }
        return "该角色名称已存在";
    }

    @Override
    public void modifyAdminRole(AdminInfo adminInfo, String[] roleIds) {
        AdminInfoDaoImpl dao = new AdminInfoDaoImpl();
        dao.deleteAdminRole(adminInfo.getAdminId());
        dao.addAdminRole(adminInfo.getAdminId(),roleIds);
        dao.modifyAdmin(adminInfo);
    }

    @Override
    public AdminRoleVo findById(int adminId) {
        AdminInfoDaoImpl dao = new AdminInfoDaoImpl();
        return dao.findById(adminId);
    }

    @Override
    public void resetPassword(int adminId) {
        AdminInfoDaoImpl dao = new AdminInfoDaoImpl();
        dao.resetPassword(adminId);
    }

    @Override
    public void deleteAdmin(int adminId) {
        AdminInfoDaoImpl dao = new AdminInfoDaoImpl();
        dao.deleteAdmin(adminId);
    }
}
