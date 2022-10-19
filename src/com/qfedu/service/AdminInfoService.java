package com.qfedu.service;

import com.qfedu.entity.AdminInfo;

public interface AdminInfoService {
    AdminInfo login(String adminCode ,String password);
}
