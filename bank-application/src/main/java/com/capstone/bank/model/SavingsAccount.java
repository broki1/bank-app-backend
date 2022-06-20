package com.capstone.bank.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "savings")
public class SavingsAccount extends BankAccount {

	private String type = "savings";
	
	public SavingsAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SavingsAccount(Long accountNumber, double balance, Long userId) {
		super(accountNumber, balance, userId);
		// TODO Auto-generated constructor stub
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
