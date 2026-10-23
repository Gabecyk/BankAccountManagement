package com.bankaccount.demo.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bankaccount.demo.domain.port.inbound.CreateAccountUseCase;
import com.bankaccount.demo.domain.port.inbound.DepositUseCase;
import com.bankaccount.demo.domain.port.inbound.GetAccountByCpfUseCase;
import com.bankaccount.demo.domain.port.inbound.GetTransactionHistoryUseCase;
import com.bankaccount.demo.domain.port.inbound.SaveAccountUseCase;
import com.bankaccount.demo.domain.port.inbound.TransactionUseCase;
import com.bankaccount.demo.domain.port.inbound.UserCreateUseCase;
import com.bankaccount.demo.domain.port.outbound.AccountRepositoryPort;
import com.bankaccount.demo.domain.port.outbound.TransactionRepositoryPort;
import com.bankaccount.demo.domain.port.outbound.UserRepositoryPort;
import com.bankaccount.demo.domain.service.CreateAccountService;
import com.bankaccount.demo.domain.service.CreateUserService;
import com.bankaccount.demo.domain.service.DepositService;
import com.bankaccount.demo.domain.service.GetAccountByCpfService;
import com.bankaccount.demo.domain.service.GetTransactionHistory;
import com.bankaccount.demo.domain.service.SaveAccountService;
import com.bankaccount.demo.domain.service.TransactionService;

@Configuration
public class BeanConfiguration {

	// Account

	@Bean
	public DepositUseCase depositUseCase(AccountRepositoryPort accountRepositoryPort) {
		return new DepositService(accountRepositoryPort);
	}

	@Bean
	public SaveAccountUseCase saveAccountUseCase(AccountRepositoryPort accountRepositoryPort) {
    	return new SaveAccountService(accountRepositoryPort);	
	}

	@Bean
	public GetAccountByCpfUseCase getAccountByCpfUseCase(AccountRepositoryPort accountRepositoryPort) {
		return new GetAccountByCpfService(accountRepositoryPort);
	}

	@Bean 
	public CreateAccountUseCase createAccountUseCase(AccountRepositoryPort accountRepositoryPort) {
		return new CreateAccountService(accountRepositoryPort);
	}

	// User

	@Bean 
	public UserCreateUseCase userCreateUseCase(UserRepositoryPort userRepositoryPort) {
		return new CreateUserService(userRepositoryPort);
	}

	// Transactions

	@Bean
	public TransactionUseCase transactionUseCase(
			TransactionRepositoryPort transactionRepositoryPort,
			GetAccountByCpfUseCase getAccountByCpfUseCase,
			SaveAccountUseCase saveAccountUseCase) {
		return new TransactionService(
				transactionRepositoryPort,
				getAccountByCpfUseCase,
				saveAccountUseCase);
	}

	@Bean
	public GetTransactionHistoryUseCase getTransactionHistoryUseCase(
			TransactionRepositoryPort transactionRepositoryPort,
			GetAccountByCpfUseCase getAccountByCpfUseCase) {
		return new GetTransactionHistory(transactionRepositoryPort, getAccountByCpfUseCase);
	}
}
