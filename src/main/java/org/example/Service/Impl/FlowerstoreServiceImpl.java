package org.example.Service.Impl;

import org.example.DAO.FlowerDAO;
import org.example.DAO.FlowerstoreDAO;
import org.example.DAO.Impl.FlowerstoreDAOImpl;
import org.example.DAO.OrdersDAO;
import org.example.Factory.DBFactory;
import org.example.POJO.Flowerstore;
import org.example.POJO.Orders;
import org.example.Service.FlowerstoreService;
import org.example.Service.OrdersService;
import org.example.Ui.LoginTable;

import java.sql.Date;
import java.util.List;

public class FlowerstoreServiceImpl implements FlowerstoreService {
    FlowerstoreDAO flowerstoreDAO = DBFactory.getInstance().getFlowerstoreDAO();
    FlowerDAO flowerDAO = DBFactory.getInstance().getFlowerDAO();
    OrdersDAO ordersDAO = DBFactory.getInstance().getOrdersDAO();
    @Override
    public Flowerstore findFlowerByName(String Flowername) {
        return flowerstoreDAO.findFlowerByName(Flowername);
    }

    @Override
    public List<Flowerstore> findAllFlower() {
        return flowerstoreDAO.findAllFlower();
    }

    @Override
    public void addflowerstore(Flowerstore flowerstore) {
        flowerstoreDAO.addflowerstore(flowerstore);
    }

    @Override
    public void updateflowerstoreOutPrince(String flowerstoreName, double OutPrince) {
        flowerstoreDAO.updateflowerstoreOutPrince(flowerstoreName,OutPrince);
    }

    @Override
    public double buyFlower(String flowerName, long num) {
        double much=flowerstoreDAO.buyFlower(flowerName,num);
        Orders orders=new Orders();
        orders.setBuyUserName(LoginTable.LoginUserName);
        orders.setBuyNumber(num);
        orders.setBuyFlowerName(flowerName);
        orders.setTotalPrice(much);
        Date date = new Date(System.currentTimeMillis());
        orders.setBuyTime(date);
        ordersDAO.addOrder(orders);
        flowerDAO.updatebuyflowernum(flowerName,num);
        return  much;
    }
}
