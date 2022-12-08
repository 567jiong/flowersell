package org.example.DAO.Impl;

import org.example.DAO.FlowerDAO;
import org.example.POJO.Flower;
import org.example.Utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlowerDAOImpl implements FlowerDAO {
    @Override
    public void addFlower(Flower flower) {
        Connection conn = DBUtil.getConnection();
        String sql = "insert into flower(name,inPrice,totalNumber,buyNumber) values(?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,flower.getName());
            ps.setDouble(2,flower.getInPrice());
            ps.setLong(3, flower.getTotalNumber());
            ps.setLong(4, flower.getBuyNumber());
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            DBUtil.close(conn, null, ps, null);
        }
    }

    @Override
    public void updateFlowerNumber(String flowerName, long num) {
        Connection conn = DBUtil.getConnection();
        String sql = "update flower set totalNumber = ? where name ='"+flowerName +"'";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1,num);
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            DBUtil.close(conn, null, ps, null);
        }
    }

    @Override
    public void reduceFlowerNumber(String flowerName, long num) {
        long number = findFlowerNumber(flowerName);
        Connection conn = DBUtil.getConnection();
        String sql = "update flower set totalNumber = ? where name ='"+flowerName +"'";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1,number-num);
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            DBUtil.close(conn, null, ps, null);
        }
    }

    @Override
    public long findFlowerNumber(String flowerName) {
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;

        conn = DBUtil.getConnection();
        try {
            String sql = "select totalNumber from flower where name= '"+ flowerName+"'";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                return rs.getLong("totalNumber");
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            DBUtil.close(conn, st, null, rs);
        }
        return 0;
    }

    @Override
    public List<Flower> findAllFlower() {
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;

        conn = DBUtil.getConnection();
        List<Flower> list = new ArrayList<Flower>();

        try {
            String sql = "select * from flower";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Flower f = new Flower();
                f.setName(rs.getString(1));
                f.setInPrice(rs.getDouble(2));
                f.setTotalNumber(rs.getLong(3));
                f.setBuyNumber(rs.getLong(4));
                list.add(f);
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
    public void updatebuyflowernum(String flowername, long num) {
        long number = findbuyflowernum(flowername);
        Connection conn = DBUtil.getConnection();
        String sql = "update flower set buyNumber = ? where name ='"+flowername +"'";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1,num+number);
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            DBUtil.close(conn, null, ps, null);
        }
    }

    @Override
    public long findbuyflowernum(String flowername) {
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;

        conn = DBUtil.getConnection();
        try {
            String sql = "select buyNumber from flower where name= '"+ flowername+"'";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                return rs.getLong("buyNumber");
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            DBUtil.close(conn, st, null, rs);
        }
        return 0;
    }
}
