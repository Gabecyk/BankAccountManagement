package com.bankaccount.demo.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bankaccount.demo.domain.port.inbound.CreateAccountUseCase;
import com.bankaccount.demo.domain.port.inbound.DepositUseCase;
import com.bankaccount.demo.domain.port.inbound.GetAccountByCpfUseCase;
import com.bankaccount.demo.domain.port.outbound.AccountRepositoryPort;
import com.bankaccount.demo.domain.service.CreateAccountService;
import com.bankaccount.demo.domain.service.DepositService;
import com.bankaccount.demo.domain.service.GetAccountByCpfService;

@Configuration
public class BeanConfiguration {

	@Bean
	public DepositUseCase depositUseCase(AccountRepositoryPort accountRepositoryPort) {
		return new DepositService(accountRepositoryPort);
	}

	@Bean
	public GetAccountByCpfUseCase getAccountByCpfUseCase(AccountRepositoryPort accountRepositoryPort) {
		return new GetAccountByCpfService(accountRepositoryPort);
	}

	@Bean 
	public CreateAccountUseCase createAccountUseCase(AccountRepositoryPort accountRepositoryPort) {
		return new CreateAccountService(accountRepositoryPort);
	}
}
