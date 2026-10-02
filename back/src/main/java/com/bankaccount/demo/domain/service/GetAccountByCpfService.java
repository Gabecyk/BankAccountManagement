package com.bankaccount.demo.domain.service;

import java.util.NoSuchElementException;

import com.bankaccount.demo.domain.model.Account;
import com.bankaccount.demo.domain.port.inbound.GetAccountByCpfUseCase;
import com.bankaccount.demo.domain.port.outbound.AccountRepositoryPort;

public class GetAccountByCpfService implements GetAccountByCpfUseCase {

    private final AccountRepositoryPort accountRepositoryPort;

    public GetAccountByCpfService(AccountRepositoryPort accountRepositoryPort) {
        this.accountRepositoryPort = accountRepositoryPort;
    }

    @Override
    public Account findByCpf(String cpf) {
        Account account = accountRepositoryPort.findByCpf(cpf);
        if (account == null) {
            throw new NoSuchElementException("Account not found");
        }
        return account;
    }
}