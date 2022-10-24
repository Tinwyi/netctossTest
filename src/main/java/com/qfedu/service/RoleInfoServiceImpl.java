package com.qfedu.service;

import com.qfedu.dao.RoleInfoDao;
import com.qfedu.dao.RoleInfoDaoImpl;
import com.qfedu.entity.RoleInfo;
import com.qfedu.util.PageResult;
import com.qfedu.vo.RoleInfoUpdateVo;
import com.qfedu.vo.RoleInfoVo;

import java.util.List;

public class RoleInfoServiceImpl implements RoleInfoService{
    @Override
    public PageResult pageFind(String roleName, int currentPage, int pageSize) {
        RoleInfoDaoImpl dao =new RoleInfoDaoImpl();
        List<RoleInfoVo> list =dao.pageFind(roleName,currentPage,pageSize);
        int count = dao.pageFindCount(roleName);
        return new PageResult(list,currentPage,pageSize,count);
    }

    @Override
    public void addRole(String roleName, String[] moduleIds) {
        RoleInfoDaoImpl dao = new RoleInfoDaoImpl();
        int roleId = dao.addRole(roleName);
        dao.addRoleModule(roleId,moduleIds);
    }

    @Override
    public String checkRoleName(String roleName) {
        RoleInfoDaoImpl dao = new RoleInfoDaoImpl();
        int count = dao.findCountByRoleName(roleName);
        if (count == 0){
            return "y";
        }
        return "该用户名字已经重复";
    }

    @Override
    public RoleInfoUpdateVo findById(int roleId) {
        RoleInfoDaoImpl dao = new RoleInfoDaoImpl();
        return dao.findById(roleId);

    }

    @Override
    public void updateRole(RoleInfo roleInfo, String[] moduleIds) {
        RoleInfoDao dao = new RoleInfoDaoImpl();
        dao.updateRoleInfo(roleInfo);
        dao.deleteRoleModuleByRoleId(roleInfo.getRoleId());
        dao.addRoleModule(roleInfo.getRoleId(),moduleIds);
    }

    @Override
    public void deleteByRoleId(int roleId) {
        //删除状态
        final String deleteStatus = "1";

        RoleInfoDao dao = new RoleInfoDaoImpl();
        dao.updateStatusByRoleId(roleId,deleteStatus);
    }


//
//    @Override
//    public List<RoleInfoVo> pageFind(String roleName, int currentPage, int pageSize) {
//        RoleInfoDao dao = new RoleInfoDaolmpl();
//        return dao.pageFind(roleName,currentPage,pageSize);
//    }

    @Override
    public int pageFindCount(String roleName) {
        RoleInfoDaoImpl dao = new RoleInfoDaoImpl();
        return dao.pageFindCount(roleName);
    }
}
