package com.shop.jewelry_erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.jewelry_erp.entity.SellingItem;

public interface SellingItemRepository extends JpaRepository<SellingItem, Long> {
}