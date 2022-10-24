package com.qfedu.dao;

import com.qfedu.entity.AdminInfo;
import com.qfedu.util.DbUtil;
import com.qfedu.vo.AdminInfoVo;
import com.qfedu.vo.AdminRoleVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminInfoDaoImpl implements AdminInfoDao{
    @Override
    public List<AdminInfoVo> pageFind(List<Integer> ids,String name, int currentPage, int pageSize) {

        List<AdminInfoVo> list = new ArrayList<>();
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            stm = DbUtil.getStatement(conn);

            StringBuilder sql = new StringBuilder();
            StringBuilder sb = new StringBuilder();
            sql.append("SELECT a.admin_id,a.`name`,a.admin_code,a.telephone,a.email," +
                    "a.enroll_date,GROUP_CONCAT(r.role_name) roleNames ");
            sql.append("FROM admin_info a,admin_role ar,role_info r ");
            sql.append("WHERE a.admin_id=ar.admin_id AND ar.role_id=r.role_id ");
            sql.append("AND a.`status`=0 AND r.`status`=0 ");
            if( ids!=null) {
                sql.append("AND a.admin_id in ( ");
                for (Integer id : ids) {
                    sql.append(id+",");
                }
                String str = sql.substring(0,sql.length()-1)+") ";
                sb.append(str);
            }
            if( name!=null && name!="") {
                sb.append("AND a.name LIKE '%"+name+"%' ");
            }
            sb.append("GROUP BY a.admin_id ");
            sb.append("LIMIT "+(currentPage-1)*pageSize+", "+pageSize);

            rs = stm.executeQuery(sb.toString());
            while(rs.next()) {
                int adminId = rs.getInt("admin_id");
                String adminName = rs.getString("name");
                String adminCode = rs.getString("admin_code");
                String telephone = rs.getString("telephone");
                String email = rs.getString("email");
                String enrollDate = rs.getString("enroll_date");
                String roleNames = rs.getString("roleNames");
                AdminInfoVo vo = new AdminInfoVo(adminId, adminName, adminCode, telephone, email, enrollDate, roleNames);
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
    public List<Integer> pageFindAdminId(Integer roleId,String name) {
        ArrayList<Integer> list = new ArrayList<>();

        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();

            stm = DbUtil.getStatement(conn);

            StringBuilder sql = new StringBuilder();  // ctrl+d
            sql.append("SELECT DISTINCT a.admin_id ");
            sql.append("FROM admin_info a,admin_role ar,role_info r ");
            sql.append("WHERE a.admin_id=ar.admin_id AND ar.role_id=r.role_id ");
            sql.append("AND a.`status`=0 AND r.`status`=0 ");
            if(roleId!=null) {
                sql.append("and r.role_id = '"+roleId+"' ");
            }
            if(name!=null && name!="") {
                sql.append("and a.name LIKE '%"+name+"%' ");
            }
            rs = stm.executeQuery(sql.toString());
            while (rs.next()) {
                int adminId = rs.getInt(1);
                list.add(adminId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn, stm, rs);
        }
        return list;
    }

    @Override
    public AdminInfo findByAdminCodeAndPwd(String adminCode, String password) {
        AdminInfo adminInfo = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM admin_info  ");
            sql.append("WHERE admin_code =? AND `password`=? ");
            sql.append("AND `status`=0 ");

            stm = conn.prepareStatement(sql.toString());
            stm.setString(1, adminCode);
            stm.setString(2, password);

            rs = stm.executeQuery();

            while(rs.next()) {
                int adminId = rs.getInt("admin_id");
                String name = rs.getString("name");
                String telephone = rs.getString("telephone");
                String email = rs.getString("email");
                String enrollDate = rs.getString("enroll_date");
                Integer status = rs.getInt("status");

                adminInfo = new AdminInfo(adminId, adminCode, password, name, telephone, email, enrollDate, status);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn, stm, rs);
        }

        return adminInfo;
    }

    @Override
    public int addAdmin(AdminInfo admin) {

        int id = 0;

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rs = null;
        try {

            conn = DbUtil.getConnection();
            String sql ="INSERT INTO admin_info VALUES (null,?,?,?,?,?,CURDATE(),0)";
            pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, admin.getAdminCode());
            pstm.setString(2, admin.getPassword());
            pstm.setString(3, admin.getName());
            pstm.setString(4, admin.getTelephone());
            pstm.setString(5, admin.getEmail());

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
    public void addAdminRole(int adminId, String[] roleIds) {
        Connection conn = null;
        Statement stm = null;

        ResultSet rs = null;
        try {

            conn = DbUtil.getConnection();
            stm = DbUtil.getStatement(conn);

            String sql = "INSERT INTO admin_role VALUES";
            for(String roleId : roleIds) {
                sql+="("+adminId+","+roleId+"),";
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
    public int findCountByAdminCode(String adminCode) {
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;
        int count=0;
        try {

            conn = DbUtil.getConnection();
            stm = DbUtil.getStatement(conn);

            String sql = "select count(*) from admin_info where admin_code='"+adminCode+"'";

            rs = stm.executeQuery(sql);
            while (rs.next()){
                count = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn,stm, rs);
        }
        return count;
    }

    //修改管理员信息
    @Override
    public void modifyAdmin(AdminInfo adminInfo) {
        String sql = ("UPDATE admin_info SET admin_code=?,password=?,name=?,telephone=?,email=? WHERE admin_id=?");
        Object[] args = {adminInfo.getAdminCode(),adminInfo.getPassword(),adminInfo.getName(),
                adminInfo.getTelephone(),adminInfo.getEmail(),adminInfo.getAdminId()};
        DbUtil.commonUpdate(sql,args);
    }

    //删除管理员角色信息
    @Override
    public void deleteAdminRole(int adminId) {
        String sql = "DELETE FROM admin_info WHERE admin_id="+adminId;
        Object[] args = {adminId};
        DbUtil.commonUpdate(sql,args);
    }

    @Override
    public AdminRoleVo findById(int adminId) {
        AdminRoleVo vo = null;
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();

            stm = DbUtil.getStatement(conn);

            StringBuilder sql = new StringBuilder();  // ctrl+d
            sql.append("SELECT a.`name`,a.admin_code,a.password,a.telephone,a.email,GROUP_CONCAT(ar.role_id) roleIds ");
            sql.append("FROM admin_info a,admin_role ar ");
            sql.append("WHERE a.admin_id=ar.admin_id ");
            sql.append("AND a.`status`=0 ");
            sql.append("AND a.admin_id="+adminId);

            rs = stm.executeQuery(sql.toString());
            while(rs.next()) {
                String adminName = rs.getString("name");
                String adminCode = rs.getString("admin_code");
                String password = rs.getString("password");
                String telephone = rs.getString("telephone");
                String email = rs.getString("email");
                String roleIds = rs.getString("roleIds");
                vo = new AdminRoleVo(adminId,adminName,adminCode,password,telephone,email,roleIds);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn, stm, rs);
        }

        return vo;
    }

    @Override
    public void resetPassword(int adminId) {
        String sql = "update admin_info set password='123' where admin_id = ? ";
        Object[] args = {adminId};
        DbUtil.commonUpdate(sql,args);
    }

    /**
     * 状态删除
     * @param adminId
     * @return
     */
    @Override
    public void deleteAdmin(int adminId) {
        String sql = ("UPDATE admin_info SET status='1' WHERE admin_id=?");
        Object[] args = {adminId};
        DbUtil.commonUpdate(sql,args);
    }
}

