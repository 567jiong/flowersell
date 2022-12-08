package org.example.Service.Impl;

import org.example.DAO.Impl.OrdersDAOImpl;
import org.example.DAO.OrdersDAO;
import org.example.Factory.DBFactory;
import org.example.POJO.Orders;
import org.example.Service.OrdersService;

import java.util.List;

public class OrdersServiceImpl implements OrdersService {

    private OrdersDAO ordersDAO= DBFactory.getInstance().getOrdersDAO();
    @Override
    public void addOrder(Orders orders) {
        ordersDAO.addOrder(orders);
    }

    @Override
    public List<Orders> findOrders(String buyUserName) {
        return ordersDAO.findOrders(buyUserName);
    }

    @Override
    public List<Orders> findAllOrders() {
        return ordersDAO.findAllOrders();
    }
}
