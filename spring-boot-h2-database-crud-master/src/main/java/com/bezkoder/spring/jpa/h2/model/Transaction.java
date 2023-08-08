package com.bezkoder.spring.jpa.h2.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long customerId;
	private double transactionAmount;
	private Date transactionDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Transaction(Long id, Long customerId, double transactionAmount, Date transactionDate) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.transactionAmount = transactionAmount;
		this.transactionDate = transactionDate;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", customerId=" + customerId + ", transactionAmount=" + transactionAmount
				+ ", transactionDate=" + transactionDate + "]";
	}
	
	

}
