package com.shop.jewelry_erp.controller;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.jewelry_erp.dto.SellingItemRequest;
import com.shop.jewelry_erp.entity.SellingItem;
import com.shop.jewelry_erp.flow.SellingItemFlowService;
import com.shop.jewelry_erp.repository.SellingItemRepository;
import com.shop.jewelry_erp.service.SellingItemService;

@RestController
@RequestMapping("/flow")
public class SellingFlowController {

    @Autowired
    private SellingItemFlowService flowService;

    @Autowired
    private SellingItemService saveService;
    
    @Autowired
    private SellingItemRepository sellingRepo;

    // Step 1
    @PostMapping("/step1")
    public String step1(Long customerId, String itemName) {
        flowService.createFlow(customerId, itemName);
        return "Item name saved. Enter weight.";
    }

    // Step 2
    @PostMapping("/step2")
    public String step2(Long customerId, double weight) {
        flowService.setWeight(customerId, weight);
        return "Weight saved. Enter description.";
    }

    // Step 3
    @PostMapping("/step3")
    public String step3(Long customerId, String description) {
        flowService.setDescription(customerId, description);
        return "Enter purity.";
    }

    // Step 4
    @PostMapping("/step4")
    public String step4(Long customerId, double purity) {
        flowService.setPurity(customerId, purity);
        return "Enter rate.";
    }

    // Step 5 (calculate)
    @PostMapping("/step5")
    public double step5(Long customerId, double ratePer10Gram) {
    	flowService.setRate(customerId, ratePer10Gram);
        return flowService.calculate(customerId, ratePer10Gram);
    }

    // Final Step
    @PostMapping("/final")
    public String finalStep(Long customerId, boolean save, double finalPrice) {

        if (!save) {
            flowService.clear(customerId);
            return "Data discarded";
        }

        SellingItemRequest req = flowService.get(customerId);
        saveService.save(req, finalPrice);

        flowService.clear(customerId);

        return "Saved successfully";
    }
    
 // 🔥 THIS IS REQUIRED FOR MAPPING
    @GetMapping("/today")
    public List<SellingItem> getTodaySelling() {

        return sellingRepo.findAll().stream()
                .filter(s -> s.getReceiptDate() != null &&
                             s.getReceiptDate().equals(LocalDate.now()))
                .toList();
    }
}