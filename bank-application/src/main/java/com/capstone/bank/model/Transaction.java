package com.capstone.bank.model;

import java.time.LocalDateTime;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {
	
	@Id
	@Column(name = "transaction_id")
	private Long transactionId;
	
	@Column(name = "date")
	private LocalDateTime dateOfTransaction;
	
	@Column(name = "transaction_amount")
	private double transactionAmount;
	
	@Column(name = "account_number")
	private Long accountNumber;
	
	public Transaction() {
		this.dateOfTransaction = LocalDateTime.now();
		this.transactionId = generateTransactionId();
	}

	public Transaction(double transactionAmount,
			Long accountNumber) {
		super();
		this.transactionId = generateTransactionId();
		this.dateOfTransaction = LocalDateTime.now();
		this.transactionAmount = transactionAmount;
		this.accountNumber = accountNumber;
	}
	
	public Long generateTransactionId() {
		return (long) (100000000 + new Random().nextInt(900000000));
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public LocalDateTime getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(LocalDateTime dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

}
