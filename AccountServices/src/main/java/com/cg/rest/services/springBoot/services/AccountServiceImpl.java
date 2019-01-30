package com.cg.rest.services.springBoot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.rest.services.springBoot.Entity.Account;
import com.cg.rest.services.springBoot.Entity.SavingsAccount;
import com.cg.rest.services.springBoot.Repo.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountRepository repository;

	@Override
	public List<Account> getAllAccounts() {
		return repository.findAll();
	}

	@Override
	public void addNewAccount(Account account) {
		repository.save(account);
	}

	@Override
	public Optional<Account> getAccountById(int accountNumber) {
		return repository.findById(accountNumber);
	}

	@Override
	public void deleteAccountById(int accountNumber) {
		// TODO Auto-generated method stub
		repository.deleteById(accountNumber);

	}

	/*
	 * @Override public void updateAccountById(Account account) { // TODO
	 * Auto-generated method stub repository.save(account); }
	 */

	public double getCurrentBalance(int accountNumber) {

		Optional<Account> account = repository.findById(accountNumber);

		double currentBalance = account.get().getCurrentBalance();

		return currentBalance;
	}

	@Override
	public void updateAccountByBalance(Account account) {
		// TODO Auto-generated method stub
		repository.save(account);
	}

}