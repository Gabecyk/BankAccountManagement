package com.bankaccount.demo.domain.service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.UUID;

import com.bankaccount.demo.domain.model.Account;
import com.bankaccount.demo.domain.port.inbound.DepositUseCase;
import com.bankaccount.demo.domain.port.outbound.AccountRepositoryPort;

public class DepositService implements DepositUseCase {

    private final AccountRepositoryPort repositoryPort;

    public DepositService(AccountRepositoryPort accountRepositoryPort) {
        this.repositoryPort = accountRepositoryPort;
    }

    @Override 
    public void deposit(UUID accountId, BigDecimal amount) {
        Account account = repositoryPort.findById(accountId);
        if(account == null) {
            throw new NoSuchElementException("Account not found");
        }

        if(amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero");
        }

        account.deposit(amount);
        repositoryPort.save(account);
    }
}