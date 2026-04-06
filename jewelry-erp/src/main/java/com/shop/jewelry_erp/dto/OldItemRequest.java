package com.shop.jewelry_erp.dto;


public class OldItemRequest {

    private Long customerId;
    private String itemName;
    private double weight;
    private String description;
    private double purity;
    private double ratePer10Gram;
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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
	public double getPurity() {
		return purity;
	}
	public void setPurity(double purity) {
		this.purity = purity;
	}
	public double getRatePer10Gram() {
		return ratePer10Gram;
	}
	public void setRatePer10Gram(double ratePer10Gram) {
		this.ratePer10Gram = ratePer10Gram;
	}

    // getters setters
}