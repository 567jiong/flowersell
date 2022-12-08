package org.example.DAO.Impl;

import org.example.DAO.CustomerDAO;
import org.example.Utils.DBUtil;

import java.sql.*;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public int login(String name, String password) {
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;

        conn = DBUtil.getConnection();
        try {
            String sql = "select passWord from customer where userName= '"+ name+"'";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                if(!rs.getString("passWord").equals(password)){
                    return 0;
                }
                if(rs.getString("passWord").equals(password)){
                    return 1;
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            DBUtil.close(conn, st, null, rs);
        }
        return 2;
    }

    @Override
    public void addCustomer(String name, String password) {
        Connection conn = DBUtil.getConnection();
        String sql = "insert into customer(userName,passWord) values(?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,password);
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            DBUtil.close(conn, null, ps, null);
        }
    }

    @Override
    public Boolean findUserName(String name) {
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;

        conn = DBUtil.getConnection();
        try {
            String sql = "select count(*) from customer where userName= '"+ name+"'";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
               if(rs.getInt(1)!=0){
                   return true;
               }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            DBUtil.close(conn, st, null, rs);
        }
        return false;
    }
}
