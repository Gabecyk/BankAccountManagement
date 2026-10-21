package com.bankaccount.demo.infrastructure.adapter.inbound.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankaccount.demo.domain.port.inbound.TransactionUseCase;
import com.bankaccount.demo.infrastructure.adapter.inbound.web.dto.TransactionRequest;

@RestController
@RequestMapping ("/api/transactions")
public class TransactionController {
    private final TransactionUseCase transactionUseCase;

    public TransactionController(TransactionUseCase transactionUseCase) {
        this.transactionUseCase = transactionUseCase;
    }

    @PostMapping("/transfer")
    public ResponseEntity<Void> createTransaction(@RequestBody TransactionRequest request) {
        if (request.senderCpf() == null || request.senderCpf().isBlank()) {
            throw new IllegalArgumentException("Sender CPF is required");
        }
        if (request.receiverCpf() == null || request.receiverCpf().isBlank()) {
            throw new IllegalArgumentException("Receiver CPF is required");
        }
        if (request.amount() <= 0) {
            throw new IllegalArgumentException("Amount must be a positive value");
        }
        if (request.transactionType() == null || request.transactionType().isBlank()) {
            throw new IllegalArgumentException("Transaction type is required");
        }

        transactionUseCase.transfer(request.senderCpf(), request.receiverCpf(), request.amount(), request.transactionType());
        return ResponseEntity.accepted().build();
    }
}
