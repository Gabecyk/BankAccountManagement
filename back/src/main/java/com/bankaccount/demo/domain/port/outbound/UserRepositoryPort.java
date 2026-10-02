package com.bankaccount.demo.domain.port.outbound;

import com.bankaccount.demo.domain.model.User;

public interface UserRepositoryPort {
    User save(User user);
    User findUserByAccountId(String accountId);
}
