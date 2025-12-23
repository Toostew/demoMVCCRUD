package com.toostew.demoMVCCRUD.service.impl;

import com.toostew.demoMVCCRUD.DAO.CustomerDAO;
import com.toostew.demoMVCCRUD.entity.Customer;
import com.toostew.demoMVCCRUD.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerDAO customerDAO;

    public CustomerServiceImpl(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    //set all CRUD operations

    public void createCustomer(Customer customer) {
        customerDAO.createCustomer(customer);
    }


    public Customer getCustomer(int Id) {
        return customerDAO.getCustomerById(Id);
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    public void updateCustomer(Customer customer) {
        customerDAO.updateCustomer(customer);
    }

    public void deleteCustomer(int Id) {
        customerDAO.deleteCustomer(Id);
    }




}
