package org.example.DAO.Impl;

import org.example.DAO.FlowerstoreDAO;
import org.example.POJO.Flower;
import org.example.POJO.Flowerstore;
import org.example.Utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlowerstoreDAOImpl implements FlowerstoreDAO {

    /*
    查找鲜花
    * */
    @Override
    public Flowerstore findFlowerByName(String Flowername) {
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;

        conn = DBUtil.getConnection();
        try {
            String sql = "select * from flowerstore where flowerName= '"+ Flowername+"'";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Flowerstore f= new Flowerstore();
                f.setFlowerName(rs.getString(1));
                f.setOutPrice(rs.getDouble(2));
                return f;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            DBUtil.close(conn, st, null, rs);
        }
        return null;
    }

    @Override
    public List<Flowerstore> findAllFlower() {
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;

        conn = DBUtil.getConnection();
        List<Flowerstore> list = new ArrayList<Flowerstore>();

        try {
            String sql = "select * from flowerstore";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Flowerstore f = new Flowerstore();
                f.setFlowerName(rs.getString(1));
                f.setOutPrice(rs.getDouble(2));
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

    /*
    购买鲜花
    * */
    @Override
    public double buyFlower(String flowerName, long num) {
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;

        conn = DBUtil.getConnection();
        try {
            String sql = "select outPrice from flowerstore where flowerName = '"+ flowerName+"'";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                return rs.getDouble("outPrice")*num;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            DBUtil.close(conn, st, null, rs);
        }
        return 0;
    }

    /*
    上架鲜花
    * */
    @Override
    public void addflowerstore(Flowerstore flowerstore) {
        Connection conn = DBUtil.getConnection();
        String sql = "insert into flowerstore(flowerName,outPrice) values(?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,flowerstore.getFlowerName());
            ps.setDouble(2,flowerstore.getOutPrice());
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            DBUtil.close(conn, null, ps, null);
        }
    }

    /*
    修改鲜花售价
    * */
    @Override
    public void updateflowerstoreOutPrince(String flowerstoreName, double OutPrince) {
        Connection conn = DBUtil.getConnection();
        String sql = "update flowerstore set outPrice = ? where flowerName ='"+flowerstoreName +"'";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setDouble(1,OutPrince);
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            DBUtil.close(conn, null, ps, null);
        }
    }


}
