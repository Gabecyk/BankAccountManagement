package com.bankaccount.demo.domain.service;

import com.bankaccount.demo.domain.model.User;
import com.bankaccount.demo.domain.port.inbound.UserCreateUseCase;
import com.bankaccount.demo.domain.port.outbound.UserRepositoryPort;

public class CreateUserService implements UserCreateUseCase {

    private final UserRepositoryPort userRepository;

    public CreateUserService(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(String name, String email, String password, String cpf) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required to create a user.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is required to create a user.");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password is required to create a user.");
        }
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF is required to create a user.");
        }

        User existingUser = userRepository.findUserByEmail(email);
        if (existingUser != null) {
            throw new IllegalArgumentException("User with email " + email + " already exists.");
        }

        User user = new User (
            name, 
            email, 
            password, 
            cpf
        );
        userRepository.save(user);
    } 
}
