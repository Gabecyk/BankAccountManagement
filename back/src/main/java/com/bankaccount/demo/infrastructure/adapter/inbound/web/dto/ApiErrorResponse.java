package com.bankaccount.demo.infrastructure.adapter.inbound.web.dto;

import java.time.Instant;

public record ApiErrorResponse(
        Instant timestamp,
        int status,
        String error,
        String message,
        String path) {
}