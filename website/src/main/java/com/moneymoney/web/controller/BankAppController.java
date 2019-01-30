package com.moneymoney.web.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.moneymoney.web.entity.CurrentDataSet;
import com.moneymoney.web.entity.Transaction;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Controller
public class BankAppController {

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/")
	public String indexForm() {
		return "index";
	}

	@RequestMapping("/DepositLink")
	public String depositForm() {
		return "DepositForm";
	}
	// @HystrixCommand(fallbackMethod = "reliable")
	 
	@RequestMapping("/deposit")
	public String deposit(@ModelAttribute Transaction transaction, Model model) {
		restTemplate.postForEntity("http://zuul/transaction/transactions", transaction, null);
		model.addAttribute("message", "Success!");
		return "DepositForm";
	}

	@RequestMapping("/WithdrawLink")
	public String withdrawForm() {
		return "withdraw";
	}
	 //@HystrixCommand(fallbackMethod = "reliable")

	@RequestMapping("/withdrawForm")
	public String withdraw(@ModelAttribute Transaction transaction, Model model) {
		restTemplate.postForEntity("http://zuul/transaction/transactions/withdraw", transaction, null);
		model.addAttribute("message", "Success!");
		return "withdraw";
	}

	@RequestMapping("/FundTransferLink")
	public String fundTransferForm() {
		return "FundTransfer";
	}
	// @HystrixCommand(fallbackMethod = "reliable")
	@RequestMapping("/fundTransferForm")
	public String fundTransfer(@RequestParam("sendersAccountNumber") int sendersAccountNumber,
			@RequestParam("receiversAccountNumber") int receiversAccountNumber, @ModelAttribute Transaction transaction,
			Model model) {
		transaction.setAccountNumber(sendersAccountNumber);
		restTemplate.postForEntity("http://zuul/transaction/transactions/withdraw", transaction, null);
		transaction.setAccountNumber(receiversAccountNumber);
		restTemplate.postForEntity("http://transaction/transactions", transaction, null);
		model.addAttribute("message", "Success!");
		return "FundTransfer";
	}
	// public String reliable() {
		//    return "transactions cant be done";
		//  }


	@RequestMapping("/getstatement")
	public ModelAndView getStatement(@RequestParam("offset") int offset, @RequestParam("size") int size) {
		CurrentDataSet currentDataSet = restTemplate.getForObject("http://zuul/transaction/transactions/statement",
				CurrentDataSet.class);
		int currentSize = size == 0 ? 5 : size;
		int currentOffset = offset == 0 ? 1 : offset;
		Link next = linkTo(methodOn(BankAppController.class).getStatement(currentOffset + currentSize, currentSize))
				.withRel("next");
		Link previous = linkTo(methodOn(BankAppController.class).getStatement(currentOffset - currentSize, currentSize))
				.withRel("previous");
		List<Transaction> transactions = currentDataSet.getTransactions();
		List<Transaction> currentDataSetList = new ArrayList<Transaction>();

		for (int i = currentOffset - 1; i < currentSize + currentOffset - 1; i++) {
			if ((transactions.size() <= i && i > 0) || currentOffset < 1)
				break;
			Transaction transaction = transactions.get(i);
			currentDataSetList.add(transaction);

		}
		CurrentDataSet dataSet = new CurrentDataSet(currentDataSetList, next, previous);
		/*
		 * currentDataSet.setNextLink(next); currentDataSet.setPreviousLink(previous);
		 */
		return new ModelAndView("GetStatement", "currentDataSet", dataSet);
	}
}
