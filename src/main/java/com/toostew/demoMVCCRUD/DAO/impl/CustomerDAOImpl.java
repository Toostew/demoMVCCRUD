package com.toostew.demoMVCCRUD.DAO.impl;

import com.toostew.demoMVCCRUD.DAO.CustomerDAO;
import com.toostew.demoMVCCRUD.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    private EntityManager em;


    public CustomerDAOImpl() {

    }
    @Autowired
    public CustomerDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void createCustomer(Customer customer) {
        System.out.println("Created new Customer: " + customer.getFirstName() + " " + customer.getLastName());
        em.persist(customer);
    }

    @Override
    public Customer getCustomerById(int Id) {
        Customer temp = em.find(Customer.class, Id);
        System.out.print("Returning customer with id " + temp.getFirstName() + " " + temp.getLastName());
        return temp;
    }

    @Override
    public List<Customer> getAllCustomers() {
        TypedQuery<Customer> typedQuery = em.createQuery("FROM Customer ORDER BY lastName", Customer.class);
        return typedQuery.getResultList();
    }

    public void updateCustomer(Customer customer) {
        Customer temp = em.find(Customer.class, customer.getId());
        System.out.println("updated " + temp.getFirstName() + " " + temp.getLastName());
        temp.setFirstName(customer.getFirstName());
        temp.setLastName(customer.getLastName());
        temp.setEmail(customer.getEmail());
        em.merge(temp);
    }

    public void deleteCustomer(int Id) {
        Customer temp = em.find(Customer.class, Id);
        System.out.println("Deleting Customer " + temp.getFirstName() + " " + temp.getLastName());
        em.remove(temp);
    }

}
