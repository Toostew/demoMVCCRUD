package com.toostew.demoMVCCRUD.DAO;

import com.toostew.demoMVCCRUD.entity.Customer;

import java.util.List;

public interface CustomerDAO {
    
    //CRUD operations

    void createCustomer(Customer customer);

    Customer getCustomerById(int Id);

    List<Customer> getAllCustomers();

    void updateCustomer(Customer customer);

    void deleteCustomer(int Id);

}
