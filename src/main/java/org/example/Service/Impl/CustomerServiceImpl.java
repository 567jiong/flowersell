package org.example.Service.Impl;

import org.example.DAO.CustomerDAO;
import org.example.DAO.Impl.CustomerDAOImpl;
import org.example.Factory.DBFactory;
import org.example.Service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

    private CustomerDAO customerDAO = DBFactory.getInstance().getCustmerDAO();
    @Override
    public void addCustomer(String name, String password) {
        customerDAO.addCustomer(name,password);
    }

    @Override
    public int login(String name, String password) {
        return customerDAO.login(name,password);
    }

    @Override
    public Boolean findUserName(String name) {
        return customerDAO.findUserName(name);
    }
}
