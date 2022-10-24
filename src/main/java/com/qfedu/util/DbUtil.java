package com.qfedu.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DbUtil {
    private static  String DRIVER;
    private static  String URL;
    private static  String USER;
    private static  String PASSWORD;

    static {
        //读取配置文件
        //1. 使用反射读取资源转化为输入流
        InputStream is = DbUtil.class.getResourceAsStream("/db.properties");
        Properties p = new Properties();

        try {
            p.load(is);
            DRIVER = p.getProperty("jdbc.driver");
            URL = p.getProperty("jdbc.url");
            USER = p.getProperty("jdbc.user");
            PASSWORD = p.getProperty("jdbc.password");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //加载驱动
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //获取连接
    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            return conn;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    //获取语句块的方法
    public static Statement getStatement(Connection conn) {
        try {
            Statement stm = conn.createStatement();
            return stm;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    //获取语句块的方法
    public static Statement createStatement(Connection conn) {
        try {
            Statement stm = conn.createStatement();
            return stm;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    //关闭资源
    public static void close(Connection conn,Statement stm,ResultSet rs) {

        try {
            if(rs!=null) {
                rs.close();
            }
            if(stm!=null) {
                stm.close();
            }
            if(conn!=null) {
                conn.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    //定义增删改的公共方法
    public static void commonUpdate(String sql, Object[] args) {
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = DbUtil.getConnection();
            pstm = conn.prepareStatement(sql);

            //把参数放在sql中
            for(int i=0; i<args.length; i++) { //i=0,1,2,3
                pstm.setObject(i+1, args[i]);
            }

            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn, pstm, null);
        }
    }

}
