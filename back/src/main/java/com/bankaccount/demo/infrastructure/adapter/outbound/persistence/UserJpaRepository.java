package com.bankaccount.demo.infrastructure.adapter.outbound.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, UUID> {
	Optional<UserJpaEntity> findByAccount_Id(UUID accountId);
}
