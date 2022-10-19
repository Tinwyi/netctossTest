package com.qfedu.dao;

import com.qfedu.entity.AdminInfo;
import com.qfedu.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminInfoDaoImpl implements AdminInfoDao{
    @Override
    public AdminInfo findByAdminCodeAndPwd(String adminCode, String password) {

        AdminInfo adminInfo = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            conn = DbUtil.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM admin_info  ");
            sql.append("WHERE admin_code =? AND `password`=? ");
            sql.append("AND `status`=0 ");

            stm= conn.prepareStatement(sql.toString());
            stm.setString(1,adminCode);
            stm.setString(2,password);

            rs = stm.executeQuery();
            while (rs.next()){
                int adminId = rs.getInt("admin_id");
                String name = rs.getString("name");
                String telephone = rs.getString("telephone");
                String email = rs.getString("email");
                String enrolldate = rs.getString("enrolldate");
                String status = rs.getString("status");

                adminInfo = new AdminInfo(adminId, adminCode, password, name, telephone, email, enrolldate, status);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            DbUtil.close(conn,stm,rs);
        }
        return adminInfo;
    }
}
