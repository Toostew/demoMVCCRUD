package com.toostew.demoMVCCRUD.service;

import com.toostew.demoMVCCRUD.entity.Customer;

import java.util.List;

public interface CustomerService {

    public void createCustomer(Customer customer);

    public Customer getCustomer(int Id);

    public List<Customer> getAllCustomers();

    public void updateCustomer(Customer customer);

    public void deleteCustomer(int Id);


}
