package org.example.DAO;

public interface CustomerDAO {
    int login(String name,String password);
    void addCustomer(String name,String password);
    Boolean findUserName(String name);
}
