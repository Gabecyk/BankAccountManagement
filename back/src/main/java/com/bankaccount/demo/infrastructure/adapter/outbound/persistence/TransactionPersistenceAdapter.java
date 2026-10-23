package com.bankaccount.demo.infrastructure.adapter.outbound.persistence;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bankaccount.demo.domain.model.Transaction;
import com.bankaccount.demo.domain.port.outbound.TransactionRepositoryPort;

@Component
public class TransactionPersistenceAdapter implements TransactionRepositoryPort {

    private final TransactionJpaRepository transactionJpaRepository;
    private final AccountJpaRepository accountJpaRepository;

    public TransactionPersistenceAdapter(
            TransactionJpaRepository transactionJpaRepository,
            AccountJpaRepository accountJpaRepository) {
        this.transactionJpaRepository = transactionJpaRepository;
        this.accountJpaRepository = accountJpaRepository;
    }

    @Override
    @Transactional
    public Transaction save(Transaction transaction) {
        if (transaction.getAccountIdsender() == null || transaction.getAccountIdreceiver() == null) {
            throw new IllegalArgumentException("Transaction must be associated with persisted accounts");
        }

        TransactionJpaEntity entity = new TransactionJpaEntity(
                transaction.getId(),
                accountJpaRepository.getReferenceById(transaction.getAccountIdsender()),
                accountJpaRepository.getReferenceById(transaction.getAccountIdreceiver()),
                transaction.getOccurredAt(),
                transaction.getAmount(),
                transaction.getTransactionType());

        return toDomain(transactionJpaRepository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> getTransactionsHistory(UUID accountId) {
        return transactionJpaRepository
                .findBySenderAccount_IdOrReceiverAccount_Id(accountId, accountId).stream()
                .map(this::toDomain)
                .toList();
    }

    private Transaction toDomain(TransactionJpaEntity entity) {
        return new Transaction(
                entity.getId(),
                entity.getReceiverAccount().getId(),
                entity.getSenderAccount().getId(),
                entity.getOccurredAt(),
                entity.getAmount(),
                entity.getTransactionType());
    }
}
