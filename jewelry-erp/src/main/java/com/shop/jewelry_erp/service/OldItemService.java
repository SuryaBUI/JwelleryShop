package com.shop.jewelry_erp.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.jewelry_erp.dto.OldItemRequest;
import com.shop.jewelry_erp.entity.Customer;
import com.shop.jewelry_erp.entity.OldItem;
import com.shop.jewelry_erp.entity.SellingItem;
import com.shop.jewelry_erp.repository.CustomerRepository;
import com.shop.jewelry_erp.repository.OldItemRepository;
import com.shop.jewelry_erp.repository.SellingItemRepository;

@Service
public class OldItemService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SellingItemRepository sellingRepo;

    @Autowired
    private OldItemRepository oldRepo;

    public OldItem save(OldItemRequest req, double price, Long sellingItemId) {

        OldItem item = new OldItem();

        item.setItemName(req.getItemName());
        item.setWeight(req.getWeight());
        item.setDescription(req.getDescription());
        item.setPrice(price);
        item.setReceiptDate(LocalDate.now());

        item.setCustomer(customerRepository.findById(req.getCustomerId()).orElseThrow());

        if (sellingItemId != null) {

            SellingItem selling = sellingRepo.findById(sellingItemId)
                    .orElseThrow();

            // 🔥 IMPORTANT VALIDATION
            if (!selling.getReceiptDate().equals(LocalDate.now())) {
                throw new RuntimeException("❌ Only today's selling items can be mapped");
            }

            item.setSellingItem(selling);
        }

        return oldRepo.save(item);
    }
    
    public OldItem saveOldItem(Long customerId,
                               String itemName,
                               double weight,
                               String description,
                               double price,
                               Long sellingItemId) {

        Customer customer = customerRepository.findById(customerId).orElseThrow();

        OldItem item = new OldItem();
        item.setItemName(itemName);
        item.setWeight(weight);
        item.setDescription(description);
        item.setPrice(price);
        item.setReceiptDate(java.time.LocalDate.now());
        item.setCustomer(customer);

        if (sellingItemId != null) {
            SellingItem selling = sellingRepo.findById(sellingItemId).orElseThrow();
            item.setSellingItem(selling);
        }

        return oldRepo.save(item);
    }
}