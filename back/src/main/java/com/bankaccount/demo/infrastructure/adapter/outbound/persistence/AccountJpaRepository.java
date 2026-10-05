package com.bankaccount.demo.infrastructure.adapter.outbound.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpaRepository extends JpaRepository<AccountJpaEntity, UUID> {
    Optional<AccountJpaEntity> findByCpf(String cpf);
    Optional<AccountJpaEntity> findByUserId(UUID userId);
}
