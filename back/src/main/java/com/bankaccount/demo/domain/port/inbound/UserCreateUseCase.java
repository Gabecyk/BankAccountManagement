package com.bankaccount.demo.domain.port.inbound;

public interface UserCreateUseCase {
    void createUser(String name, String email, String password, String cpf);
}
