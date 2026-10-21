package com.bankaccount.demo.domain.port.inbound;

import com.bankaccount.demo.domain.model.Account;

public interface SaveAccountUseCase {
    void saveAccount(Account account);
}
