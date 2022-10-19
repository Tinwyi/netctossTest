package com.qfedu.service;

import com.qfedu.dao.AdminInfoDaoImpl;
import com.qfedu.entity.AdminInfo;

public class AdminInfoServiceImpl implements AdminInfoService{
    @Override
    public AdminInfo login(String adminCode, String password) {
        AdminInfoDaoImpl dao = new AdminInfoDaoImpl();
        return dao.findByAdminCodeAndPwd(adminCode,password);

    }
}
