package com.qfedu.dao;

import com.qfedu.entity.RoleInfo;
import com.qfedu.util.DbUtil;
import com.qfedu.vo.RoleInfoUpdateVo;
import com.qfedu.vo.RoleInfoVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleInfoDaoImpl implements RoleInfoDao {

    @Override
    public List<RoleInfoVo> pageFind(String roleName, int currentPage, int pageSize) {

        List<RoleInfoVo> list = new ArrayList<>();
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();

            stm = DbUtil.getStatement(conn);

            StringBuilder sql = new StringBuilder();  // ctrl+d
            sql.append("SELECT r.role_id,r.role_name, GROUP_CONCAT(m.module_name) moduleNames ");
            sql.append("FROM role_info r, module_info m, role_module rm ");
            sql.append("WHERE r.role_id = rm.role_id AND rm.module_id = m.module_id ");
            sql.append("AND r.`status`=0 ");
            if(roleName!=null&&roleName!="") {
                sql.append("AND r.role_name LIKE '%"+roleName+"%' ");
            }
            sql.append("GROUP BY r.role_id,r.role_name ");
            sql.append("LIMIT "+(currentPage-1)*pageSize+", "+pageSize);   //分页

            rs = stm.executeQuery(sql.toString());
            while(rs.next()) {
                int roleId = rs.getInt("role_id");
                String rn = rs.getString("role_name");
                String moduleNames = rs.getString("moduleNames");
                RoleInfoVo vo = new RoleInfoVo(roleId, rn ,moduleNames);
                list.add(vo);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn, stm, rs);
        }

        return list;
    }

    @Override
    public int pageFindCount(String roleName) {

        int count = 0;

        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();

            stm = DbUtil.getStatement(conn);

            StringBuilder sql = new StringBuilder();  // ctrl+d
            sql.append("SELECT count(*) FROM role_info WHERE  `status`=0 ");
            if(roleName!=null && roleName!="") {
                sql.append("and role_name LIKE '%o%' ");
            }


            rs = stm.executeQuery(sql.toString());
            while(rs.next()) {
                count = rs.getInt(1);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn, stm, rs);
        }
        return count;
    }

    @Override
    public int addRole(String roleName) {

        int id = 0;

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rs = null;
        try {

            conn = DbUtil.getConnection();
            String sql ="insert into role_info value(null,?,0)";
            pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, roleName);

            pstm.executeUpdate();

            rs = pstm.getGeneratedKeys();

            rs.next();
            id = rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn,pstm, rs);
        }
        return id;
    }

    @Override
    public void addRoleModule(int roleId, String[] moduleIds) {
        Connection conn = null;
        Statement stm = null;

        ResultSet rs = null;
        try {

            conn = DbUtil.getConnection();
            stm = DbUtil.getStatement(conn);

            //INSERT INTO role_module VALUES(1,2),(1,3),
            String sql = "INSERT INTO role_module VALUES";
            for(String moduleId : moduleIds) {
                sql+="("+roleId+","+moduleId+"),";
            }
            sql = sql.substring(0,sql.length()-1);

            stm.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn,stm, rs);
        }
    }

    @Override
    public int findCountByRoleName(String roleName) {
        int count = 0;
        Connection conn = null;
        Statement stm = null;

        ResultSet rs = null;
        try{
            conn = DbUtil.getConnection();
            stm = DbUtil.getStatement(conn);
            StringBuilder sql =new StringBuilder();
            sql.append("SELECT count(*) FROM role_info WHERE `status`=0 and role_name='"+roleName+"'");
            rs = stm.executeQuery(sql.toString());

            while (rs.next()){
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            DbUtil.close(conn,stm,rs);
        }
        return count;
    }

    @Override
    public RoleInfoUpdateVo findById(int roleId) {
        RoleInfoUpdateVo vo = null;
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            stm = DbUtil.getStatement(conn);
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT r.role_id, r.role_name, GROUP_CONCAT(rm.module_id) moduleIds ");
            sql.append(" FROM role_info r, role_module rm ");
            sql.append(" WHERE r.role_id = rm.role_id ");
            sql.append(" AND r.role_id = "+roleId);
            sql.append(" GROUP BY r.role_id, r.role_name ");

            rs = stm.executeQuery(sql.toString());
            while (rs.next()){
                String roleName = rs.getString("role_name");
                String moduleIds = rs.getString("moduleIds");

                vo = new RoleInfoUpdateVo(roleId,roleName,moduleIds);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DbUtil.close(conn,stm,rs);
        }
        return vo;
    }

    @Override
    public void updateRoleInfo(RoleInfo role) {
        String sql = "UPDATE role_info SET role_name=? WHERE role_id=?";
        System.out.println(sql);
        Object[] args = {role.getRoleName(),role.getRoleId()};
        DbUtil.commonUpdate(sql,args);
    }

    @Override
    public void deleteRoleModuleByRoleId(int roleId) {
        String sql = "DELETE FROM role_module WHERE role_id = ?";
        System.out.println(sql);
        Object[] args ={roleId};
        DbUtil.commonUpdate(sql,args);
    }

    @Override
    public void updateStatusByRoleId(int roleId, String status) {
        String sql = "update role_info set status=? where role_id=?";
        Object[] args = {status, roleId};
        DbUtil.commonUpdate(sql,args);
    }


}
