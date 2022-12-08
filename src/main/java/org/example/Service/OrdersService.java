package org.example.Service;

import org.example.POJO.Flower;
import org.example.POJO.Orders;

import java.util.List;

public interface OrdersService {
    void addOrder(Orders orders);
    List<Orders> findOrders(String UserName);
    List<Orders> findAllOrders();
}
