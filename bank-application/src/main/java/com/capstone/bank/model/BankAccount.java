package com.capstone.bank.model;

import java.util.Random;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "account_type")
public abstract class BankAccount {
	
	@Id
	@Column(name = "account_number")
	protected Long accountNumber;
	
	@Column(name = "balance")
	protected double balance;
	
	@Column(name = "user_id")
	protected Long userId;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_number")
	private Set<Transaction> transactions;
	
//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
//	@JoinColumn()
//	private User user;
	
	public BankAccount() {
		this.accountNumber = generateAccountNumber();
	}

	public BankAccount(Long accountNumber, double balance, Long userId) {
		super();
		this.accountNumber = generateAccountNumber();
		this.balance = balance;
		this.userId = userId;
//		this.user = user;
	}
	
	public Long generateAccountNumber() {
		return (long) (1000000 + new Random().nextInt(9000000));
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Set<Transaction> getTransactions() {
		return this.transactions;
	}
	
	public abstract String getType();

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
	
	
	
}
