package com.qfedu.dao;

import com.qfedu.entity.Cost;

import java.util.List;

/**
     * cost表
     */
public interface CostDao {

        /**
         * 资费页面查询
         *
         * @param orderType   排序方式
         * @param currentPage 当前页码
         * @param pageSize    每页行数
         * @return
         */
        List<Cost> pageFind(String orderType, int currentPage, int pageSize);

        /**
         * 根据id查询的资费信息  /  id为null则查所有资费信息
         * @return
         */
        List<Cost> findAll(Integer id);

        /**
         * 添加资费信息
         * @param cost
         * @return
         */
        int addCost(Cost cost);

        /**
         * 根据资费名称查询多少条数据
         * @param name
         * @return
         */
        int findCountByCostName(String name);

        /**
         * 修改资费信息
         * @param cost
         */
        void modifyCost(Cost cost);

        /**
         * 资费状态变化：'0':开通，'1'：暂停；'-1'删除
         * @param id
         */
        void changeStatusCost(String status, int id);

}
