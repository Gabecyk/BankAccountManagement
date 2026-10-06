package com.bankaccount.demo.infrastructure.adapter.outbound.persistence;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bankaccount.demo.domain.model.Account;
import com.bankaccount.demo.domain.model.User;
import com.bankaccount.demo.domain.port.outbound.UserRepositoryPort;

@Component
public class UserPersistenceAdapter implements UserRepositoryPort {

    private final UserJpaRepository userJpaRepository;

    public UserPersistenceAdapter(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    @Transactional
    public User save(User user) {
        if (user.getAccount() == null) {
            throw new IllegalArgumentException("User must have an account");
        }

        return toDomain(userJpaRepository.save(toEntity(user)));
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByAccountId(String accountId) {
        UUID id = UUID.fromString(accountId);
        return userJpaRepository.findByAccountId(id)
                .map(this::toDomain)
                .orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .map(this::toDomain)
                .orElse(null);
    }

    private UserJpaEntity toEntity(User user) {
        UserJpaEntity entity = new UserJpaEntity(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getCreatedAt());

        Account account = user.getAccount();
        AccountJpaEntity accountEntity = new AccountJpaEntity(
                account.getId(),
                account.getCpf(),
                account.getBalance(),
                account.getCreatedAt());
        entity.setAccount(accountEntity);
        return entity;
    }

    private User toDomain(UserJpaEntity entity) {
        AccountJpaEntity accountEntity = entity.getAccount();
        Account account = new Account(
                accountEntity.getId(),
                accountEntity.getCpf(),
                accountEntity.getBalance(),
                entity.getId(),
                accountEntity.getCreatedAt());

        return new User(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getCreatedAt(),
                account);
    }
}