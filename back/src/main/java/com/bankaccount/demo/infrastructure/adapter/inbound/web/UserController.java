package com.bankaccount.demo.infrastructure.adapter.inbound.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankaccount.demo.domain.port.inbound.UserCreateUseCase;
import com.bankaccount.demo.infrastructure.adapter.inbound.web.dto.CreateUserRequest;

@RestController
@RequestMapping ("/api/users")
public class UserController {
    private final UserCreateUseCase userCreateUseCase;

    public UserController(UserCreateUseCase userCreateUseCase) {
        this.userCreateUseCase = userCreateUseCase;
    }

    @PostMapping("/createUser")
    public ResponseEntity<Void> createUser(@RequestBody CreateUserRequest request) {
        if (request.name() == null || request.name().isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (request.email() == null || request.email().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (request.password() == null || request.password().isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }
        if (request.cpf() == null || request.cpf().isBlank()) {
            throw new IllegalArgumentException("CPF is required");
        }

        userCreateUseCase.createUser(request.name(), request.email(), request.password(), request.cpf());
        return ResponseEntity.created(null).build();
    }
}
