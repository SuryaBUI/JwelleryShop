package com.shop.jewelry_erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.jewelry_erp.entity.OldItem;

public interface OldItemRepository extends JpaRepository<OldItem, Long> {
}