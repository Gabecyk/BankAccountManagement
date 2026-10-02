package com.bankaccount.demo.infrastructure.adapter.inbound.web.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record AccountResponse(
        UUID id,
        String cpf,
        BigDecimal balance,
        UUID userId,
        LocalDateTime createdAt) {
}