package com.qfedu.service;

import com.qfedu.entity.AdminInfo;
import com.qfedu.util.PageResult;
import com.qfedu.vo.AdminRoleVo;

import java.util.List;

public interface AdminInfoService {
    /**
     * 登陆
     * @param adminCode
     * @param password
     * @return
     */
    AdminInfo login(String adminCode, String password);

    /**
     * 管理员页面查询
     * @param roleId        角色id
     * @param name          管理员姓名
     * @param currentPage   当前页码
     * @param pageSize      每页行数
     * @return
     */
    PageResult pageFind(Integer roleId, String name, int currentPage, int pageSize);

    /**
     * 查询总条数
     * @param roleId
     * @return
     */
    List<Integer> pageFindAdminId(Integer roleId, String name);

    /**
     * 添加管理员信息
     * @param admin         管理员基本信息
     *  @param roleIds      角色ids
     */
    void addAdmin(AdminInfo admin,String[] roleIds);

    /**
     * 根据管理员账号查询多少条数据
     * @param adminCode
     * @return
     */
    String checkAdminCode(String adminCode);

    /**
     * 修改管理员和角色的关联关系
     * @param adminInfo  管理员信息
     * @param roleIds    角色ids
     */
    void modifyAdminRole(AdminInfo adminInfo, String[] roleIds);

    /**
     * 根据管理员id查找管理员角色关系信息
     * @param adminId
     * @return
     */
    AdminRoleVo findById(int adminId);

    /**
     * 根据管理员id重置密码
     * @param adminId
     * @return
     */
    void resetPassword(int adminId);

    /**
     * 状态删除
     * @param adminId
     */
    void deleteAdmin(int adminId);
}
