package com.bankaccount.demo.infrastructure.adapter.outbound.persistence;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionJpaRepository extends JpaRepository<TransactionJpaEntity, UUID> {
    List<TransactionJpaEntity> findBySenderAccount_IdOrReceiverAccount_Id(UUID senderAccountId, UUID receiverAccountId);
}
