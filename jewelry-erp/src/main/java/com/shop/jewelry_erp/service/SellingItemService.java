package com.shop.jewelry_erp.service;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.jewelry_erp.dto.SellingItemRequest;
import com.shop.jewelry_erp.entity.Customer;
import com.shop.jewelry_erp.entity.SellingItem;
import com.shop.jewelry_erp.repository.CustomerRepository;
import com.shop.jewelry_erp.repository.SellingItemRepository;

@Service
public class SellingItemService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SellingItemRepository sellingItemRepository;

    public SellingItem save(SellingItemRequest req, double finalPrice) {

        Customer customer = customerRepository.findById(req.getCustomerId())
                .orElseThrow();

        SellingItem item = new SellingItem();
        item.setItemName(req.getItemName());
        item.setWeight(req.getWeight());
        item.setDescription(req.getDescription());
        item.setPrice(finalPrice);
        item.setRate(req.getRate());
        item.setReceiptDate(LocalDate.now());
        item.setCustomer(customer);

        return sellingItemRepository.save(item);
    }
}