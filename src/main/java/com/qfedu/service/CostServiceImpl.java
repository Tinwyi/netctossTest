package com.qfedu.service;

import com.qfedu.dao.CostDao;
import com.qfedu.dao.CostDaoImpl;
import com.qfedu.entity.Cost;
import com.qfedu.util.PageResult;

import java.util.List;

public class CostServiceImpl implements CostService{

    @Override
    public PageResult pageFind(String orderType, int currentPage, int pageSize) {
        CostDaoImpl costDao = new CostDaoImpl();
        List<Cost> list =costDao.pageFind(orderType,currentPage,pageSize);
        List<Cost> allCost =costDao.findAll(null);
        return new PageResult(list,currentPage,pageSize,allCost.size());
    }

    @Override
    public int addCost(Cost cost) {
        CostDaoImpl costDao = new CostDaoImpl();
        return costDao.addCost(cost);
    }

    @Override
    public String checkCostName(String name) {
        CostDaoImpl costDao = new CostDaoImpl();
        int count = costDao.findCountByCostName(name);
        if(count == 0){
            return "y";
        }return "该资费名称已存在！";
    }

    @Override
    public Cost findCostById(Integer id) {
        CostDaoImpl costDao = new CostDaoImpl();
        List<Cost> list = costDao.findAll(id);
        return list.get(0);
    }

    @Override
    public void modifyCost(Cost cost) {
        CostDaoImpl costDao = new CostDaoImpl();
        costDao.modifyCost(cost);
    }

    @Override
    public void enableCost(int id) {
        CostDaoImpl costDao = new CostDaoImpl();
        costDao.changeStatusCost("0",id);
    }

    @Override
    public void deleteCost(int id) {
        CostDaoImpl costDao = new CostDaoImpl();
        costDao.changeStatusCost("-1",id);
    }
}
