package com.shop.jewelry_erp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.jewelry_erp.dto.CustomerRequest;
import com.shop.jewelry_erp.entity.Customer;
import com.shop.jewelry_erp.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Customer createCustomer(CustomerRequest req) {

        Customer c = new Customer();
        c.setName(req.getName());
        c.setAddress(req.getAddress());
        c.setContactNumber(req.getContactNumber());
        c.setAlternateNumber(req.getAlternateNumber());

        return repository.save(c);
    }
}