package com.bankaccount.demo.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private final UUID id;
    private final UUID accountIdreceiver;
    private final UUID accountIdsender;
    private final LocalDateTime occurredAt;
    private final BigDecimal amount;
    private final String transactionType;

    public Transaction(UUID id, UUID accountIdreceiver, UUID accountIdsender, LocalDateTime occurredAt, BigDecimal amount, String transactionType) {
        this.id = id;
        this.accountIdreceiver = accountIdreceiver;
        this.accountIdsender = accountIdsender;
        this.occurredAt = occurredAt;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public Transaction(Account senderAccount, Account receiverAccount, BigDecimal amount, String transactionType) {
        this.id = UUID.randomUUID();
        this.accountIdsender = senderAccount.getId();
        this.accountIdreceiver = receiverAccount.getId();
        this.occurredAt = LocalDateTime.now();
        this.amount = amount;
        this.transactionType = transactionType;
    }

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public UUID getAccountIdreceiver() {
        return accountIdreceiver;
    }

    public UUID getAccountIdsender() {
        return accountIdsender;
    }

    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getTransactionType() {
        return transactionType;
    }
}
