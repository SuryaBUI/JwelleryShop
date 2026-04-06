package com.shop.jewelry_erp.entity;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "selling_item")
public class SellingItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellingItemId;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private double weight;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private LocalDate receiptDate;

    // 🔗 Many items belong to one customer
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore   // 🔥 THIS LINE FIXES LOOP
    private Customer customer;

    @Column(nullable = false)
    private double rate;   // 🔥 rate per 10g (or your unit)
    
    // 🔹 Constructors
    public SellingItem() {}

    public SellingItem(String itemName, double weight, String description,
                       double price, LocalDate receiptDate, Customer customer) {
        this.itemName = itemName;
        this.weight = weight;
        this.description = description;
        this.price = price;
        this.receiptDate = receiptDate;
        this.customer = customer;
    }

    // 🔹 Getters and Setters

    public Long getSellingItemId() {
        return sellingItemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(LocalDate receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public void setSellingItemId(Long sellingItemId) {
		this.sellingItemId = sellingItemId;
	}
}