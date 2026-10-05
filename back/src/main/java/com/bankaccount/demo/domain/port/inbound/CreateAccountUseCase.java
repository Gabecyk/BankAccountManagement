package com.bankaccount.demo.domain.port.inbound;

import java.util.UUID;

public interface CreateAccountUseCase {
    void createAccount(String cpf, UUID userId);
}
