package com.bankaccount.demo.domain.port.inbound;

import com.bankaccount.demo.domain.model.Account;

public interface GetAccountByCpfUseCase {
    Account findByCpf(String cpf);
}