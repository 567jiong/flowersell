package org.example.Factory;

import org.example.DAO.CustomerDAO;
import org.example.DAO.FlowerDAO;
import org.example.DAO.FlowerstoreDAO;
import org.example.DAO.Impl.CustomerDAOImpl;
import org.example.DAO.Impl.FlowerDAOImpl;
import org.example.DAO.Impl.FlowerstoreDAOImpl;
import org.example.DAO.Impl.OrdersDAOImpl;
import org.example.DAO.OrdersDAO;

public class DBFactory {
    private static DBFactory factory;

    public static DBFactory getInstance(){
        if(factory == null){
            factory = new DBFactory();
        }
        return factory;
    }

    public CustomerDAO getCustmerDAO(){
        return new CustomerDAOImpl();
    }

    public FlowerDAO getFlowerDAO(){
        return new FlowerDAOImpl();
    }

    public FlowerstoreDAO getFlowerstoreDAO(){
        return new FlowerstoreDAOImpl();
    }

    public OrdersDAO getOrdersDAO(){
        return new OrdersDAOImpl();
    }
}
