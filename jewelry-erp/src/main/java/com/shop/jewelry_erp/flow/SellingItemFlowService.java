package com.shop.jewelry_erp.flow;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.shop.jewelry_erp.dto.SellingItemRequest;

@Service
public class SellingItemFlowService {

    private Map<Long, SellingItemRequest> tempStorage = new HashMap<>();

    public void createFlow(Long customerId, String itemName) {
        SellingItemRequest req = new SellingItemRequest();
        req.setCustomerId(customerId);
        req.setItemName(itemName);
        tempStorage.put(customerId, req);
    }

    public void setWeight(Long customerId, double weight) {
        tempStorage.get(customerId).setWeight(weight);
    }

    public void setDescription(Long customerId, String description) {
        tempStorage.get(customerId).setDescription(description);
    }

    public void setPurity(Long customerId, double purity) {
        tempStorage.get(customerId).setPurity(purity);
    }
    
    public void setRate(Long customerId, double rate) {
        tempStorage.get(customerId).setRate(rate);
    }

    public double calculate(Long customerId, double ratePer10Gram) {
        SellingItemRequest req = tempStorage.get(customerId);
        req.setRatePer10Gram(ratePer10Gram);

        double ratePerGram = ratePer10Gram / 10;
        double pureRate = ratePerGram * (req.getPurity() / 100);

        return pureRate * req.getWeight();
    }

    public SellingItemRequest get(Long customerId) {
        return tempStorage.get(customerId);
    }

    public void clear(Long customerId) {
        tempStorage.remove(customerId);
    }
}