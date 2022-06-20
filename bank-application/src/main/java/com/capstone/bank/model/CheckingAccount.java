package com.capstone.bank.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "checking")
public class CheckingAccount extends BankAccount {

	private String type = "checking";
	
	public CheckingAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CheckingAccount(Long accountNumber, double balance, Long userId) {
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
