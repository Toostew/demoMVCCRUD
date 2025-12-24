package com.toostew.demoMVCCRUD.controller;


import com.toostew.demoMVCCRUD.entity.Customer;
import com.toostew.demoMVCCRUD.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;

@RequestMapping("/customers")
@Controller
public class CustomerController {

    private CustomerService customerService;
    private JsonMapper jsonMapper;

    @Autowired
    public CustomerController(CustomerService customerService, JsonMapper jsonMapper) {
        this.customerService = customerService;
        this.jsonMapper = jsonMapper; //used for partial update via PATCH
    }


    //we are using thymeleaf and thus are required to return thymeleaf templates
    //this is for debugging, deprecated now
    //NOTE:/{value} is dangerous because anything in this directory is treated like a value
    //so something like /customers/delicious, id = delicious
    /*
    @GetMapping("/{id}")
    public String getCustomerById(@PathVariable int id, Model model) {
        Customer temp = customerService.getCustomer(id);
        model.addAttribute("customer", temp);

        return "test";
    }
    */


    @GetMapping("/list")
    public String getAllCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        for (Customer customer : customers) {
            System.out.println("Customer: "+customer.getFirstName() + customer.getLastName());
        }
        model.addAttribute("customers", customers);

        return "list";
    }

    @GetMapping("/createCustomer-form")
    public String createCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "createCustomer-form";
    }

    @PostMapping("/processCustomer-form")
    public String processCustomerForm(@ModelAttribute Customer customer, Model model) {
        Customer temp = new  Customer();
        temp.setFirstName(customer.getFirstName());
        temp.setLastName(customer.getLastName());
        temp.setEmail(customer.getEmail());
        customerService.createCustomer(temp);
        System.out.println("created new Customer: "+customer.getFirstName() +" "+ customer.getLastName() +" "+ customer.getEmail());
        //redirect is used to redirect the user
        //by default it returns to root path, ignoring request mapping
        //in this example, we redirect the user localhost:8080/customers/list
        //the appropriate get method will respond to this
        return "redirect:/customers/list";
    }


    //updating


    @GetMapping("/updateCustomer-form")
    public String updateCustomerForm(@RequestParam(name = "customerId") int id, Model model) {
        Customer temp = customerService.getCustomer(id);
        model.addAttribute("prevCustomer", temp);
        return "updateCustomer-form";
    }

    //this accepts a post request from the html
    //HTML forms only support GET and POST requests
    @PostMapping("/updateCustomer")
    public String updateCustomer(@ModelAttribute Customer customer) {
        Customer temp = customerService.getCustomer(customer.getId());
        temp.setFirstName(customer.getFirstName());
        temp.setLastName(customer.getLastName());
        temp.setEmail(customer.getEmail());
        System.out.println("altered Customer");
        customerService.updateCustomer(temp);
        return "redirect:/customers/list";
    }

    //deleting
    @GetMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam(name = "customerId") int id) {
        System.out.println("deleting Customer: " + customerService.getCustomer(id));
        customerService.deleteCustomer(id);
        return "redirect:/customers/list";
    }

}
