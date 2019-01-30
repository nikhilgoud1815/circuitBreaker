package com.cg.rest.services.springBoot.Resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.rest.services.springBoot.Entity.Account;
import com.cg.rest.services.springBoot.services.AccountService;

@RefreshScope
@RestController
@RequestMapping("/accounts")
public class AccountResource {

	@Autowired
	private AccountService service;

	@PostMapping
	public void addNewAccount(@RequestBody Account account) {
		service.addNewAccount(account);
	}

	@GetMapping()
	public List<Account> getAllAccounts() {
		return service.getAllAccounts();
	}

	@GetMapping("/{account}")
	public Optional<Account> getAccountById(@PathVariable("account") int accountNumber) {
		return service.getAccountById(accountNumber);
	}

	@GetMapping("/{accountNumber}/balance")
	public double getCurrentBalance(@PathVariable int accountNumber) {
		return service.getCurrentBalance(accountNumber);
	}

	@DeleteMapping("/{accountNumber}")
	public void deleteAccountById(@PathVariable int accountNumber) {
		service.deleteAccountById(accountNumber);
	}

	/*
	 * @PutMapping("/{accountNumber}") public void updateAccountById(@RequestBody
	 * Account account) { service.updateAccountById(account); }
	 */

	@PutMapping("/{accountNumber}")
	public void updateAccountByBalance(@PathVariable int accountNumber,
			@RequestParam("currentBalance") double currentBalance) {
		Optional<Account> object = service.getAccountById(accountNumber);
		Account account = object.get();
		account.setCurrentBalance(currentBalance);
		service.updateAccountByBalance(account);
	}

}
