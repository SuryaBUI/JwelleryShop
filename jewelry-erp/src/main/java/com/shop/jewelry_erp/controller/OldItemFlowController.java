package com.shop.jewelry_erp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.jewelry_erp.dto.OldItemRequest;
import com.shop.jewelry_erp.entity.SellingItem;
import com.shop.jewelry_erp.flow.OldItemFlowService;
import com.shop.jewelry_erp.repository.SellingItemRepository;
import com.shop.jewelry_erp.service.OldItemService;

@RestController
@RequestMapping("/oldflow")
public class OldItemFlowController {

    @Autowired
    private OldItemFlowService flowService;
    
    @Autowired
    private SellingItemRepository sellingRepo;

    @Autowired
    private OldItemService saveService;

    @PostMapping("/step1")
    public String step1(Long customerId, String itemName) {
        flowService.createFlow(customerId, itemName);
        return "Saved";
    }

    @PostMapping("/step2")
    public String step2(Long customerId, double weight) {
        flowService.setWeight(customerId, weight);
        return "Saved";
    }

    @PostMapping("/step3")
    public String step3(Long customerId, String description) {
        flowService.setDescription(customerId, description);
        return "Saved";
    }

    @PostMapping("/step4")
    public String step4(Long customerId, double purity) {
        flowService.setPurity(customerId, purity);
        return "Saved";
    }

    @PostMapping("/step5")
    public double step5(Long customerId, double ratePer10Gram) {
        return flowService.calculate(customerId, ratePer10Gram);
    }

    @PostMapping("/final")
    public String finalStep(Long customerId, boolean save, double finalPrice, Long sellingItemId) {

        if (!save) {
            flowService.clear(customerId);
            return "Discarded";
        }

        try {
            OldItemRequest req = flowService.get(customerId);
            saveService.save(req, finalPrice, sellingItemId);

            flowService.clear(customerId);

            return "Saved successfully";

        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
    @GetMapping("/finalPrice")
    public double finalPrice(@RequestParam Long sellingId,
                             @RequestParam double oldPrice) {

        SellingItem s = sellingRepo.findById(sellingId)
                .orElseThrow(() -> new RuntimeException("Selling item not found"));

        return s.getPrice() - oldPrice;
    }
}