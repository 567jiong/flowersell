package org.example.DAO.Impl;

import org.example.DAO.OrdersDAO;
import org.example.POJO.Flower;
import org.example.POJO.Orders;
import org.example.Utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAOImpl implements OrdersDAO {

    @Override
    public void addOrder(Orders orders) {
        Connection conn = DBUtil.getConnection();
        String sql = "insert into orders(buyUserName,buyFlowerName,buyNumber,totalPrice,buyTime) values(?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,orders.getBuyUserName());
            ps.setString(2,orders.getBuyFlowerName());
            ps.setLong(3, orders.getBuyNumber());
            ps.setDouble(4, orders.getTotalPrice());
            ps.setDate(5,orders.getBuyTime());
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            DBUtil.close(conn, null, ps, null);
        }
    }

    @Override
    public List<Orders> findOrders(String UserName) {
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;

        conn = DBUtil.getConnection();
        List<Orders> list = new ArrayList<Orders>();

        try {
            String sql = "select * from orders where buyUserName= '"+UserName+"'";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Orders orders =new Orders();
                orders.setBuyUserName(rs.getString(1));
                orders.setBuyFlowerName(rs.getString(2));
                orders.setBuyNumber(rs.getInt(3));
                orders.setTotalPrice(rs.getDouble(4));
                orders.setBuyTime(rs.getDate(5));
                list.add(orders);
            }
            return list;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            DBUtil.close(conn, st, null, rs);
        }
        return null;
    }

    @Override
    public List<Orders> findAllOrders() {
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;

        conn = DBUtil.getConnection();
        List<Orders> list = new ArrayList<Orders>();

        try {
            String sql = "select * from orders order by BuyTime desc ";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Orders orders =new Orders();
                orders.setBuyUserName(rs.getString(1));
                orders.setBuyFlowerName(rs.getString(2));
                orders.setBuyNumber(rs.getInt(3));
                orders.setTotalPrice(rs.getDouble(4));
                orders.setBuyTime(rs.getDate(5));
                list.add(orders);
            }
            return list;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            DBUtil.close(conn, st, null, rs);
        }
        return null;
    }
}
