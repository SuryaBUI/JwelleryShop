package com.shop.jewelry_erp.dto;

public class BankiCalculationResponse {

    private double interest;
    private double totalAmount;

    public BankiCalculationResponse(double interest, double totalAmount) {
        this.interest = interest;
        this.totalAmount = totalAmount;
    }

	public double getInterest() {
		return interest;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

    // getters
}