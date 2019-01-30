package com.moneymoney.app.transaction.service;

import java.time.LocalDate;
import java.util.List;

import com.moneymoney.app.transaction.entity.Transaction;

public interface TransactionService {
	
	List<Transaction> getStatement(LocalDate startDate, LocalDate endDate);

	
	
	 
	 
	Double deposit(int accountNumber, String transactioDetails, double currentBalance, double amount);

	/*
	 * Double withdraw(Integer accountNumber, String transactionDetails, Double
	 * currentBalance, Double amount);
	 */
	
	/*
	 * Double[] fundTransfer(int senderAccountNumber, String transactioDetails,
	 * double currentBalance, int recieverAccountNumber, double amount);
	 * 
	 */

	
	  Double withdraw(int accountNumber, String transactioDetails, double
	  currentBalance, double amount);





	List<Transaction> getStatement();
	 
	
}