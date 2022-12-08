package org.example.DAO;

import org.example.POJO.Orders;

import java.util.List;

public interface OrdersDAO {
    void addOrder(Orders orders);
    List<Orders> findOrders(String UserName);
    List<Orders> findAllOrders();
}
