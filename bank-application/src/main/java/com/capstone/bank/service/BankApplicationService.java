package com.capstone.bank.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.bank.model.BankAccount;
import com.capstone.bank.model.CheckingAccount;
import com.capstone.bank.model.Transaction;
import com.capstone.bank.model.User;
import com.capstone.bank.repository.BankAccountRepository;
import com.capstone.bank.repository.TransactionRepository;
import com.capstone.bank.repository.UserRepository;

@Service
public class BankApplicationService {
	
	@Autowired
	UserRepository userRepo;

	@Autowired
	BankAccountRepository bankAccountRepo;
	
	@Autowired
	TransactionRepository transactionRepo;
	
//	Deposit API, User will send the amount and account type.
//	The API Service needs to create the transaction object and save it into the transaction table.
//	It needs to add the amount to the correct account (savings/checkings).
	
//	Withdraw API, Same as deposit api but needs to subtract the amount sent.
	
//	List all transactions. The API will recieve the account number,
//	the API will return the list of transactions for that user.
	
	public User createUser(User user) {
		return userRepo.save(user);
	}
	
	public User getUser(Long userId) {
		return userRepo.findById(userId).get();
	}
	
	public BankAccount getCheckingAccount(User user) {
		for(int i = 0; i < user.getBankAccounts().size(); i++) {
			if(user.getBankAccounts().get(i).getType().equals("checking")) {
				return user.getBankAccounts().get(i);
			}
		}
		
		return null;
	}
	
	public Transaction deposit(Transaction transaction) {
		BankAccount account = bankAccountRepo.findById(transaction.getAccountNumber()).get();
		
		double transactionAmount = account.getBalance() + transaction.getTransactionAmount();
		
		account.setBalance(transactionAmount);
		
		return transactionRepo.save(transaction);
	}
	
	public Transaction withdraw(Transaction transaction) {
		BankAccount account = bankAccountRepo.findById(transaction.getAccountNumber()).get();
		
		double transactionAmount = account.getBalance() - transaction.getTransactionAmount();
		
		account.setBalance(transactionAmount);
		
		transaction.setTransactionAmount(transaction.getTransactionAmount() * -1);
		
		return transactionRepo.save(transaction);
	}
	
	public Set<Transaction> listTransactions(Long accountNumber) {
		return bankAccountRepo.findById(accountNumber).get().getTransactions();
	}
	
	public void deleteUser(Long id) {
		Long userId = userRepo.findById(id).get().getUserId();
		userRepo.deleteById(id);
	}
	
	public List<Transaction> transfer(Long fromAccountNumber, Long toAccountNumber, double transferAmount) {
		
		Transaction withdrawTransaction = withdraw(new Transaction(transferAmount, fromAccountNumber));
		Transaction depositTransaction = deposit(new Transaction(transferAmount, toAccountNumber));
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		
		transactions.add(withdrawTransaction);
		transactions.add(depositTransaction);
		
		return transactions; 
	}
	
	
}
