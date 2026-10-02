package com.bankaccount.demo.infrastructure.adapter.outbound.persistence;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.bankaccount.demo.domain.model.Account;
import com.bankaccount.demo.domain.port.outbound.AccountRepositoryPort;

@Component
public class AccountPersistenceAdapter implements AccountRepositoryPort {

    private final AccountJpaRepository accountJpaRepository;
    private final UserJpaRepository userJpaRepository;

    public AccountPersistenceAdapter(AccountJpaRepository accountJpaRepository, UserJpaRepository userJpaRepository) {
        this.accountJpaRepository = accountJpaRepository;
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public Account findById(UUID accountId) {
        return accountJpaRepository.findById(accountId)
                .map(this::toDomain)
                .orElse(null);
    }

    @Override
    public Account findByCpf(String cpf) {
        return accountJpaRepository.findByCpf(cpf)
            .map(this::toDomain)
            .orElse(null);
    }

    @Override
    public Account save(Account account) {
        UUID userId = account.getUserId();
        if (userId == null) {
            throw new IllegalArgumentException("Account must be associated with a persisted user");
        }

        AccountJpaEntity entity = new AccountJpaEntity(
                account.getId(),
                account.getCpf(),
                account.getBalance(),
            account.getCreatedAt());
        entity.setUser(userJpaRepository.getReferenceById(userId));

        return toDomain(accountJpaRepository.save(entity));
    }

    private Account toDomain(AccountJpaEntity entity) {
        return new Account(
            entity.getId(),
            entity.getCpf(),
            entity.getBalance(),
            entity.getUser() == null ? null : entity.getUser().getId(),
            entity.getCreatedAt());
    }
}
