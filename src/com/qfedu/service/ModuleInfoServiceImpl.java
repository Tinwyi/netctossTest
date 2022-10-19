package com.qfedu.service;

import com.qfedu.dao.ModuleInfoDao;
import com.qfedu.dao.ModuleInfoDaoImpl;
import com.qfedu.entity.ModuleInfo;

import java.util.List;

public class ModuleInfoServiceImpl implements ModuleInfoService{
    @Override
    public List<ModuleInfo> findAll() {
        ModuleInfoDao dao = new ModuleInfoDaoImpl();
        return dao.findAll();

    }

    @Override
    public String getModuleNamesByAdminId(int adminId) {
        ModuleInfoDaoImpl dao = new ModuleInfoDaoImpl();
        return dao.getModuleNamesByAdminId(adminId);
    }
}
