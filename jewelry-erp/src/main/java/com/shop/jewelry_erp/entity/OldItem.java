package com.shop.jewelry_erp.entity;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class OldItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oldItemId;

    private String itemName;
    private double weight;
    private String description;
    private double price;
    private LocalDate receiptDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // 🔥 Mapping with SellingItem
    @ManyToOne
    @JoinColumn(name = "selling_item_id")
    private SellingItem sellingItem;

    @Column(nullable = false)
    private double rate;
    
	public Long getOldItemId() {
		return oldItemId;
	}

	public void setOldItemId(Long oldItemId) {
		this.oldItemId = oldItemId;
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

	public SellingItem getSellingItem() {
		return sellingItem;
	}

	public void setSellingItem(SellingItem sellingItem) {
		this.sellingItem = sellingItem;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

    // getters setters
}