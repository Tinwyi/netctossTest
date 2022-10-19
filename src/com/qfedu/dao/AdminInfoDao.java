package com.qfedu.dao;

import com.qfedu.entity.AdminInfo;

public interface AdminInfoDao {
    /**
     * 管理员业务
     */
    AdminInfo findByAdminCodeAndPwd(String adminCode ,String password);
}
