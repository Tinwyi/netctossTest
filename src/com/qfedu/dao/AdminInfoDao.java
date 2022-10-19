package com.qfedu.dao;

import com.qfedu.entity.AdminInfo;
import com.qfedu.vo.AdminInfoVo;
import com.qfedu.vo.AdminRoleVo;

import java.util.List;

public interface AdminInfoDao {
    /**
     * 管理员业务
     */

    /**
     * 查找管理员账号和密码
     * @param adminCode
     * @param password
     * @return
     */
    AdminInfo findByAdminCodeAndPwd(String adminCode ,String password);

    /**
     * 管理员页面查询
     * @param ids
     * @param name
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<AdminInfoVo> pageFind(List<Integer> ids,String name,int currentPage,int pageSize);

    /**
     * 查询管理员ids
     * @param roleId
     * @param name
     * @return
     */
    List<Integer> pageFindAdminId(Integer roleId,String name);

    /**
     * 添加角色信息,包括账号、密码、姓名、手机号、邮箱
     * @param adminInfo
     */
    int addAdmin(AdminInfo adminInfo);

    /**
     * 添加角色和模块的关联关系
     * @param adminId  管理员id
     * @param roleIds 角色ids
     */
    void addAdminRole(int adminId, String[] roleIds);

    /**
     * 根据管理员账号查询多少条数据
     * @param adminCode
     * @return
     */
    int findCountByAdminCode(String adminCode);

    /**
     * 修改管理员信息
     * @param adminInfo
     */
    void modifyAdmin(AdminInfo adminInfo);

    /**
     *  role 表  删除admin_id数据
     * @param adminId
     * @return
     */
    void deleteAdminRole(int adminId);

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
