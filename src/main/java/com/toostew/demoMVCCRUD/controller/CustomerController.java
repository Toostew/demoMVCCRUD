package com.toostew.demoMVCCRUD.controller;


import com.toostew.demoMVCCRUD.entity.Customer;
import com.toostew.demoMVCCRUD.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tools.jackson.databind.json.JsonMapper;

@RequestMapping("/customers")
@Controller
public class CustomerController {

    private CustomerService customerService;
    private JsonMapper jsonMapper;

    @Autowired
    public CustomerController(CustomerService customerService, JsonMapper jsonMapper) {
        this.customerService = customerService;
        this.jsonMapper = jsonMapper;
    }

    @GetMapping("/list/{id}")
    public String getCustomerById(@PathVariable int id, Model model) {
        Customer temp = customerService.getCustomer(id);
        model.addAttribute("customer", temp);

        return "test";
    }



}
