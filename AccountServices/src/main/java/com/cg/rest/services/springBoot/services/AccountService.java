package com.cg.rest.services.springBoot.services;

import java.util.List;
import java.util.Optional;

import com.cg.rest.services.springBoot.Entity.Account;
import com.cg.rest.services.springBoot.Entity.SavingsAccount;

public interface AccountService {

	void addNewAccount(Account account);

	List<Account> getAllAccounts();

	Optional<Account> getAccountById(int accountNumber);

	/* void updateAccountById(SavingsAccount savingsAccount); */

	void deleteAccountById(int accountNumber);

	/* void updateAccountById(Account account); */

	double getCurrentBalance(int accountNumber); 

	 void updateAccountByBalance(Account account); 

	

}
