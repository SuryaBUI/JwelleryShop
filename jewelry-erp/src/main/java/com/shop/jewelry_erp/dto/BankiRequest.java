package com.shop.jewelry_erp.dto;


public class BankiRequest {

    private Long customerId;
    private String customerName;
    private String address;
    private double principalAmount;
    private String description;
    private double approxWeight;
    private Long parentBankiId;
    
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getPrincipalAmount() {
		return principalAmount;
	}
	public void setPrincipalAmount(double principalAmount) {
		this.principalAmount = principalAmount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getApproxWeight() {
		return approxWeight;
	}
	public void setApproxWeight(double approxWeight) {
		this.approxWeight = approxWeight;
	}
	public Long getParentBankiId() {
		return parentBankiId;
	}
	public void setParentBankiId(Long parentBankiId) {
		this.parentBankiId = parentBankiId;
	}

    // getters setters
}
