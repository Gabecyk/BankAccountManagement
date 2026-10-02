package com.bankaccount.demo.infrastructure.adapter.inbound.web;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankaccount.demo.domain.port.inbound.DepositUseCase;
import com.bankaccount.demo.domain.port.inbound.GetAccountByCpfUseCase;
import com.bankaccount.demo.domain.model.Account;
import com.bankaccount.demo.infrastructure.adapter.inbound.web.dto.AccountResponse;
import com.bankaccount.demo.infrastructure.adapter.inbound.web.dto.DepositRequest;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final DepositUseCase depositUseCase;
    private final GetAccountByCpfUseCase getAccountByCpfUseCase;

    public AccountController(DepositUseCase depositUseCase, GetAccountByCpfUseCase getAccountByCpfUseCase) {
        this.depositUseCase = depositUseCase;
        this.getAccountByCpfUseCase = getAccountByCpfUseCase;
    }

    @PostMapping("/{accountId}/deposit")
    public ResponseEntity<Void> deposit(@PathVariable UUID accountId, @RequestBody DepositRequest request) {
        if (request.amount() == null) {
            throw new IllegalArgumentException("amount is required");
        }
        depositUseCase.deposit(accountId, request.amount());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<AccountResponse> getAccountByCpf(@PathVariable String cpf) {
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF is required");
        }

        Account account = getAccountByCpfUseCase.findByCpf(cpf);
        AccountResponse response = new AccountResponse(
                account.getId(),
                account.getCpf(),
                account.getBalance(),
                account.getUserId(),
                account.getCreatedAt());
        return ResponseEntity.ok(response);
    }
    

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Void> handleNotFound(NoSuchElementException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Void> handleBadRequest(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().build();
    }
}