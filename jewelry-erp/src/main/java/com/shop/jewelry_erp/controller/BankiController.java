package com.shop.jewelry_erp.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.jewelry_erp.dto.BankiCalculationRequest;
import com.shop.jewelry_erp.dto.BankiCalculationResponse;
import com.shop.jewelry_erp.dto.BankiRequest;
import com.shop.jewelry_erp.entity.Banki;
import com.shop.jewelry_erp.repository.BankiRepository;
import com.shop.jewelry_erp.service.BankiService;

@RestController
@RequestMapping("/banki")
public class BankiController {

    @Autowired
    private BankiService service;
    
    @Autowired
    private BankiRepository bankiRepo;

    @PostMapping("/save")
    public String save(@RequestBody BankiRequest req) {

        service.save(req);

        return "Banki entry saved successfully";
    }
    
    @PostMapping("/calculate")
    public BankiCalculationResponse calculate(@RequestBody BankiCalculationRequest req) {

        return service.calculate(req.getBankiId(), req.getRate());
    }
    
    @GetMapping("/all")
    public Page<Banki> getAll(
            @RequestParam Long customerId,
            @RequestParam int page,
            @RequestParam(required = false) Double amount,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {

        Pageable pageable = PageRequest.of(page, 15);

        // 🔥 BOTH FILTERS
        if(amount != null && startDate != null && endDate != null){
            return bankiRepo
                .findByCustomer_CustomerIdAndPrincipalAmountAndBankiDateBetween(
                    customerId,
                    amount,
                    LocalDate.parse(startDate),
                    LocalDate.parse(endDate),
                    pageable
                );
        }

        // 🔥 ONLY DATE RANGE
        if(startDate != null && endDate != null){
            return bankiRepo
                .findByCustomer_CustomerIdAndBankiDateBetween(
                    customerId,
                    LocalDate.parse(startDate),
                    LocalDate.parse(endDate),
                    pageable
                );
        }

        // 🔥 ONLY AMOUNT
        if(amount != null){
            return bankiRepo
                .findByCustomer_CustomerIdAndPrincipalAmount(
                    customerId,
                    amount,
                    pageable
                );
        }

        // 🔥 DEFAULT
        return bankiRepo.findByCustomer_CustomerId(customerId, pageable);
    }
    
    @PostMapping("/addAmount")
    public String addAmount(@RequestBody BankiRequest req) {

        service.save(req);

        return "Amount added to existing banki";
    }
    
    @PostMapping("/close")
    public String close(@RequestParam Long bankiId) {

        service.closeBanki(bankiId);

        return "Banki closed successfully";
    }
}