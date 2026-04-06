package com.shop.jewelry_erp.flow;


import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

import com.shop.jewelry_erp.dto.OldItemRequest;

@Service
public class OldItemFlowService {

    private Map<Long, OldItemRequest> tempStorage = new HashMap<>();

    public void createFlow(Long customerId, String itemName) {
        OldItemRequest req = new OldItemRequest();
        req.setCustomerId(customerId);
        req.setItemName(itemName);
        tempStorage.put(customerId, req);
    }

    public void setWeight(Long customerId, double weight) {
        tempStorage.get(customerId).setWeight(weight);
    }

    public void setDescription(Long customerId, String desc) {
        tempStorage.get(customerId).setDescription(desc);
    }

    public void setPurity(Long customerId, double purity) {
        tempStorage.get(customerId).setPurity(purity);
    }

    public double calculate(Long customerId, double ratePer10Gram) {

        OldItemRequest req = tempStorage.get(customerId);
        req.setRatePer10Gram(ratePer10Gram);

        double ratePerGram = ratePer10Gram / 10;
        double pureRate = ratePerGram * (req.getPurity() / 100);

        return pureRate * req.getWeight();
    }

    public OldItemRequest get(Long customerId) {
        return tempStorage.get(customerId);
    }

    public void clear(Long customerId) {
        tempStorage.remove(customerId);
    }
}