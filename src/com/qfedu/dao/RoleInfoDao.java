package com.qfedu.dao;

import com.qfedu.entity.RoleInfo;
import com.qfedu.vo.RoleInfoUpdateVo;
import com.qfedu.vo.RoleInfoVo;

import java.util.List;

public interface RoleInfoDao {
    /**
     * 分页查询显示的数据
     * @param roleName      角色名称
     * @param currentPage   当前页
     * @param pageSize      一页显示的条数
     * @return
     */
    List<RoleInfoVo> pageFind(String roleName, int currentPage,int pageSize);

    /**
     * 查找用户姓名
     * @param roleName  角色姓名
     * @return
     */
    int pageFindCount(String roleName);

    /**
     * 添加角色
     * @param roleName
     * @return
     */
    int addRole(String roleName);


    /**
     * 添加角色和模块的关联关系
     * @param roleId
     * @param moduleIds
     */
    void addRoleModule(int roleId ,String[] moduleIds);

    /**
     * 根据角色名称查出多少条数据
     * @param roleName
     * @return
     */
    int findCountByRoleName(String roleName);

    /**
     * 根据主键id，修改一条角色信息
     * @param roleId
     * @return
     */
    RoleInfoUpdateVo findById(int roleId);

    /**
     * 根据主键id,修改一条角色信息
     * @param role
     */
    void updateRoleInfo(RoleInfo role);

    /**
     * 根据角色id,删除和菜单权限的关联关系
     * @param roleId
     */
    void deleteRoleModuleByRoleId(int roleId);

    /**
     * 根据角色id，修改status的状态
     * @param roleId
     * @param status
     */
    void updateStatusByRoleId(int roleId, String status);

}
