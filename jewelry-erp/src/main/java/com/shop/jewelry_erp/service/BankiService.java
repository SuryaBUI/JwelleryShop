package com.shop.jewelry_erp.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.jewelry_erp.dto.BankiCalculationResponse;
import com.shop.jewelry_erp.dto.BankiRequest;
import com.shop.jewelry_erp.entity.Banki;
import com.shop.jewelry_erp.entity.Customer;
import com.shop.jewelry_erp.repository.BankiRepository;
import com.shop.jewelry_erp.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class BankiService {

    @Autowired
    private BankiRepository bankiRepo;

    @Autowired
    private CustomerRepository customerRepo;

    public Banki save(BankiRequest req) {

        Customer customer = customerRepo.findById(req.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Banki b = new Banki();

        b.setCustomer(customer);
        b.setCustomerName(req.getCustomerName());
        b.setAddress(req.getAddress());
        b.setPrincipalAmount(req.getPrincipalAmount());
        b.setDescription(req.getDescription());
        b.setApproxWeight(req.getApproxWeight());
        b.setBankiDate(LocalDate.now());
        b.setActive(true);

        // 🔥 PARENT LINKING
        if(req.getParentBankiId() != null){
            Banki parent = bankiRepo.findById(req.getParentBankiId())
                    .orElseThrow();
            // 🔥 VALIDATION
            if(!parent.isActive()){
                throw new RuntimeException("Cannot add amount. Banki is closed.");
            }
            b.setParentBanki(parent);
        }
        
        return bankiRepo.save(b);
    }
    
    public BankiCalculationResponse calculate(Long bankiId, double rate) {

        Banki main = bankiRepo.findById(bankiId)
                .orElseThrow();

        List<Banki> allEntries = new ArrayList<>();

        // add parent
        allEntries.add(main);

        // add children
        List<Banki> children = bankiRepo.findByParentBanki(main);
        allEntries.addAll(children);

        double totalInterest = 0;
        double totalPrincipal = 0;

        for (Banki b : allEntries) {

            long days = ChronoUnit.DAYS.between(b.getBankiDate(), LocalDate.now());

            double months = calculateMonths(days);

            double interest = (b.getPrincipalAmount() / 100) * rate * months;

            totalInterest += interest;
            totalPrincipal += b.getPrincipalAmount();
        }

        return new BankiCalculationResponse(
                totalInterest,
                totalPrincipal + totalInterest
        );
    }
    
 // 🔥 CUSTOM MONTH LOGIC
    private double calculateMonths(long days) {

        if (days <= 30) return 1;
        if (days <= 40) return 1;
        if (days <= 50) return 1.5;
        if (days <= 58) return 1.75;
        if (days <= 60) return 2;

        // beyond 60 → custom rounding
        double months = days / 30.0;

        int integerPart = (int) months;
        double decimalPart = months - integerPart;

        if (decimalPart <= 0.40) {
            return integerPart;
        } else if (decimalPart <= 0.60) {
            return integerPart + 0.5;
        } else if (decimalPart <= 0.90) {
            return integerPart + 0.75;
        } else {
            return integerPart + 1;
        }
    }
    
    @org.springframework.transaction.annotation.Transactional
    public void closeBanki(Long bankiId) {

        Banki banki = bankiRepo.findById(bankiId)
                .orElseThrow(() -> new RuntimeException("Banki not found"));

        closeRecursive(banki);
    }

    private void closeRecursive(Banki banki) {

        // 🔥 Close current
        banki.setActive(false);
        bankiRepo.save(banki);

        // 🔥 Find children
        List<Banki> children = bankiRepo.findByParentBanki(banki);

        // 🔁 Recursive close
        for (Banki child : children) {
            closeRecursive(child);
        }
    }
}