package com.shop.jewelry_erp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.jewelry_erp.dto.CustomerRequest;
import com.shop.jewelry_erp.entity.Customer;
import com.shop.jewelry_erp.repository.CustomerRepository;
import com.shop.jewelry_erp.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private CustomerService service;

    @GetMapping("/all")
    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    @PostMapping("/create")
    public Customer create(@RequestBody CustomerRequest req) {
        return service.createCustomer(req);
    }
}