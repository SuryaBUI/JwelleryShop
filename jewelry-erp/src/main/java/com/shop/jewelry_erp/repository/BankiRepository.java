package com.shop.jewelry_erp.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.jewelry_erp.entity.Banki;

public interface BankiRepository extends JpaRepository<Banki, Long> {

Page<Banki> findByCustomer_CustomerId(Long customerId, Pageable pageable);
List<Banki> findByParentBanki(Banki parentBanki);
Page<Banki> findByCustomer_CustomerIdAndPrincipalAmount(
        Long customerId,
        double principalAmount,
        Pageable pageable
);
Page<Banki> findByCustomer_CustomerIdAndBankiDateBetween(
        Long customerId,
        LocalDate startDate,
        LocalDate endDate,
        Pageable pageable
);
Page<Banki> findByCustomer_CustomerIdAndPrincipalAmountAndBankiDateBetween(
        Long customerId,
        double amount,
        LocalDate startDate,
        LocalDate endDate,
        Pageable pageable
);
}