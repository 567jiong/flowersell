package org.example.Service;

public interface CustomerService {
    void addCustomer(String name,String password);
    int login(String name,String password);
    Boolean findUserName(String name);
}
