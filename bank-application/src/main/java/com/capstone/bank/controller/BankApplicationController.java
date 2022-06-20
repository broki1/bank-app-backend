package com.capstone.bank.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.bank.model.CheckingAccount;
import com.capstone.bank.model.SavingsAccount;
import com.capstone.bank.model.Transaction;
import com.capstone.bank.model.User;
import com.capstone.bank.service.BankApplicationService;

@RestController
public class BankApplicationController {
	
	@Autowired
	BankApplicationService service;
	
	@CrossOrigin(origins="*")
	@PostMapping("/user")
	public User createUser(@RequestBody User user) {
		return service.createUser(user);
	}
	
	@CrossOrigin(origins="*")
	@PostMapping("/deposit")
	public Transaction deposit(@RequestBody Transaction transaction) {
		return service.deposit(transaction);
	}
	
	@CrossOrigin(origins="*")
	@PostMapping("/withdraw")
	public Transaction withdraw(@RequestBody Transaction transaction) {
		return service.withdraw(transaction);
	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/transactions/{accountNumber}")
	public Set<Transaction> getTransactions(@PathVariable(value = "accountNumber") Long accountNumber) {
		return service.listTransactions(accountNumber);
	}
	
	@CrossOrigin(origins="*")
	@DeleteMapping("/delete/{id}")
	public void deleteUser(@PathVariable(value = "id") Long id) {
		service.deleteUser(id);
	}
	
	@CrossOrigin(origins="*")
	@PostMapping("/transfer/{fromAccountNumber}/{toAccountNumber}/{amount}")
	public List<Transaction> transferBetweenAccounts(@PathVariable(value = "fromAccountNumber") Long fromAccountNumber,
			@PathVariable(value = "toAccountNumber") Long toAccountNumber, @PathVariable(value = "amount") double amount) {
		return service.transfer(fromAccountNumber, toAccountNumber, amount);
	}
	
	@CrossOrigin(origins="*")
	@PostMapping("/transfer/{userId}")
	public List<Transaction> transferBetweenUsers(@PathVariable(value = "userId") Long userId, @RequestBody Transaction fromTransaction) {
		Long toAccountNumber = service.getCheckingAccount(service.getUser(userId)).getAccountNumber();
		
		return service.transfer(fromTransaction.getAccountNumber(), toAccountNumber, fromTransaction.getTransactionAmount());
	}
}
