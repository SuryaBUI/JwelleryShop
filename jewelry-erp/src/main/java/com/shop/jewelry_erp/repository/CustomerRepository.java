package com.shop.jewelry_erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.jewelry_erp.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}