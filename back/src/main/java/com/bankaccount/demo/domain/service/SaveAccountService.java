package com.bankaccount.demo.domain.service;

import com.bankaccount.demo.domain.model.Account;
import com.bankaccount.demo.domain.port.inbound.SaveAccountUseCase;
import com.bankaccount.demo.domain.port.outbound.AccountRepositoryPort;

public class SaveAccountService implements SaveAccountUseCase {
    private final AccountRepositoryPort accountRepositoryPort;

    public SaveAccountService(AccountRepositoryPort accountRepositoryPort) {
        this.accountRepositoryPort = accountRepositoryPort;
    }

    @Override
    public void saveAccount(Account account) {
        accountRepositoryPort.update(account);
    }
    
}
