package com.qfedu.dao;

import com.qfedu.entity.Cost;
import com.qfedu.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CostDaoImpl implements CostDao {
    @Override
    public List<Cost> pageFind(String orderType, int currentPage, int pageSize) {
        List<Cost> list = new ArrayList<>();
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;

        try {
            conn = DbUtil.getConnection();

            stm = DbUtil.getStatement(conn);

            StringBuilder sb = new StringBuilder();
            StringBuilder sql = new StringBuilder();
            String str = "";
            sb.append("SELECT * FROM cost where status in ('1','0') ");
            str = sb.toString();
            if (orderType != null && orderType != "") {
                sb.append("ORDER BY  ");
                String[] arr = orderType.split(",");
                for (String s : arr) {
                    if (s.contains("aa")) {
                        sb.append("base_cost,");
                    } else if (s.contains("ab")) {
                        sb.append("base_cost DESC,");
                    }
                    if (s.contains("ba")) {
                        sb.append("unit_cost,");
                    } else if (s.contains("bb")) {
                        sb.append("unit_cost DESC,");
                    }
                    if (s.contains("ca")) {
                        sb.append("base_duration,");
                    } else if (s.contains("cb")) {
                        sb.append("base_duration DESC,");
                    }
                }
                str = sb.substring(0, sb.length() - 1);
            }
            sql.append(str);
            sql.append(" LIMIT " + (currentPage - 1) * pageSize + ", " + pageSize);

            rs = stm.executeQuery(sql.toString());
            while (rs.next()) {
                Integer costId = rs.getInt("cost_id");
                String name = rs.getString("name");
                Integer baseDuration = rs.getInt("base_duration");
                Double baseCost = rs.getDouble("base_cost");
                Double unitCost = rs.getDouble("unit_cost");


                baseDuration = baseDuration == 0 ? null : baseDuration;
                baseCost = baseCost == 0.0 ? null : baseCost;
                unitCost = unitCost == 0.0 ? null : unitCost;

                Date creatime = rs.getDate("creatime");
                Date startime = rs.getDate("startime");
                String status = rs.getString("status");
                Cost cost = new Cost(costId, name, baseDuration, baseCost, unitCost, status, creatime, startime);
                list.add(cost);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DbUtil.close(conn,stm,rs);
        }
        return list;
    }

    @Override
    public List<Cost> findAll(Integer id) {

        List<Cost> list = new ArrayList<>();
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();

            stm = DbUtil.getStatement(conn);

            String sql = "SELECT * FROM cost ";

            if (id != null) {
                sql += "where cost_id =" + id;
            }

            rs = stm.executeQuery(sql);
            while (rs.next()) {
                int costId = rs.getInt("cost_id");
                String name = rs.getString("name");
                Integer baseDuration = rs.getInt("base_duration");
                Double baseCost = rs.getDouble("base_cost");
                Double unitCost = rs.getDouble("unit_cost");

                baseDuration = baseDuration == 0 ? null : baseDuration;
                baseCost = baseCost == 0.0 ? null : baseCost;
                unitCost = unitCost == 0.0 ? null : unitCost;

                Date creatime = rs.getDate("creatime");
                Date startime = rs.getDate("startime");
                String status = rs.getString("status");
                String desr = rs.getString("desr");
                String costType = rs.getString("cost_type");
                Cost cost = new Cost(costId, name, baseDuration, baseCost, unitCost
                        , status, desr, creatime, startime, costType);
                list.add(cost);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DbUtil.close(conn,stm,rs);
        }

        return list;
    }

    @Override
    public int addCost(Cost cost) {
        int id = 0;

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            String sql = "INSERT INTO cost VALUES (null,?," + cost.getBaseDuration()
                    + "," + cost.getBaseCost() + "," + cost.getUnitCost() + ",'1',?,CURDATE(),null,?)";
            pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, cost.getName());
            pstm.setString(2, cost.getDesr());
            pstm.setString(3, cost.getCostType());

            pstm.executeUpdate();

            rs = pstm.getGeneratedKeys();

            rs.next();
            id = rs.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DbUtil.close(conn,pstm,rs);
        }
        return id;
    }

    @Override
    public int findCountByCostName(String name) {
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;
        int count = 0;
        try {

            conn = DbUtil.getConnection();
            stm = DbUtil.getStatement(conn);

            String sql = "select count(*) from cost where name='" + name + "'";

            rs = stm.executeQuery(sql);
            while (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            DbUtil.close(conn, stm, rs);
        }
        return count;
    }

    @Override
    public void modifyCost(Cost cost) {
        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            String sql = "UPDATE cost set  name=?,base_duration=" + cost.getBaseDuration()
                    + ",base_cost=" + cost.getBaseCost() + ",unit_cost=" + cost.getUnitCost() +
                    ",desr=?,cost_type=? where cost_id=?";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, cost.getName());
            pstm.setString(2, cost.getDesr());
            pstm.setString(3, cost.getCostType());
            pstm.setInt(4, cost.getCostId());

            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            DbUtil.close(conn, pstm, rs);
        }
    }

    @Override
    public void changeStatusCost(String status, int id) {
        String sql = "update cost set status=? where cost_id=? ";
        if(status.equals("0")){
            sql = "update cost set status=?,startime=curdate() where cost_id=? ";
        }else if(status.equals("1")){
            sql = "update cost set status=?,startime=null,creatime=curdate() where cost_id=? ";
        }
        System.out.println("status:"+status);
        Object[] args = {status,id};
        DbUtil.commonUpdate(sql,args);
    }
}
