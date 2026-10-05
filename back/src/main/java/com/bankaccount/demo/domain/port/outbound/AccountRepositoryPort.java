package com.bankaccount.demo.domain.port.outbound;

import java.util.UUID;

import com.bankaccount.demo.domain.model.Account;

public interface AccountRepositoryPort { // implemented in AccountRepositoryAdapter
    Account findById(UUID accountId);
    Account findByUserId(UUID userId);
    Account findByCpf(String cpf);
    Account save(Account account);
}
