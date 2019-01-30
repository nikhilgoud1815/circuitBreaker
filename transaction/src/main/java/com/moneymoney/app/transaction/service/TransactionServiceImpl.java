package com.moneymoney.app.transaction.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moneymoney.app.transaction.entity.Transaction;
import com.moneymoney.app.transaction.repository.TransactionRepository;
import com.moneymoney.app.transaction.transactionType.TransactionType;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository repository;

	

	@Override
	public Double deposit(int accountNumber, String transactioDetails, double currentBalance, double amount) {
		Transaction transaction = new Transaction();
		transaction.setAccountNumber(accountNumber);
		transaction.setAmount(amount);
		currentBalance = currentBalance + amount;
		System.out.println(""+currentBalance);
		transaction.setCurrentBalance(currentBalance);
		transaction.setTransactionDate(LocalDateTime.now());
		transaction.setTransactionType(TransactionType.DEPOSIT);
		repository.save(transaction);
		return currentBalance;
	}

	/*
	 * @Override public Double[] fundTransfer(int senderAccountNumber,String
	 * transactioDetails,double currentBalance,int recieverAccountNumber, double
	 * amount) { Double senderBalance = withdraw(senderAccountNumber,
	 * transactioDetails, currentBalance, amount); Double recieverBalance =
	 * deposit(recieverAccountNumber, transactioDetails, currentBalance, amount);
	 * return new Double[] {senderBalance,recieverBalance};
	 * 
	 * }
	 */

	@Override
	public List<Transaction> getStatement(LocalDate startDate, LocalDate endDate) {
		return null;

	}

	
	
	  @Override
	  public Double withdraw(int accountNumber, String transactioDetails,
	  double currentBalance, double amount)
	  { 
		  Transaction transaction = new
	  Transaction(); transaction.setAccountNumber(accountNumber);
	  transaction.setAmount(amount); 
	  transaction.setCurrentBalance(currentBalance);
	  currentBalance = currentBalance - amount;
	  transaction.setTransactionDate(LocalDateTime.now());
	  transaction.setTransactionType(TransactionType.WITHDRAW);
	  repository.save(transaction); 
	  return currentBalance;
	  }

	@Override
	public List<Transaction> getStatement() {
		return repository.findAll();
	}

	

}
