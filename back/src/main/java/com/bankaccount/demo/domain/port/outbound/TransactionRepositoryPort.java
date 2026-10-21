package com.bankaccount.demo.domain.port.outbound;

import java.util.List;
import java.util.UUID;

import com.bankaccount.demo.domain.model.Transaction;

public interface TransactionRepositoryPort {
    Transaction save(Transaction transaction);
    List<Transaction> findBySenderAccountId(UUID accountId);
    List<Transaction> findByReceiverAccountId(UUID accountId);
}
