package com.qfedu.dao;

import com.qfedu.entity.ModuleInfo;
import com.qfedu.util.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ModuleInfoDaoImpl implements ModuleInfoDao{
    @Override
    public List<ModuleInfo> findAll() {
        List<ModuleInfo> list = new ArrayList<>();

        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;
        try{
            conn = DbUtil.getConnection();
            stm = DbUtil.getStatement(conn);
            String sql = "select * from module_info ";

            rs = stm.executeQuery(sql.toString());
            while (rs.next()){
                int moduleId = rs.getInt("module_id");
                String moduleName = rs.getString("module_name");

                list.add(new ModuleInfo(moduleId,moduleName));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            DbUtil.close(conn,stm,rs);
        }

        return list;
    }

    @Override
    public String getModuleNamesByAdminId(int adminId) {

        String moduleNames = null;

        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();

            stm = DbUtil.getStatement(conn);

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT GROUP_CONCAT(DISTINCT m.module_name) ");
            sql.append("FROM admin_role ar, role_module rm, module_info m ");
            sql.append("WHERE ar.admin_id ="+adminId);
            sql.append(" AND ar.role_id = rm.role_id AND rm.module_id = m.module_id ");
            sql.append("GROUP BY ar.admin_id");

            rs = stm.executeQuery(sql.toString());
            while(rs.next()) {
                moduleNames = rs.getString(1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            DbUtil.close(conn,stm,rs);
        }

        return moduleNames;
    }
}

