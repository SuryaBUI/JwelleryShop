package com.shop.jewelry_erp.entity;


import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "banki")
public class Banki {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankiId;

    private String customerName;
    private String address;

    @Column(nullable = false)
    private double principalAmount;
    
    @Column(nullable = false)
    private boolean isActive = true;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false)
    private double approxWeight;

    @Column(nullable = false)
    private LocalDate bankiDate;

    // 🔗 relation
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;
    
    @ManyToOne
    @JoinColumn(name = "parent_banki_id")
    @JsonBackReference
    private Banki parentBanki;
    
    @OneToMany(mappedBy = "parentBanki")
    @JsonManagedReference
    private List<Banki> childBankis;

	public Long getBankiId() {
		return bankiId;
	}

	public void setBankiId(Long bankiId) {
		this.bankiId = bankiId;
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

	public LocalDate getBankiDate() {
		return bankiDate;
	}

	public void setBankiDate(LocalDate bankiDate) {
		this.bankiDate = bankiDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Banki getParentBanki() {
		return parentBanki;
	}

	public void setParentBanki(Banki parentBanki) {
		this.parentBanki = parentBanki;
	}

	public List<Banki> getChildBankis() {
		return childBankis;
	}

	public void setChildBankis(List<Banki> childBankis) {
		this.childBankis = childBankis;
	}

    // getters setters
    
}