package com.qfedu.service;

import com.qfedu.entity.Cost;
import com.qfedu.util.PageResult;

public interface CostService {
    /**
     * 资费页面查询
     * @param orderType     排序方式
     * @param currentPage   当前页码
     * @param pageSize      每页行数
     * @return
     */
    PageResult pageFind(String orderType, int currentPage, int pageSize);

    /**
     * 添加资费信息
     * @param cost
     * @return
     */
    int addCost(Cost cost);

    /**
     * 根据资费名称判断是否重复
     * @param name
     * @return
     */
    String checkCostName(String name);

    /**
     * 根据id查询当前的资费信息
     * @param id
     * @return
     */
    Cost findCostById(Integer id);

    /**
     * 修改资费信息
     * @param cost
     */
    void modifyCost(Cost cost);

    /**
     * 启用资费
     * @param id
     */
    void enableCost(int id);

    /**
     * 删除资费
     * @param id
     */
    void deleteCost(int id);
}
