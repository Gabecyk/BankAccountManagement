package com.bankaccount.demo.infrastructure.adapter.outbound.persistence;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class TransactionJpaEntity {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sender_account_id", nullable = false)
    private AccountJpaEntity senderAccount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "receiver_account_id", nullable = false)
    private AccountJpaEntity receiverAccount;

    @Column(name = "occurred_at", nullable = false)
    private LocalDateTime occurredAt;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "transaction_type", nullable = false)
    private String transactionType;

    public TransactionJpaEntity() {}

    public TransactionJpaEntity(UUID id, AccountJpaEntity senderAccount, AccountJpaEntity receiverAccount,
            LocalDateTime occurredAt, BigDecimal amount, String transactionType) {
        this.id = id;
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.occurredAt = occurredAt;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public UUID getId() {
        return id;
    }

    public AccountJpaEntity getSenderAccount() {
        return senderAccount;
    }

    public AccountJpaEntity getReceiverAccount() {
        return receiverAccount;
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

    public void setId(UUID id) {
        this.id = id;
    }

    public void setSenderAccount(AccountJpaEntity senderAccount) {
        this.senderAccount = senderAccount;
    }

    public void setReceiverAccount(AccountJpaEntity receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public void setOccurredAt(LocalDateTime occurredAt) {
        this.occurredAt = occurredAt;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

}