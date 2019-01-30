package com.cg.rest.services.springBoot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;

import com.cg.rest.services.springBoot.Entity.CurrentAccount;
import com.cg.rest.services.springBoot.Entity.SavingsAccount;
import com.cg.rest.services.springBoot.Repo.AccountRepository;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringBootServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootServicesApplication.class, args);
	}

	@Bean
	@LoadBalanced
	CommandLineRunner populateData(AccountRepository accountRepository) {
		return (args) -> {
			accountRepository.save(new SavingsAccount(101, "bhanu", 50000.0, true));
			accountRepository.save(new SavingsAccount(102, "nikil", 9000.0, true));
			accountRepository.save(new SavingsAccount(103, "akhil", 17000.0, true));
			accountRepository.save(new CurrentAccount(104, "harish", 10000.0, 1000.0));
			accountRepository.save(new CurrentAccount(105, "vikas", 12000.0, 1200.0));
			accountRepository.save(new CurrentAccount(106, "shabbi", 100000.0, 10000.0));
		};
	}
}
