package com.bankaccount.demo.infrastructure.adapter.inbound.web.dto;

import java.util.UUID;

public record CreateAccountRequest(
    String cpf,
    UUID userId
) {
}