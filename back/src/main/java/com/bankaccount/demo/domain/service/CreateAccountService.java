package com.bankaccount.demo.domain.service;

import java.util.UUID;

import com.bankaccount.demo.domain.model.Account;
import com.bankaccount.demo.domain.port.inbound.CreateAccountUseCase;
import com.bankaccount.demo.domain.port.outbound.AccountRepositoryPort;

public class CreateAccountService implements CreateAccountUseCase {

    private final AccountRepositoryPort accountRepository;

    public CreateAccountService(AccountRepositoryPort accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void createAccount(String cpf, UUID userId) {
        Account existingAccount = accountRepository.findByCpf(cpf);
        Account existingUserAccount = accountRepository.findByUserId(userId);
        if (existingAccount != null) {
            throw new IllegalArgumentException("Account with CPF " + cpf + " already exists.");
        }
        if (existingUserAccount != null) {
            throw new IllegalArgumentException("User with ID " + userId + " already has an account.");
        }

        if (userId == null || userId.toString().isBlank()) {
            throw new IllegalArgumentException("User ID is required to create an account.");
        }

        Account newAccount = new Account(
            cpf,
            userId
        );

        accountRepository.save(newAccount);
    }
    
}
