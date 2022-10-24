package com.qfedu.service;

import com.qfedu.entity.ModuleInfo;

import java.util.List;

/**
     * 模块业务层接口
     */
    public interface ModuleInfoService {

        /**
         * 查询所有的模块信息
         * @return
         */
        List<ModuleInfo> findAll();

    /**
     * 根据管理id，查询他所拥有的模块名称
     * @param adminId
     * @return
     */
    String getModuleNamesByAdminId(int adminId);
    }

