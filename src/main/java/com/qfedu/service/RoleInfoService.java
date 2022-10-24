package com.qfedu.service;

import com.qfedu.entity.RoleInfo;
import com.qfedu.util.PageResult;
import com.qfedu.vo.RoleInfoUpdateVo;
import com.qfedu.vo.RoleInfoVo;

import java.util.List;

public interface RoleInfoService {
//    /**
//     * 分页查询显示的数据
//     * @param roleName      角色名称
//     * @param currentPage   当前页
//     * @param pageSize      一页显示的条数
//     * @return
//     */
//    List<RoleInfoVo> pageFind(String roleName, int currentPage, int pageSize);
//
//
//
//    /**
//     * 查找用户姓名
//     * @param roleName  角色姓名
//     * @return
//     */
//    int pageFindCount(String roleName);

    /**
     *  分页查询
     * @param roleName
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageResult pageFind(String roleName, int currentPage, int pageSize);

    /**
     *
     * @param
     * @return
     */
    int pageFindCount(String roleName);


    /**
     *
     * @param roleName
     * @param moduleIds
     */
    void addRole(String roleName,String[] moduleIds);

    /**
     *
     * @param roleName
     * @return
     */
    String checkRoleName(String roleName);


    /**
     * 根据主键id，修改一条角色信息
     * @param roleId
     * @return
     */
    RoleInfoUpdateVo findById(int roleId);

    /**
     * 修改角色信息
     * @param roleInfo
     * @param moduleIds
     */

    void updateRole(RoleInfo roleInfo , String[] moduleIds);

    /**
     * 根据主键id删除角色信息
     * @param roleId
     */
    void deleteByRoleId(int roleId);

}
