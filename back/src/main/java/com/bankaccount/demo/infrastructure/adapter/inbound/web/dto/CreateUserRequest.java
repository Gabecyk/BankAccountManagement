package com.bankaccount.demo.infrastructure.adapter.inbound.web.dto;

public record CreateUserRequest(
    String name,
    String email,
    String password,
    String cpf
) {
}