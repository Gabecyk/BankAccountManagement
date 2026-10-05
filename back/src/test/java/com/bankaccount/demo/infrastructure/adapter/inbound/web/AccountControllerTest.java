package com.bankaccount.demo.infrastructure.adapter.inbound.web;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.bankaccount.demo.domain.model.Account;
import com.bankaccount.demo.domain.port.inbound.CreateAccountUseCase;
import com.bankaccount.demo.domain.port.inbound.DepositUseCase;
import com.bankaccount.demo.domain.port.inbound.GetAccountByCpfUseCase;
import com.bankaccount.demo.infrastructure.adapter.inbound.web.dto.AccountResponse;
import com.bankaccount.demo.infrastructure.adapter.inbound.web.dto.DepositRequest;

class AccountControllerTest {

    @Test
    void depositDelegatesAccountIdAndAmountToUseCase() {
        UUID accountId = UUID.randomUUID();
        BigDecimal amount = new BigDecimal("25.50");
        AtomicReference<UUID> receivedAccountId = new AtomicReference<>();
        AtomicReference<BigDecimal> receivedAmount = new AtomicReference<>();

        DepositUseCase depositUseCase = (id, value) -> {
            receivedAccountId.set(id);
            receivedAmount.set(value);
        };
        CreateAccountUseCase createAccountUseCase = (cpf, userId) -> {};
        AccountController controller = new AccountController(
            depositUseCase,
            cpf -> null,
            createAccountUseCase);

        var response = controller.deposit(accountId, new DepositRequest(amount));

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(accountId, receivedAccountId.get());
        assertEquals(amount, receivedAmount.get());
    }

    @Test
    void getAccountByCpfReturnsResponseDto() {
        UUID accountId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        LocalDateTime createdAt = LocalDateTime.now();
        Account account = new Account(accountId, "12345678901", new BigDecimal("75.00"), userId, createdAt);
        GetAccountByCpfUseCase getAccountByCpfUseCase = cpf -> account;
        CreateAccountUseCase createAccountUseCase = (cpf, ownerId) -> {};
        AccountController controller = new AccountController(
            (id, amount) -> {},
            getAccountByCpfUseCase,
            createAccountUseCase);

        var response = controller.getAccountByCpf("12345678901");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(new AccountResponse(accountId, "12345678901", new BigDecimal("75.00"), userId, createdAt),
                response.getBody());
    }
}